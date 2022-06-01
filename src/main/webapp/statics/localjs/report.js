$(function () {
    //获取项目跟路径请求
    function getRealPath() {
        var curwwwPath = window.document.location.href;
        var pathName = window.document.location.pathname;
        var position = curwwwPath.indexOf(pathName);
        var localhostPath = curwwwPath.substring(0, position);
        var projectName = pathName.substring(0, pathName.substring(1).indexOf('/') + 1);
        var realPath = localhostPath + projectName;
        return realPath;
    }

    $("#Emajor").change(function () {
        var regionMajor = $("#Emajor").val();
        if (regionMajor != null && regionMajor != "") {
            $.ajax({
                // 提交方式，默认POST
                type: "POST",
                url: getRealPath() + "/classServlet", // 提交的服务器地址
                data: "param=class&major=" + regionMajor, // 提交到servlet的参数的值
                dataType: "text",// 提交到servlet后返回的结果的数据类型，text是纯文本类型
                success: function (data) {
                    var jsonArray = eval(data);
                    $("#Eclassname").html("");
                    var options = "<option value=''>---请选择---</option>";
                    $(jsonArray).each(function (i) {
                        options += "<option value='" + this.className + "'>" + this.className + "</option>"
                    })
                    $("#Eclassname").html(options);
                },
                error: function () {
                    alert('加载二级菜单失败');
                    location.href = "401.jsp"
                }
            });
        } else {
            $("#Eclassname").html("");
            var options = "<option value=''>---请选择---</option>";
            $("#Eclassname").html(options);
        }
    });

    $("#qMajor").change(function () {
        var regionMajor = $("#qMajor").val();
        if (regionMajor != null && regionMajor != "") {
            $.ajax({
                // 提交方式，默认POST
                type: "POST",
                url: getRealPath() + "/classServlet.do", // 提交的服务器地址
                data: "param=class&major=" + regionMajor, // 提交到servlet的参数的值
                dataType: "text",// 提交到servlet后返回的结果的数据类型，text是纯文本类型
                success: function (data) {
                    var jsonArray = eval(data);
                    $("#qClassname").html("");
                    var options = "<option value=''>---请选择---</option>";
                    $(jsonArray).each(function (i) {
                        options += "<option value='" + this.className + "'>" + this.className + "</option>"
                    })
                    $("#qClassname").html(options);
                },
                error: function () {
                    alert('加载二级菜单失败');
                    location.href = "401.jsp"
                }
            });
        } else {
            $("#qClassname").html("");
            var options = "<option value=''>---请选择---</option>";
            $("#qClassname").html(options);
        }
    });

    // 表单验证，不能为空
    $("#query").submit(function () {
        var flag = true;
        if (!checkNull("query")) {
            flag = false;
            alert("请输入要查询的专业和班级");
        }
        return flag;
    });
    $("#demo-form2").submit(function () {
        var flag = true;
        if (!checkNull("demo-form2")) {
            flag = false;
            alert("请选择需要导出的专业、班级和月份");
        }
        return flag;
    });
});

function checkNull(queryId){
    var flag = true;
    var formElements = document.getElementById(queryId).getElementsByClassName("require");
    // 只要有一项为空，flag即为false，阻止提交
    for (let i = 0; i < formElements.length; i++) {
        if (formElements[i].value == "") {
            flag = false;
            break;
        }
    }
    return flag;
}



