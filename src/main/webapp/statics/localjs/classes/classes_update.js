$(function () {
    let oldClassName = $("#classes").val();
    var submit=false;
    $("#query").submit(function () {
        if (submit){
            alert("修改成功！")
        }
        return submit;
    })
    $("#major").change(checkClassName)
    $("#classes").blur(checkClassName)
    function checkClassName(){
        var option = $("#major option:selected");//获取select选中的op对象
        var text = option.text();//专业名称
        $("div>span").html('');
        let val = option.val();//专业id
        let classname = $("#classes").val();//获取班级名称
        var number = classname.indexOf(text);//查看班级名称是否包含专业名称
        if (val==null||val==""){
            $("#classMes").html("请先选择专业类型！")
            submit = false;
            return false;
        }
        if (classname == ""){
            $("#classMes").html("请输入班级名称！")
            submit = false;
            return false;
        }
        let classNameJson = getClassesNameJson();//得到所有班级列表json数据
        let ff = false;
        $.each(classNameJson,function (index, value) {

            if (classname==value.className){
                let classId = $("[name='classid']").val();
                if (oldClassName==value.className&&classId!=""){
                    if (confirm("这是原班级名称，你不做修改吗？")){
                        ff=false;
                        return false;
                    }
                }
                $("#classMes").html("该班级已存在，请重新输入！")
                ff=true;
                return false;
            }
        })
        if (ff){
            submit = false;
            return false;
        }

        if (number>=0){
            let regele=/^\w{2,12}[\u4e00-\u9fa5]{1}$/
            if (!regele.test(classname)){
                $("#classMes").html("班级名称以’专业名称‘开头以’班‘结尾且不要含特殊字符，不要超过12字符！")
                submit = false;
                return false;
            }
            let lastword = classname.substring(classname.length-1,classname.length);
            if (lastword!="班"){
                $("#classMes").html("请在班级名称末尾加上'班'且不要有其他文字！")
                submit = false;
                return false;
            }else {
                let f = /\d/;
                let classNum = classname.substring(text.length,classname.length-1);
                if (!f.test(classNum)){
                    $("#classMes").html("请在班级名称中专业后面加上数字！")
                    submit = false;
                    return false;
                }
            }
            $("#classMes").html("班级名称可用！")
        } else {
            $("#classMes").html("班级名称请添加专业类型<b style='color: red;font-size: 16px'>"+text+"</b>！")
            submit = false;
            return false;
        }
        submit=true;
    }
})

//验证专业信息
function checkMajor() {
    var val = $("[name='major']").val();
    if (val==null||val==""){
        $("#majorMes").html("请选择专业类型！")
        return false;
    }
    return true;
}
// 得到所有班级信息
function getClassesNameJson(){
    var result = {};
    $.ajax({
        async: false,//同步才可以获取到值
        type: "get",
        url: "classServlet.do?param=chaClassNameJson",
        dataType: "json",
        success: function (json) {
            result = json;
        }
    })
    return result;
}