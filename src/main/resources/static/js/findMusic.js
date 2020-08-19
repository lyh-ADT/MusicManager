function getRcommecdMlid(obj) {
    let mlid = $(obj).attr("id");
   // console.info(window.parent.$("#rightMain")[0]);
    window.parent.getOnLoad(mlid);
    window.parent.showpage(0,"MusicList_detail.html")
}


let vue_findMusicListInfo = new Vue({
    el:"#vue_findMusicInfo",
    data:{
        findMusicList:[],
        recommendList:[{date:"",description:"",imgurl:"",mlid:"",mlname: "",uid:""}],
        personalizedList:[{date:"",description:"",imgurl:"",mlid:"",mlname: "",uid:""}]
    },
    methods:{
        getRecommedMusicList:function(){
            axios.post("getRecommedMusicList").then(function(response){
                // console.info(response.data);
                vue_findMusicListInfo.$data.findMusicList = response.data;
                //取十个歌单 ， 将取到的歌单均分到推荐歌单 、 个性化歌单
                for(let i = 0 ; i < 5 ;i++){
                    vue_findMusicListInfo.$data.recommendList[i] = vue_findMusicListInfo.$data.findMusicList[i]
                    vue_findMusicListInfo.$data.personalizedList[i] = vue_findMusicListInfo.$data.findMusicList[i+5]
                }
                // console.info(vue_findMusicListInfo.$data.recommendList);
                // console.info(vue_findMusicListInfo.$data.personalizedList);
                vue_findMusicListInfo.$forceUpdate();
            });
        }

    },
    mounted:function(){
        this.getRecommedMusicList();
    }
})

