$(function () {
    //判断验证的标志flag
    var flag = false;
    var doUpdateFlag=false;
    var oldLoginCode=$("#logincode").val();
    $("#query").submit(function () {
        let caozuo = $("[name='param']").val();

        var logincode = $("#logincode").val();//登录账号
        var param = $("[name='param']").val();

        if (flag){
            if (caozuo=="add"){
                alert("添加成功！")
            }else if (caozuo=="update"){
                alert("更新成功")
            } else {
                alert("注册成功")
            }
        }else {
            if (param=="register"&&!doUpdateFlag){
                alert("请输入注册信息内容！")
                return false;
            }
            if (caozuo=="update"&&!doUpdateFlag){
                if (confirm("不做改动吗？")){
                    flag=true;
                }
            }
        }
        return flag;
    })
    $("#name,#classes,#occupation,#education,#logincode,#userpwd").blur(function () {
        doUpdateFlag=true;
        var name = $("#name").val();//姓名
        var classes =$("#classes").val();//班级*
        var fromschool = $("#occupation").val();//来自院校
        var education =$("#education").val();//学历*
        var logincode = $("#logincode").val();//登录账号
        var userpwd = $("#userpwd").val();//密码
        var pinyin = GetReturnValue();//姓名拼音
        var loginCodeJson = getLoginCodeJson();//姓名拼音
        $("div>span").html('');
        if(!checkName(name)){
            $("#name").select();
            flag=false;
            return false;
        }
        if (!checkClasses(classes)){
            $("#classes").select();
            flag=false;
            return false;
        }
        if (!checkFromSchools(fromschool)){
            $("#occupation").select()
            flag=false;
            return false;
        }
        if (!checkEducation(education)){
            $("#education").select()
            flag=false;
            return false;
        }
        if (!checkLogincode(logincode)){
            $("#logincode").select()
            flag=false;
            return false;
        }
        let pin = true;
        if (pinyin!=logincode){
            if (!confirm("当前输入的登录账号不是您的姓名拼音，确认使用吗？")){
                $("#logincodeMes").text("请重新输入登录账号");
                pin=false;
            }
        }
        if (!pin){
            flag=false;
            return false;
        }
        let loginCodeFlag=true;
        $.each(loginCodeJson,function (index, value) {
            if (logincode==value){
                if (oldLoginCode==value){
                    if (!confirm("继续使用原来的登录账号吗？")){
                        $("#logincodeMes").text("请重新输入登录账号");
                        loginCodeFlag=false;
                    }
                }else {
                    $("#logincodeMes").text("此登录账号已被使用！");
                    loginCodeFlag=false;
                }
            }
        })
        if (!loginCodeFlag){
            flag=false;
            return false;
        }
        if (!checkUserpwd(userpwd)){
            $("#userpwd").select()
            flag=false;
            return false;
        }
        flag = true;
    })
})


function checkUserpwd(userpwd) {
    // 如果字符串包含空格返回false 不包含空格则返回true
    var reg=/^\s*\S+\s*$/
    if (userpwd==""){
        $("#pwdMes").text("密码不能为空！");
        return false;
    }else if (!reg.test(userpwd)){
        $("#pwdMes").text("密码不符合要求，请不要填写空格和回车符。请重新输入！");
        return false;
    }else if (userpwd.length<5||userpwd.length>8){
        $("#pwdMes").text("密码长度不符合要求。请重新输入！");
        return false;
    }
    return true;
}

function checkLogincode(logincode) {
    var reg=/^[\w]{4,12}$/
    if (logincode==""){
        $("#logincodeMes").text("登录账号不能为空");
        return false;
    }else if (!reg.test(logincode)){
        $("#logincodeMes").text("登录账号含有特殊字符或者长度不匹配！");
        return false;
    }
    return true;
}
function checkEducation(education) {
    if(education==""){
        $("#xueliMes").text("请选择学历！");
        return false;
    }
    return true;
}

function checkFromSchools(fromschool) {
    let reg=/^[\u4e00-\u9fa5_a-zA-Z]{4,20}$/
    if (fromschool==""){
        $("#yuanxiaoMes").text("请填写院校名称！")
        return false
    }else if (!reg.test(fromschool)){
        $("#yuanxiaoMes").text("院校验证失败！")
        return false
    }
    return true;
}
function checkClasses(classes) {
    if(classes==""){
        $("#classMes").text("请选择班级！");
        return false;
    }
    return true;
}
function checkName(name){
    let regele=/^[\u4e00-\u9fa5_a-zA-Z]{2,5}$/
    if(name==""){
        $("#nameMes").text("姓名不能为空！");
        return false;
    }else if(!regele.test(name)){
        $("#nameMes").text("姓名验证失败！");
        return false;
    }
    return  true;
}
// 得到姓名拼音
function GetReturnValue() {
    var result = "";
    var name = $("#name").val();
    $.ajax({
        async: false,//同步才可以获取到值
        type: "get",
        url: "studentServlet?param=pinyin",
        data: "name="+name,
        dataType: "text",
        success: function (json) {
            json = json.substring(1,json.length-1);
            result = json;
        }
    })
    return result;
}
// 得到所有用户名
function getLoginCodeJson() {
    var result = "";
    var name = $("#name").val();
    $.ajax({
        async: false,//同步才可以获取到值
        type: "get",
        url: "studentServlet?param=getLoginCode",
        dataType: "json",
        success: function (json) {
            result = json;
        }
    })
    return result;
}
