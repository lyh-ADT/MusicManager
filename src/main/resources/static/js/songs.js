var vue = new Vue({
    el: "#songsInfo",
    data: {
        uri: 'searchSongs',
        songs: []
    },
    methods: {
        search: function (searchInfo) {
            var url = this.uri + "?searchInfo=" + searchInfo;
            axios.post(url).then(function (response) {
                console.info(response.data)
                vue.$data.songs = response.data;
                $("#searchResult").text(searchInfo);
                $("#searchSongNum").text(response.data.length);
            });
        },
        play:(sid)=>{
            window.parent.musicPlayer.play(sid);
        }
    }
});

window.search_app = vue;