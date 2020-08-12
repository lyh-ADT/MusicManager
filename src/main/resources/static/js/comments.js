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
                },
                error: function () {
                    alert("未登录");
                }
            });
        },
        addSubComment: function (cid) {
            const dialog = document.getElementById("comment-popover");
            dialog.style.display = "block";
            // 居中显示
            dialog.style.top = (window.innerHeight - dialog.clientHeight) / 2 + "px";
            dialog.style.left = (window.innerWidth - dialog.offsetWidth) / 2 + "px";

            const sid = window.parent.musicPlayer.sid;
            const input = dialog.getElementsByTagName("textarea")[0];
            const button = dialog.getElementsByTagName("button")[0];
            button.onclick = function () {
                $.ajax({
                    url: `/song/${sid}/comment`,
                    method:"PUT",
                    data: {content:input.value, sub_cid:cid},
                    success:function(result){
                        alert(result);
                        input.value = "";
                        dialog.style.display = "none";
                    },
                    error: function () {
                        alert("未登录");
                    }
                })
            }
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