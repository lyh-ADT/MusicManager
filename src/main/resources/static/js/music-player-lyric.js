class Lyrics{
    static highlightCssClass = "active";

    /**
     * 把00:00:00格式的字符串转换成秒钟数
     * @param {string} text 
     * @return {number} 秒钟数
     */
    static parseTime(text) {
        const components = text.split(":");
        const hours = parseInt(components[0]);
        const minutes = parseInt(components[1]);
        const seconds = parseInt(components[2]);
        return ((hours * 60) + minutes) * 60 + seconds;
    }

    constructor(text, music_player){
        this.current_line = 0;
        this.root = document.getElementById("lyrics");
        this.script = this.parseText(text);
        this.fillLyric();
        this.music_player = music_player;
        music_player.bindTimeUpdateListener(this.updateHighlight);
    }

    parseText(text){
        let script = [];
        const list = text.split("\n");
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
                index = i;
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
    }
}

const musicPlayer = window.parent.musicPlayer;
const lyrics = new Lyrics("", musicPlayer);
