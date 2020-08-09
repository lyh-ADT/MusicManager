var vue = new Vue({
    el:"#vue-app",
    data:{
        uri:'showMusicListInfo',
        data:[]
    },
    methods:{
        getMusicList:function(mlid){
            var url = this.uri + "?mlid=" + mlid;
            axios.post(url).then(function(response){
                console.info(response.data)
                vue.$data.data = response.data;
                vue.$nextTick(function(){
                    document.getElementById("songsInfo").onmouseup = function(e){
                        if(e.button===2){       //如果button=1（鼠标左键），button=2（鼠标右键），button=0（鼠标中间键）
                            // console.log(e);     //将传进去的参数打印出来
                            // console.log(e.offsetY);     //打印出鼠标点击的Y轴坐标
                            // console.log(e.offsetX);     //打印出鼠标点击的X轴坐标
                            $(".showOptions").css("margin-top",(e.clientY-250)+'px');     //鼠标点击时给div定位Y轴
                            $(".showOptions").css("margin-left",e.clientX+'px');    //鼠标点击时给div定位X轴
                            $(".showOptions").css("display" , "block");        //显示div盒子
                        }else{
                            $(".showOptions").css("display" , "none");           //否则不显示div盒子
                        }
                    }
                })
            });
        }
    },

});

window.getMusicList_app = vue;