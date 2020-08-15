function getMlid(obj) {
    // console.info($(obj).val());
    // mlid = parseInt($(obj).val());
    let mlid = $(obj).attr("id");
    console.info($("#rightMain")[0]);
    $("#rightMain")[0].onload = function () {
        $("#rightMain")[0].contentWindow.getMusicList_app.getMusicList(mlid);
        $("#rightMain")[0].contentWindow.getMusicList_app.judgeListBelongOrnot(mlid);
    }
}
let musicList = new Vue({
    el: ".music-list",
    data: {
        uri: "getUserEstablishMusicList",
        uid:0,
        userEstablishMusicList: [],
        userCollectMusicList:[]
    },
    methods: {
        //获取用户id
        getUid:function(){
            $.post("getUid" , {} , response =>{
                musicList.$data.uid = response;
                window.uid = musicList.$data.uid
                console.info("登录用户id="+window.uid)
                musicList.getUserMusicList(musicList.$data.uid)
                musicList.getUserCollectMusicList(musicList.$data.uid);
            })
        },
        getUserMusicList:function(uid){
            let url = this.uri + "?uid=" + uid
            axios.post(url).then(function (response) {
                musicList.$data.userEstablishMusicList = response.data
            })
        },
        getUserCollectMusicList:function(uid){
            let url = "getUserCollectMusicList" + "?uid=" + uid
            axios.post(url).then(function (response) {
                musicList.$data.userCollectMusicList = response.data
            })

        }
    },
    mounted: function(){
        this.getUid();

    }
})
//显示或者隐藏用户音乐列表
function ShowOrHide(obj , choice){
    var status = $(obj).parent().parent().attr("class");
    if( "hide"==status){
        if(choice == 0){
            $("#ShowOrHideList>img").attr("src","images/showList.png")
            $("#MySonglist span").show();
            $("#MySonglist").attr("class" , "show");
        }else if(choice == 1){
            $("#ShowOrHideCollectList>img").attr("src","images/showList.png")
            $("#CollectSonglist span").show();
            $("#CollectSonglist").attr("class" , "show");
        }
    } else if("show"==status){
        if(choice == 0){
            $("#ShowOrHideList>img").attr("src","images/hideList.png")
            $("#MySonglist span").hide();
            $("#MySonglist").attr("class" , "hide");
        }else if(choice == 1){
            $("#ShowOrHideCollectList>img").attr("src","images/hideList.png")
            $("#CollectSonglist span").hide();
            $("#CollectSonglist").attr("class" , "hide");
        }
    }
}

//点击创建的歌单的加号显示隐藏的创建歌单面板
function showddListInfo(obj) {
    // $(".showOptions").css("margin-top", (event.clientY - 250) + 'px');     //鼠标点击时给div定位Y轴
    // $(".showOptions").css("margin-left", (event.clientX + 200) + 'px');    //鼠标点击时给div定位X轴
    $(".addListInfo").css("display", "block");        //显示div盒子
}
//当鼠标离开时则隐藏该元素
$(".addListInfo").hover(function () {
    $(this).show();
}, function () {
    $(this).hide();
})

 function sureAddMusicList(choiceSureOrCancel){
    if(choiceSureOrCancel==1){
        var newMusicListName = $("#setListName").val();
        var url2 = "addMusicList" + "?newMusicListName=" +newMusicListName
        axios.post(url2).then(function(response){
            console.info(response.status);
            //成功添加
            if(response.status == 200){
                musicList.getUserMusicList(1);
            } else if(response == 500){

            }
        })
    }else{
        $(".addListInfo").hide();
    }
 }

window.musicList = musicList
