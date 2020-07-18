class MusicPlayer extends Audio{
    constructor(url){
        super(url);
        this.bindListeners();
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
        this.progress_bar.oninput = (ev)=>{
            super.currentTime = this.progress_bar.value;
        }
        this.time = document.getElementById("player_time");
        
        this.volume_bar = document.getElementById("player_volume_bar");
        this.volume_bar.value = this.volume_bar.max = 100;
        this.volume_bar.min = 0;
        this.volume_bar.step = 1;
        this.volume_bar.oninput = (ev)=>{
            super.volume = this.volume_bar.value / this.volume_bar.max;
        }
        
        super.onloadeddata = this.loadeddata;
        super.ontimeupdate = this.timeupdate;
    }

    /**
     * 播放指定歌曲ID的歌，
     * @param {number} sid 歌曲ID
     * @param {number} mlid 歌单ID可选
     */
    play(sid, mlid){
        if(sid != undefined){
            this.sid = sid;
            const src = `/song/${sid}/mp3`;
            super.src = src;
            this.updateInfo();
        }
        // TODO: 歌单部分的逻辑，等dsq
        super.play();
        this.play_btn.src = "./images/pause_button.png";
    }

    updateInfo(){
        $.get(`/song/${this.sid}/info`, (data)=>{
            this.setInfo(data);
        }, "json");
    }

    setInfo(data){
        document.getElementById("player_info_title").textContent = data.name;
        document.getElementById("player_info_singer").textContent = data.singer;
        document.getElementById("player_info_liked").src = data.liked ?　"./images/favor_icon_like.png":"./images/favor_icon_unlike.png";
    }

    loadeddata = ()=>{
        this.progress_bar.value = this.progress_bar.min = 0;
        this.progress_bar.max = Math.round(this.duration);
        this.progress_bar.step = 1;
    }

    timeupdate = ()=>{
        this.progress_bar.value = this.currentTime;
        this.time.textContent = MusicPlayer.parseTime(this.currentTime);
    }
}

let mp = new MusicPlayer();