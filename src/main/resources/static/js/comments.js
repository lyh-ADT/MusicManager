const comments_app = new Vue({
    el: "#comments_app",
    data: {
       comments: []
    },
    mounted: function () {
        const sid = window.parent.musicPlayer.sid;
        if(sid){
            $.get(`/song/${sid}/comments`, (result) => {
                this.comments = result;
            });
        }
        this.comments.push({
            avatar: "http://p1.music.126.net/sQKLXBR_GThk5n-M2wtdDg==/758663033420897.jpg?param=130y130",
            nickname: "昵称",
            content: "评论"
        })
    }
});