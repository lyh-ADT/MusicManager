
var vue = new Vue({
    el:"#vue-app",
    data:{
        uri:'showMusicListInfo',
        uriJudgeLikeORNotInfo:'judgeLikeOrNot',
        ListData:[],
        songsData:[],
        likeOrNot:[],
        mild: 0
    },
    methods:{
        getMusicList:function(mlid){  //获取歌单中的歌曲信息
            vue.getMusicListInfo(mlid);
            // vue.$data.mlid = mlid;
            var url = this.uri + "?mlid=" + mlid;
            axios.post(url).then(function(response){
                // console.info(response.data)
                vue.JudgeLikeORNotInfo(mlid);
                vue.$data.songsData = response.data;
                vue.$nextTick(() =>{
                    choices();
                    showTrCSS();
                })
            });
        },
        getMusicListInfo:function(mlid){  //获取歌单的信息
            var url= "getMusicListIfo" +"?mlid=" + mlid;
            axios.post(url).then(function(response){
                console.info(response.data);
                vue.$data.ListData = response.data;
            });
        },
        JudgeLikeORNotInfo:function(mlid){
            vue.$data.mild = mlid;
            var url1 = this.uriJudgeLikeORNotInfo + "?mlid=" + mlid;
            axios.post(url1).then(function(response){
                // console.info(response.data)
                vue.$data.likeOrNot = response.data;
                for (var i = 0 ; i < vue.$data.songsData.length ; i++){
                    for(var j = 0 ; j < vue.$data.likeOrNot.length ; j++){
                        if( vue.$data.songsData[i].sid ==  vue.$data.likeOrNot[j]){
                            vue.$data.songsData[i].likeOrNot = true;
                        }
                    }
                }
                vue.$nextTick(() =>{
                    choices();
                    showTrCSS();
                })
            });
        },
        play:(sid)=>{
            window.parent.musicPlayer.play(sid, parseInt(vue.$data.mild));
        }
    },
    mounted:function(){
    }

});


function choiceLikeOrNot(obj , choice){
    console.info(vue.$data.mild);
    let sid = $(obj).parent().parent().attr("id");
    if(choice == 1){  //添加喜欢方法
        let url = "addLike" + "?sid=" + sid;
        axios.post(url).then(function(response){
            if(response.status ==200){
                vue.getMusicList(vue.$data.mild);
            } else if(response.status == 500){
                alert("操作失败");
            }
        });
    } else if(choice == 0){ //取消喜欢方法
        let url1 = "cancelLike" + "?sid=" + sid;
        axios.post(url1).then(function(response){
            console.info(response.status);
            if(response.status ==200){
                vue.getMusicList(vue.$data.mild);
            } else if(response.status == 500){
                alert("操作失败");
            }

        });
    }
}


function choices(){
    // console.info(vue.$data.data)
        document.getElementById("songsInfo").onmouseup = function(e){
            if(e.button===2){       //如果button=1（鼠标左键），button=2（鼠标右键），button=0（鼠标中间键）
                // console.log(e);     //将传进去的参数打印出来
                // console.log(e.offsetY);     //打印出鼠标点击的Y轴坐标
                // console.log(e.offsetX);     //打印出鼠标点击的X轴坐标
                $(".showOptions").css("margin-top",(e.clientY-250)+'px');     //鼠标点击时给div定位Y轴
                $(".showOptions").css("margin-left",e.clientX+'px');    //鼠标点击时给div定位X轴
                $(".showOptions").show();        //显示div盒子
            }else{
                $(".showOptions").hide();          //否则不显示div盒子
            }
    }
}

$(".showOptions").hover(function () {
    $(".showOptions").show();
}, function () {
    $(".showOptions").hide();
})

function showTrCSS(){
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