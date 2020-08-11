const comments_app = new Vue({
    el: "#comments_app",
    data: {
        comments: [],
        inputComment: ""
    },
    methods:{
        addComment: function(ev){
            console.log(ev.keyCode)
            if(ev.keyCode !== 13){
                return;
            }
            console.log(this.inputComment)
            const sid = window.parent.musicPlayer.sid;
            if(!sid){
                return;
            }
            $.ajax({
                url: `/song/${sid}/comment`,
                method:"PUT",
                data: {content:this.inputComment, sub_cid:null},
                success:function(result){
                    alert(result);
                }
            });
        }
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