$(function () {
    $("#Emajor").change(function () {
        var regionMajor = $("#Emajor").val();
        //var classname2=$("#classname").val();
        // alert("id:"+regionParentId);
        if (regionMajor != null && regionMajor !== "") {
            $.ajax({
                // 提交方式，默认GET
                type: "POST",
                url: "getBanJi", // 提交的服务器地址
                data: "major=" + regionMajor, // 提交到servlet的参数的值
                dataType: "json",// 提交到servlet后返回的结果的数据类型，text是纯文本类型
                success: function (data) {
                    $("#Eclassname").html("");
                    var options = "<option value=''>---请选择---</option>";
                    for (var i = 0; i < data.length; i++) {
                        options += "<option value=" + data[i].classname + ">"
                            + data[i].classname + "</option>"
                    }
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

    // 表单验证，不能为空
    $("#demo-form2").submit(function () {
        var flag = true;
        if (!checkNull("demo-form2")) {
            flag = false;
            alert("必须选择作业月份");
        }
        return flag;
    });
});

function checkNull(queryId) {
    var flag = true;
    var formElements = document.getElementById(queryId).getElementsByClassName("require");
    // 只要有一项为空，flag即为false，阻止提交
    for (let i = 0; i < formElements.length; i++) {
        if (formElements[i].value === "") {
            flag = false;
            break;
        }
    }
    return flag;
}
