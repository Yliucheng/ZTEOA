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

    $("#normal,#late,#absent,#excused").click(function () {
        obj = document.getElementsByName("studentNo");
        //学号
        check_studentNo = [];
        for (k in obj) {
            if (obj[k].checked) {
                check_studentNo.push(obj[k].value);
            }
        }
        var classname2 = $("#Eclassname").val();
        var major2 = $("#Emajor").val();
        var result = $(this).val();

        //alert(check_studentNo);
        $.ajax({
            // 提交方式，默认GET
            type: "POST",
            url: "attServlet", // 提交的服务器地址
            //traditional: true, //传入数组，赋值true！！
            data: {
                "operate": "input",
                "studentNoData": check_studentNo,
                "result": result
            }, // 提交到servlet的参数的值
            dataType: "text",
            success: function (data) {
                if (data == "success") {
                    var to = 'attServlet';
                    var params = {
                        'operate': 'cha',
                        'className': classname2,
                        'major': major2
                    }
                    alert("录入考勤成功！");
                    doPost(to, params);
                } else {
                    alert('录入考勤失败');
                }
            },
            error: function () {
                alert('录入考勤失败');
                //location.href = "401.jsp"
            }
        });
    });


    $("#toSubmit").on("click",function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: getRealPath() + "/attServlet",
            data: {
                'operate': 'change',
                'id': $("[name=id]").val(),
                'attDate': $("[name=attDate]").val(),
                'attStatus': $("[name=attStatus]").val()
            },
            success: function (data) {
                if (data == 'success') {
                    var to = getRealPath() + '/attServlet';
                    var params = {
                        'operate': 'get',
                        'className': $("[name=className]").val(),
                        'namec': $("[name=namec]").val(),
                        'date': $("[name=date]").val(),
                        'ckStatu': $("[name=ckStatu]").val(),
                        'major': $("[name=major]").val()
                    }
                    alert("修改成功");
                    doPost(to, params);
                } else {
                    alert("修改失败");
                }
            }
        })
    });

    $(".toDel").on("click",function (event) {
        event.preventDefault();
        var id = $(this).parents("td").siblings(":first").text();
        $.ajax({
            type: 'POST',
            url: getRealPath() + "/attServlet",
            data: {
                'operate': 'del',
                'id': id
            },
            success: function (data) {
                if (data == 'success') {
                    var to = getRealPath() + '/attServlet';
                    var params = {
                        'operate': 'get',
                        'className': $("[name=className]").val(),
                        'namec': $("[name=namec]").val(),
                        'date': $("[name=date]").val(),
                        'ckStatu': $("[name=ckStatu]").val(),
                        'major': $("[name=major]").val()
                    }
                    alert("删除成功");
                    doPost(to, params);
                } else {
                    alert("删除失败");
                }
            }
        })
    });

    $("#shangchuan").on("click",function (event) {
        event.preventDefault();
        $("#excelFile").click();
    });

    $("#excelFile").change(function () {
        $("#toUpload").click();
    });

    $("#selectall").click(function () {
        // 判断全选这个按钮，有没有被选中
        if ($(this).is(":checked")) {
            // 选中了，就把[name='checkBox']3个复选框 依次加上选中属性
            $("[name=studentNo]").each(function () {
                // prop是设置属性 attribute是添加属性 移除属性是removeattribute
                $(this).prop("checked", true);
                $("#selectall").prop("checked", true);
            });
        } else {
            // 没选中，就把false赋给3个复选框
            $("[name=studentNo]").each(function () {
                $(this).prop("checked", false);
                $("#selectall").prop("checked", false);
            });
        }
    });

//全选(有一个单选未选上，取消全选)

    $("[name=studentNo]").click(function () {
        var obj = document.getElementsByName("studentNo");
        for (var i = 0; i < obj.length; i++) {
            if ($(obj[i]).is(":checked")) {
                $("#selectall").each(function () {
                    $(this).prop("checked", true);
                });
            } else {
                $("#selectall").each(function () {
                    $(this).prop("checked", false);
                });
                break;
            }
        }
    });
});

function doPost(to, p) {// to:提交地址 p:参数
    var myForm = document.createElement("form");
    myForm.method = "post";
    myForm.action = to;
    for (var i in p) {
        var myInput = document.createElement("input");
        myInput.setAttribute("name", i);// 为input对象设置name
        myInput.setAttribute("value", p[i]);// 为input对象value
        myForm.appendChild(myInput);
    }
    document.body.appendChild(myForm);
    myForm.submit();
    document.body.removeChild(myForm);// 提交后移除创建的form
}
