function getMlid(obj) {
    // console.info($(obj).val());
    // mlid = parseInt($(obj).val());
    let mlid=$(obj).attr("id");
    $("#rightMain")[0].onload=function() {
        $("#rightMain")[0].contentWindow.getMusicList_app.getMusicList(mlid);
    }
}
var uid=1;
let musicList = new Vue({
    el:".music-list",
    data:{
        uri:"getUserEstablishMusicList",
        userEstablishMusicList:[],
    },
    method:{

    },
    mounted:function (uid) {
        let url = this.uri + "?uid=" + 1
        axios.post(url).then(function(response) {
            console.info(response);
            musicList.$data.userEstablishMusicList = response.data
        })
    }
})
