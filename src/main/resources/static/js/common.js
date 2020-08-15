let userFavoriteList=0;
function getUserFavoriteList(){
    $.post("getUserFavoriteList",{},response=>{
        if(window.uid == "" || uid <0 || uid == undefined){
            window.userFavoriteList = 0;
            console.info("用户最喜欢的mlid=  当前无用户")
        } else {
            console.info(response[0].mlid)
            userFavoriteList = response[0].mlid
            window.userFavoriteList = userFavoriteList;
            console.info("用户最喜欢的mlid="+window.userFavoriteList)
        }

    })
}




