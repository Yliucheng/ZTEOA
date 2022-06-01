$(function () {
    function getRealPath() {
        var curwwwPath = window.document.location.href;
        var pathName = window.document.location.pathname;
        var position = curwwwPath.indexOf(pathName);
        var localhostPath = curwwwPath.substring(0, position);
        var projectName = pathName.substring(0, pathName.substring(1).indexOf('/') + 1);
        var realPath = localhostPath + projectName;
        return realPath;
    }
    //级联
    $("[name='zhuanye']").change(function () {
        var zhuanye = $("[name='zhuanye']").val();
        var params={
            param:"getBanjiList",
            pas:zhuanye
        }
        $.getJSON("studentServlet",params,function (json) {
            $("[name='banji']").html('');
            $("[name='banji']").html("<option value=''>--请选择--</option>")
            $.each(json,function (index, banji) {
                var op = "<option value ='"+banji.classId+"'>"+banji.className+"</option>"
                $("[name='banji']").append(op);
            })
        })
    })

    $("#shangchuan").on("click",function (event) {
        event.preventDefault();
        $("#excelFile").click();
    });

    $("#excelFile").change(function () {
        $("#toSubmit").click();
    })
})
