function getMlid(obj) {
    // console.info($(obj).val());
    // mlid = parseInt($(obj).val());
    let mlid = $(obj).attr("id");
    $("#rightMain")[0].onload = function () {
        $("#rightMain")[0].contentWindow.getMusicList_app.getMusicList(mlid);
    }
}


let musicList = new Vue({
    el: ".music-list",
    data: {
        uri: "getUserEstablishMusicList",
        uid:1,
        userEstablishMusicList: [],
    },
    methods: {
        getUserMusicList:function(uid){
            let url = this.uri + "?uid=" + uid
            axios.post(url).then(function (response) {
                // console.info(response);
                musicList.$data.userEstablishMusicList = response.data
            })
        }
    },
    mounted: function (uid) {
        this.getUserMusicList(this.uid);
    }
})

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