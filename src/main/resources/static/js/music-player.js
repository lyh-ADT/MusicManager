class MusicPlayer extends Audio{
    constructor(url){
        super(url);
        this.bindListeners();
        this.musicInfo = {};
        this.timeUpdateListeners = [];
        this.musicList = [];
        this.currentMusicIndex = 0;
        this.currentCycleModeIndex = 0;
    }

    /**
     * 把秒钟数转换成“分钟：秒钟”的字符串
     * @param {number} seconds 
     * @returns {string}
     */
    static parseTime(seconds){
        let second = Math.round(seconds % 60);
        let minute = Math.round(seconds / 60);
        return `${minute}:${second < 10? "0"+second: second}`;
    }

    cycleModes = [
        {
            name: "列表循环",
            icon: "images/loop_list.png",
            strategy: () => {
                this.currentMusicIndex = (this.currentMusicIndex + 1)%this.musicList.length;
                this.play(this.musicList[this.currentMusicIndex]);
            }
        },
        {
            name: "单曲循环",
            icon: "images/loop_one.png",
            strategy: () => {
                return super.play();
            }
        },
        {
            name: "随机播放",
            icon: "images/loop_random.png",
            strategy: () => {
                const index = Math.floor(Math.random() * this.musicList.length);
                this.play(index);
            }
        },
        {
            name: "私人FM",
            icon: "javascript:void(0)",
            strategy: () => {
                $.get("/song/randomId", (result)=>{
                    this.play(parseInt(result));
                }, "text");
            }
        }
    ];

    bindListeners(){
        this.play_btn = document.getElementById("player_play_btn");
        this.play_btn.onclick = ()=>{
            if(this.paused){
                super.play();
                this.play_btn.src = "./images/pause_button.png";
            }else{
                super.pause();
                this.play_btn.src = "./images/play_button.png";
            }
        };
        this.progress_bar = document.getElementById("player_progress_bar");
        this.progress_bar.oninput = ()=>{
            super.currentTime = this.progress_bar.value;
        }
        this.time = document.getElementById("player_time");

        this.player_last_song_btn = document.getElementById("player_last_song_btn");
        this.player_last_song_btn.onclick = this.lastSong;

        this.player_next_song_btn = document.getElementById("player_next_song_btn");
        this.player_next_song_btn.onclick = ()=>this.nextSong();

        this.volume_bar = document.getElementById("player_volume_bar");
        this.volume_bar.value = this.volume_bar.max = 100;
        this.volume_bar.min = 0;
        this.volume_bar.step = 1;
        this.volume_bar.oninput = ()=>{
            super.volume = this.volume_bar.value / this.volume_bar.max;
        }

        this.player_cycle_mode_btn = document.getElementById("player_cycle_mode_btn");
        this.player_cycle_mode_btn.onclick = ()=>this.changeCycleMode();

        super.onloadeddata = this.loadeddata;
        super.ontimeupdate = this.timeupdate;
        super.onended = this.ended;
        super.onerror = ()=>this.nextSong();
    }

    /**
     * 播放指定歌曲ID的歌，
     * @param {number} sid 歌曲ID
     * @param {number} mlid 歌单ID可选
     */
    play(sid, mlid=undefined){
        if(sid !== undefined){
            this.sid = sid;
            super.src = `/song/${sid}/mp3`;
            this.updateInfo();
        }
        if(mlid){
            this.getMusicList(mlid, sid);
        }
        super.play();
        this.play_btn.src = "./images/pause_button.png";
    }

    updateInfo(){
        $.get(`/song/${this.sid}/info`, (data)=>{
            this.setInfo(data);
            this.musicInfo = data;
        }, "json");
    }

    setInfo(data){
        document.getElementById("player_info_title").textContent = data.name;
        document.getElementById("player_info_singer").textContent = data.singer;
        document.getElementById("player_info_liked").src = data.liked ?　"./images/favor_icon_like.png":"./images/favor_icon_unlike.png";
    }

    getMusicList(mlid, playingSid){
        const uri = 'showMusicListInfo';
        var url = uri + "?mlid=" + mlid;
        $.post(url, (response)=>{
            // console.info(response.data)
            this.musicList = [];
            for(let i=0; i < response.length; ++i){
                const sid = response[i].sid;
                this.musicList.push(sid);
                if(parseInt(sid) === playingSid){
                    this.currentMusicIndex = i;
                }
            }
        });
    }

    lastSong = ()=>{
        this.currentMusicIndex = (this.currentMusicIndex - 1 + this.musicList.length)%this.musicList.length;
        this.play(this.musicList[this.currentMusicIndex]);
    }

    nextSong = () => {
        this.currentMusicIndex = (this.currentMusicIndex + 1)%this.musicList.length;
        this.play(this.musicList[this.currentMusicIndex]);
    }

    loadeddata = ()=>{
        this.progress_bar.value = this.progress_bar.min = 0;
        this.progress_bar.max = Math.round(this.duration);
        this.progress_bar.step = 1;
    }

    timeupdate = ()=>{
        this.progress_bar.value = this.currentTime;
        this.time.textContent = MusicPlayer.parseTime(this.currentTime);
        for (let callback of this.timeUpdateListeners) {
            callback();
        }
    }

    ended = ()=>{
        this.nextSong();
    }

    /**
     * 添加更新播放时间的回调函数
     * @param {function():void} callback 
     */
    bindTimeUpdateListener(callback) {
        this.timeUpdateListeners.push(callback);
    }

    /**
     * 播放私人FM
     * 随机任意歌曲
     */
    playFM(){
        for(let i=0; i < this.cycleModes.length; i++){
            const cycleMode = this.cycleModes[i];
            if(cycleMode.name === "私人FM"){
                this.changeCycleMode(i);
                this.nextSong();
                return;
            }
        }
        console.error("没有私人FM模式");
    }

    /**
     * 切换播放循环模式
     * @param index {number} 可选，如果不指定就下一个模式，否则直接进入指定的模式，且不更新this.currentCycleModeIndex
     */
    changeCycleMode = (index=undefined)=>{
        if(index === undefined){
            this.currentCycleModeIndex = index = (this.currentCycleModeIndex + 1) % (this.cycleModes.length-1);
        }
        const cycleMode = this.cycleModes[index];
        this.player_cycle_mode_btn.src = cycleMode.icon;
        this.nextSong = cycleMode.strategy;
        console.log("播放模式: "+cycleMode.name);
    }
}

// 绑定到window对象，让iframe内可以访问
window.musicPlayer = new MusicPlayer();