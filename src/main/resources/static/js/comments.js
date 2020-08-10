const comments_app = new Vue({
    el: "#comments_app",
    data: {
       comments: []
    },
    mounted: function () {
        this.comments.push({
            avatar: "http://p1.music.126.net/sQKLXBR_GThk5n-M2wtdDg==/758663033420897.jpg?param=130y130",
            nickname: "昵称",
            content: "评论"
        })
        this.comments.push({
            avatar: "http://p1.music.126.net/sQKLXBR_GThk5n-M2wtdDg==/758663033420897.jpg?param=130y130",
            nickname: "昵称",
            content: "评论"
        })
    }
});