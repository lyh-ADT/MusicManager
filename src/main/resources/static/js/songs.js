var vue = new Vue({
    el: "#songsInfo",
    data: {
        uri: 'searchSongs',
        songs: []
    },
    methods: {
        play:(sid)=>{
            window.parent.musicPlayer.play(sid);
            window.parent.musicPlayer.changeCycleMode(window.parent.musicPlayer.currentCycleModeIndex);
        }
    }
});

window.search_app = vue;