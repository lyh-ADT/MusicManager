class Lyrics{
    constructor(text, music_player){
        this.current_line = 0;
        this.root = document.getElementById("lyrics");
        this.script = this.parseText(text);
        this.fillLyric();
        this.music_player = music_player;
    }

    parseText(text){
        let script = [];
        const list = text.split("\n");
        for(const line of list){
            const arr = line.split("]", 2);
            const time = arr[0];
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
}