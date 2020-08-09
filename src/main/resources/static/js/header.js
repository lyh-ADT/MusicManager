//搜索框回车事件
$("#searchInfo").keydown(function (e) {
    var searchInfo = $("#searchInfo").val().toString();
    if (searchInfo != "" && e.keyCode == "13") {
        showpage(0, "songs.html")
        $("#rightMain").on("load", function () {
            $("#rightMain")[0].contentWindow.search_app.search(searchInfo);
        })
    }
})



