var vue = new Vue({
    el: "#vue-app",
    data: {
        uri: 'showMusicListInfo',
        uriJudgeLikeORNotInfo: 'judgeLikeOrNot',
        ListData: [],
        songsData: [],
        likeOrNot: [],
        removeableMusicList:[],
        mlid: 0,
        choiceSid:0
    },
    methods: {
        getMusicList: function (mlid) {  //获取歌单中的歌曲信息
            vue.getMusicListInfo(mlid);
            vue.getRemoveableMusicList(mlid);
            vue.$data.mlid = mlid;
            var url = this.uri + "?mlid=" + mlid;
            axios.post(url).then(function (response) {
                console.info(response.data)
                vue.JudgeLikeORNotInfo(mlid);
                vue.$data.songsData = response.data;
                vue.$nextTick(() => {

                    showTrCSS();
                })
            });
        },
        getMusicListInfo: function (mlid) {  //获取歌单的信息
            var url = "getMusicListIfo" + "?mlid=" + mlid;
            axios.post(url).then(function (response) {
                console.info(response.data);
                vue.$data.ListData = response.data;
            });
        },
        JudgeLikeORNotInfo: function (mlid) {
            vue.$data.mlid = mlid;
            var url1 = this.uriJudgeLikeORNotInfo + "?mlid=" + mlid;
            axios.post(url1).then(function (response) {
                // console.info(response.data)
                vue.$data.likeOrNot = response.data;
                for (var i = 0; i < vue.$data.songsData.length; i++) {
                    for (var j = 0; j < vue.$data.likeOrNot.length; j++) {
                        if (vue.$data.songsData[i].sid == vue.$data.likeOrNot[j]) {
                            vue.$data.songsData[i].likeOrNot = true;
                        }
                    }
                }
                vue.$nextTick(() => {
                    // choices();
                    showTrCSS();
                    $(".showMoveMusicList").hover(function () {
                        $(".showOptions").show();
                        $(".showMoveMusicList").show();
                    }, function () {
                        $(".showMoveMusicList").hide();
                    })
                    $(".showOptions").hover(function () {
                        $(".showOptions").show();
                    }, function () {
                        $(".showOptions").hide();
                    })
                })
            });
        },
        play: (sid) => {
            window.parent.musicPlayer.play(sid, parseInt(vue.$data.mlid));
            window.parent.musicPlayer.changeCycleMode(window.parent.musicPlayer.currentCycleModeIndex);
        },

        getRemoveableMusicList: function(mlid){
            let url = "getRemoveableMusicList" +"?mlid=" +mlid;
            axios.post(url).then(function(response){
                vue.$data.removeableMusicList = response.data;
                console.info(vue.$data.removeableMusicList)

                vue.$nextTick(() => {
                    // choices();
                    showTrCSS();
                })

            })
        }
    },
    mounted: function () {
        // console.info(vue.$data.songs);
    }

});

function choiceLikeOrNot(obj, choice) {
    console.info(vue.$data.mlid);
    let sid = $(obj).parent().parent().attr("id");
    if (choice == 1) {  //添加喜欢方法
        let url = "addLike" + "?sid=" + sid;
        axios.post(url).then(function (response) {
            if (response.status == 200) {
                vue.getMusicList(vue.$data.mlid);
            } else if (response.status == 500) {
                alert("操作失败");
            }
        });
    } else if (choice == 0) { //取消喜欢方法
        let url1 = "cancelLike" + "?sid=" + sid;
        axios.post(url1).then(function (response) {
            console.info(response.status);
            if (response.status == 200) {
                vue.getMusicList(vue.$data.mlid);
            } else if (response.status == 500) {
                alert("操作失败");
            }

        });
    }
}



function choices(obj) {
    vue.$data.choiceSid = $(obj).attr("id");    //将选择的歌单信息记录下来
    document.getElementById("songsInfo").onmouseup = function (e) {
        if (e.button === 2) {       //如果button=1（鼠标左键），button=2（鼠标右键），button=0（鼠标中间键）
            $(".showOptions").css("margin-top", (e.clientY - 260) + 'px');     //鼠标点击时给div定位Y轴
            $(".showOptions").css("margin-left", e.clientX + 'px');    //鼠标点击时给div定位X轴
            $(".showOptions").show();        //显示div盒子
        } else {
            $(".showOptions").hide();          //否则不显示div盒子
        }
    }
}
//将歌曲添加到选择歌单方法
function changeSongStatusInThisMusicList(obj , choice){
    //将歌曲添加到指定歌单操作
    if(choice == 1){
        // console.info($(obj).attr("id").split("-")[1]);
        // console.info(vue.$data.choiceSid);
        let mlid = $(obj).attr("id").split("-")[1];
        let sid = vue.$data.choiceSid;
        let url = "addSongToMusicList" + "?mlid=" + mlid+"&sid=" + sid;
        axios.post(url).then(function (response) {
            console.info(response.status);
            if (response.status == 200) {
                vue.getMusicList(vue.$data.mlid);
            } else if (response.status == 500) {
                alert("操作失败");
            }
        });
    } else if(choice == 0){
        let mlid = vue.$data.mlid;
        let sid = vue.$data.choiceSid;
        let url = "deleteSongToMusicList" + "?mlid=" + mlid+"&sid=" + sid;
        axios.post(url).then(function (response) {
            console.info(response.status);
            if (response.status == 200) {
                vue.getMusicList(vue.$data.mlid);
            } else if (response.status == 500) {
                alert("操作失败");
            }
        });
    }
}
//点击“移动到歌单显示可移动的歌单信息”
function showRemoveableMusicList(){
    let top = $(".showOptions").offset().top;
    let left = $(".showOptions").offset().left;
    $(".showMoveMusicList").css("margin-top", (top-220) + 'px');     //鼠标点击时给div定位Y轴
    $(".showMoveMusicList").css("margin-left", (left+120) + 'px');    //鼠标点击时给div定位X轴
    $(".showMoveMusicList").show();        //显示div盒子
}



function showTrCSS() {
    $("#table4Songs>tbody>tr:even").css("background", 'snow');
    $("#table4Songs>tbody>tr:odd").css("background", 'white');
    $("#table4Songs>tbody>tr").unbind();  //取消所有绑定
    $("#table4Songs>tbody>tr").bind({
        mouseout: function () {
            showTrCSS();
        },
        mouseover: function () {
            $(this).css("background", 'lightgrey');

        }

    })
}

window.getMusicList_app = vue;