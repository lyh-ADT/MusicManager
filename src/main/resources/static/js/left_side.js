var mlid=1;
function getMlid(obj) {
    // console.info($(obj).val());
    // mlid = parseInt($(obj).val());
    // console.info(mlid);
    $("#rightMain").on("load", function () {
        $("#rightMain")[0].contentWindow.getMusicList_app.getMusicList(mlid);
    })
}