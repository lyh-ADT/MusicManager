var vue = new Vue({
    el: "#vue-app",
    data: {
        uri: 'showMusicListInfo',
        uriJudgeLikeORNotInfo: 'judgeLikeOrNot',
        ListData: [{date:"",description:"",imgurl:"",mlid:"",mlname: "",uid:""}],
        songsData: [],
        likeOrNot: [],
        searchSongs: [],
        removeableMusicList: [],
        likeOrNotForSearch: [],
        mlid: 0,
        choiceSid: 0,
        searchInfo: "",
        uid:  window.parent.uid,          //登录用户的id
        userFavoriteList:window.parent.userFavoriteList ,//登录用户最喜欢的列表
        judgeListBelongOrnotInfo:false,     //判断当前歌单是否存在用户的创建或收藏列表中
    },
    methods: {
        getMusicList: function (mlid) {  //获取歌单中的歌曲信息
            console.info(vue.$data.ListData[0].mlname)
            vue.getMusicListInfo(mlid);
            // vue.getRemoveableMusicList(mlid);
            vue.$data.mlid = mlid;
            var url = this.uri + "?mlid=" + mlid ;
            axios.post(url).then(function (response) {
                // console.info(response.data)
                vue.JudgeLikeORNotInfo(mlid);
                vue.$data.songsData = response.data;
                vue.$nextTick(() => {
                    showTrCSS();
                })
            });
        },
        judgeListBelongOrnot:function(mlid){
            if(vue.$data.uid == "" || vue.$data.uid < 1){
                return;
            }
            let url = "judgeListBelongOrnot" + "?mlid=" + mlid + "&uid=" + vue.$data.uid;
            axios.post(url).then(function(response){
                console.info("是否存在用户列表" + response.data)
                if(response.data == 0){
                    vue.$data.judgeListBelongOrnotInfo = true;
                }
                console.info("是否可收藏"+ vue.$data.judgeListBelongOrnotInfo)

            });
        },
        search: function (searchInfo) {
            vue.$data.searchInfo = searchInfo;
            // vue.$data.mlid = 0;
            // vue.getRemoveableMusicList(0);
            var url = "searchSongs" + "?searchInfo=" + searchInfo;
            axios.post(url).then(function (response) {
                // console.info(response.data)
                vue.$data.searchSongs = response.data;
                // console.info(vue.$data.searchSongs)
                //拼接字符串编号,用作数据库查询条件
                let allResultId = "";
                for (var i = 0; i < vue.$data.searchSongs.length; i++) {
                    allResultId += vue.$data.searchSongs[i].sid + ","
                }
                allResultId = allResultId.substring(0, allResultId.length - 1);
                if (allResultId != "") {
                    vue.JudgeLikeORNotInfoForSearch(allResultId);
                }
                $("#searchResult").text(searchInfo);
                $("#searchSongNum").text(response.data.length);

            });
        },
        JudgeLikeORNotInfoForSearch: function (allResultId) {
            console.info("登录用户id="+vue.$data.uid)
            if(vue.$data.uid == "" || vue.$data.uid < 1){
                return;
            }
            let url = "JudgeLikeORNotInfoForSearch" + "?allResultId=" + allResultId +"&userFavoriteList=" + vue.$data.userFavoriteList;
            axios.post(url).then(function (response) {
                // console.info(response.data);
                vue.$data.likeOrNotForSearch = response.data;
                for (let i = 0; i < vue.$data.searchSongs.length; i++) {
                    for (var j = 0; j < vue.$data.likeOrNotForSearch.length; j++) {
                        if (vue.$data.searchSongs[i].sid == vue.$data.likeOrNotForSearch[j]) {
                            vue.$data.searchSongs[i].likeOrNot = true;
                        }
                    }
                }
                vue.$nextTick(() => {
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
        getMusicListInfo: function (mlid) {  //获取歌单的信息
            var url = "getMusicListIfo" + "?mlid=" + mlid;
            axios.post(url).then(function (response) {
                // console.info(response.data);
                vue.$data.ListData = response.data;
                console.info(vue.$data.ListData)
            });
        },
        JudgeLikeORNotInfo: function (mlid) {
            console.info("登录用户id="+vue.$data.uid)
            if(vue.$data.uid == "" || vue.$data.uid < 1){
                return;
            }
            vue.$data.mlid = mlid;
            console.info(mlid);
            console.info(vue.$data.userFavoriteList);
            var url1 = this.uriJudgeLikeORNotInfo + "?mlid=" + mlid +"&userFavoriteList=" + vue.$data.userFavoriteList;
            axios.post(url1).then(function (response) {
                console.info(response.data)
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

        getRemoveableMusicList: function (mlid) {
            let url = "getRemoveableMusicList" + "?mlid=" + mlid +"&uid=" + vue.$data.uid;
            axios.post(url).then(function (response) {
                vue.$data.removeableMusicList = response.data;
                // console.info(vue.$data.removeableMusicList)

                vue.$nextTick(() => {
                    // choices();
                    showTrCSS();
                })

            })
        },
        hideMoveOutListDiv:function(){
            choiceLikeOrNot(111111111111111)
            $("removeLite").hide();
        }
    },
    mounted: function () {
        // vue.$data.searchSongs = [];
        // console.info(vue.$data.searchSongs);
    }

});
function collectThisList(){
    console.info(vue.$data.mlid);
    let url = "collectThisList" + "?uid=" + window.parent.uid + "&mlid=" + vue.$data.mlid;
        axios.post(url).then(function(response){
            console.info( "收藏操作"+response.status);
            if (response.status == 200) {
                vue.judgeListBelongOrnotInfo = false;
                window.parent.musicList.getUserCollectMusicList(window.parent.uid)
            } else if(response.status == 500){
                alert("操作失败")
            }
        })
}

function choiceLikeOrNot(obj, choice) {
    if(judgeLogin()){
        return;
    }
    // console.info(vue.$data.mlid);
    let sid = $(obj).parent().parent().attr("id");
    if (choice == 1) {  //添加喜欢方法
        let url = "addLike" + "?sid=" + sid + "&userFavoriteList=" + vue.$data.userFavoriteList;
        axios.post(url).then(function (response) {
            if (response.status == 200) {
                if (vue.$data.mlid == 0) {  //不是在歌单中的歌曲
                    vue.search(vue.$data.searchInfo)
                } else {  //实在歌单中得歌曲
                    vue.getMusicList(vue.$data.mlid);
                }
            } else if (response.status == 500) {
                alert("操作失败");
            }
        });
    } else if (choice == 0) { //取消喜欢方法
        let url1 = "cancelLike" + "?sid=" + sid + "&userFavoriteList=" + vue.$data.userFavoriteList;
        axios.post(url1).then(function (response) {
            // console.info(response.status);
            if (response.status == 200) {
                if (vue.$data.mlid == 0) {  //不是在歌单中的歌曲
                    vue.search(vue.$data.searchInfo)
                } else {  //实在歌单中得歌曲
                    vue.getMusicList(vue.$data.mlid);
                }
            } else if (response.status == 500) {
                alert("操作失败");
            }

        });
    }
}


function choices(obj) {
    vue.$data.choiceSid = $(obj).attr("id");    //将选择的歌单信息记录下来
    console.info(vue.$data.choiceSid)
    vue.getRemoveableMusicList(vue.$data.mlid)
    document.getElementById("songsInfo").onmouseup = function (e) {
        if (e.button === 2) {       //如果button=1（鼠标左键），button=2（鼠标右键），button=0（鼠标中间键）
            $(".showOptions").css("margin-top", (e.clientY - 245) + 'px');     //鼠标点击时给div定位Y轴
            $(".showOptions").css("margin-left", e.clientX + 'px');    //鼠标点击时给div定位X轴
            $(".showOptions").show();        //显示div盒子
        } else {
            $(".showOptions").hide();          //否则不显示div盒子
        }
    }
}

function choicesInSearch(obj) {
    vue.$data.choiceSid = $(obj).attr("id");    //将选择的歌单信息记录下来
    document.getElementById("songsInfo").onmouseup = function (e) {
        if (e.button === 2) {       //如果button=1（鼠标左键），button=2（鼠标右键），button=0（鼠标中间键）
            $(".showOptions").css("margin-top", (e.clientY - 100) + 'px');     //鼠标点击时给div定位Y轴
            $(".showOptions").css("margin-left", e.clientX + 'px');    //鼠标点击时给div定位X轴
            $(".showOptions").show();        //显示div盒子
        } else {
            $(".showOptions").hide();          //否则不显示div盒子
        }
    }
}

//点击“移动到歌单显示可移动的歌单信息”
function showRemoveableMusicList() {
    if(judgeLogin()){
        return;
    }
    let top = $(".showOptions").offset().top;
    let left = $(".showOptions").offset().left;
    $(".showMoveMusicList").css("margin-top", (top - 220) + 'px');     //鼠标点击时给div定位Y轴
    $(".showMoveMusicList").css("margin-left", (left + 120) + 'px');    //鼠标点击时给div定位X轴
    $(".showMoveMusicList").show();        //显示div盒子
}

//点击“移动到歌单显示可移动的歌单信息”
function showRemoveableMusicListInSearch() {
    if(judgeLogin()){
        return;
    }
    vue.getRemoveableMusicList(vue.$data.mlid)
    let top = $(".showOptions").offset().top;
    let left = $(".showOptions").offset().left;
    $(".showMoveMusicList").css("margin-top", (top - 100) + 'px');     //鼠标点击时给div定位Y轴
    $(".showMoveMusicList").css("margin-left", (left + 120) + 'px');    //鼠标点击时给div定位X轴
    $(".showMoveMusicList").show();        //显示div盒子
}

//将歌曲添加到选择歌单方法
function changeSongStatusInThisMusicList(obj, choice) {
    if(judgeLogin()){
        return;
    }
    //将歌曲添加到指定歌单操作
    if (choice == 1) {
        // console.info($(obj).attr("id").split("-")[1]);
        // console.info(vue.$data.choiceSid);
        let mlid = $(obj).attr("id").split("-")[1];
        let sid = vue.$data.choiceSid;
        let url = "addSongToMusicList" + "?mlid=" + mlid + "&sid=" + sid;
        axios.post(url).then(function (response) {
            console.info(response.status);
            if (response.status == 200) {
                vue.getMusicList(vue.$data.mlid);
            } else if (response.status == 500) {
                alert("操作失败");
            }
        });
    } else if (choice == 0) {
        let mlid = vue.$data.mlid;
        let sid = vue.$data.choiceSid;
        let url = "deleteSongToMusicList" + "?mlid=" + mlid + "&sid=" + sid;
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

function judgeLogin(){
    if(vue.$data.uid == "" || vue.$data.uid < 1){
        alert("请先登录11111");
        return 1;
    }else{
        return 0;
    }
}

window.getMusicList_app = vue;