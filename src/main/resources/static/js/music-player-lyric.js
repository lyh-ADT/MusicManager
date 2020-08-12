class Lyrics{
    static highlightCssClass = "active";
    static lyricSplit = "<br>";

    /**
     * 把00:00.00格式的字符串转换成秒钟数
     * @param {string} text 
     * @return {number} 秒钟数
     */
    static parseTime(text) {
        const components = text.split(":");
        const minutes = parseInt(components[0]);
        const seconds = parseFloat(components[1]);
        return (minutes * 60) + seconds;
    }

    constructor(music_player){
        this.current_line = 0;
        this.root = document.getElementById("lyrics");
        this.music_player = music_player;
        music_player.bindTimeUpdateListener(this.updateHighlight);
        music_player.oncanplay = ()=>this.getLyric();
        this.getLyric();
    }

    /**
     * 解析文本得到歌词脚本
     * @param text
     * @returns {[{time, content}]}
     */
    parseText(text){
        let script = [];
        const list = text.split(Lyrics.lyricSplit);
        for(const line of list){
            const arr = line.split("]", 2);
            // 去掉前面的 "["
            const time = arr[0].substring(1);
            const content = arr[1];
            script.push({
                time: time,
                content: content
            });
        }
        return script;
    }

    getLyric = () => {
        const sid = this.music_player.sid;
        if(!sid){
            console.warn("没有播放音乐");
            return;
        }
        $.ajax({
            url:`/song/${this.music_player.sid}/lyric`,
            method:"GET",
            dataType:"text",
            success:(result)=>{
                this.script = this.parseText(result);
                this.fillLyric();
            }
        })
    }

    fillLyric(){
        this.root.innerHTML = "";
        for(const line of this.script){
            const p = document.createElement("p");
            p.innerHTML = line.content;
            p.dataset["timestamp"] = line.time;
            this.root.appendChild(p);
        }
    }

    updateHighlight = () => {
        const currentTime = this.music_player.currentTime;
        let index = this.script.length-1;
        for (let i = 0; i < this.script.length; ++i) {
            const line = this.script[i];
            const line_timestamp = Lyrics.parseTime(line.time);
            if (line_timestamp > currentTime) {
                index = i > 0? i - 1: 0;
                break;
            }
        }
        this.setHighlightByIndex(index);
    }

    setHighlightByIndex(index) {
        const lines = this.root.querySelectorAll("p");
        if (lines.length <= index) {
            throw new Error("下标越界啦");
        }
        const timestamp = this.script[index].time;
        let needUpdate = null;
        for (let line of lines) {
            line.classList.remove(Lyrics.highlightCssClass);
            if (line.dataset.timestamp === timestamp) {
                needUpdate = line;
            }
        }
        needUpdate.classList.add(Lyrics.highlightCssClass);
        needUpdate.scrollIntoView();
    }
}

const musicPlayer = window.parent.musicPlayer;
const lyrics = new Lyrics(musicPlayer);

window.onunload = function () {
    // 清除musicPlayer的监听器，目前只有Lyrics会绑定它，所以直接清空
    window.parent.musicPlayer.timeUpdateListeners = [];
}