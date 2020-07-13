class MusicPlayer extends Audio{
    constructor(url){
        super(url);
        this.bindListeners();
    }

    bindListeners(){
        this.play_btn = document.getElementById("player_play_btn");
        this.play_btn.onclick = ()=>{
            if(this.paused){
                super.play();
            }else{
                super.pause();
            }
        };
        this.progress_bar = document.getElementById("player_progress_bar");
        this.progress_bar.oninput = (ev)=>{
            super.currentTime = this.progress_bar.value;
        }
        
        super.onloadeddata = this.loadeddata;
        super.ontimeupdate = this.timeupdate;
    }

    play(src){
        super.src = src;
        super.play();
    }

    loadeddata = ()=>{
        this.progress_bar.value = this.progress_bar.min = 0;
        this.progress_bar.max = Math.round(this.duration);
        this.progress_bar.step = 1;
    }

    timeupdate = ()=>{
        this.progress_bar.value = this.currentTime;
    }
}

let mp = new MusicPlayer();
// mp.src = "成都 - 赵雷.mp3";