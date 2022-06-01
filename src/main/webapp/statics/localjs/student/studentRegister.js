$(function () {
    //姓名转拼音
    $("#name").blur(function () {
        var name = $("#name").val();
        var params={
            param:"pinyin",
            name:name
        }
        $.get("studentServlet",params,function (json) {
            if (json!=null&&json!=""){
                var str = json.substring(1,json.length-1);
                $("#logincode").val(str);
            }
        })
    })
})