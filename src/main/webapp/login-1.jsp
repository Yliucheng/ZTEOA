<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="ZTE.utils.Constant" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="error" value="请先登录！"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理-登陆</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="lib/layui-v2.5.5/css/layui.css" media="all">
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        html, body {width: 100%;height: 100%;overflow: hidden}
        body {background: #1E9FFF;}
        body:after {content:'';background-repeat:no-repeat;background-size:cover;-webkit-filter:blur(3px);-moz-filter:blur(3px);-o-filter:blur(3px);-ms-filter:blur(3px);filter:blur(3px);position:absolute;top:0;left:0;right:0;bottom:0;z-index:-1;}
        .layui-container {width: 100%;height: 100%;overflow: hidden}
        .admin-login-background {width:360px;height:300px;position:absolute;left:50%;top:40%;margin-left:-180px;margin-top:-100px;}
        .logo-title {text-align:center;letter-spacing:2px;padding:14px 0;}
        .logo-title h1 {color:#1E9FFF;font-size:25px;font-weight:bold;}
        .login-form {background-color:#fff;border:1px solid #fff;border-radius:3px;padding:14px 20px;box-shadow:0 0 8px #eeeeee;}
        .login-form .layui-form-item {position:relative;}
        .login-form .layui-form-item label {position:absolute;left:1px;top:1px;width:38px;line-height:36px;text-align:center;color:#d2d2d2;}
        .login-form .layui-form-item input {padding-left:36px;padding-right: 36px}
        .captcha {width:60%;display:inline-block;}
        .captcha-img {display:inline-block;width:34%;float:right;}
        .captcha-img img {height:34px;border:1px solid #e6e6e6;height:36px;width:100%;}
        i.layui-icon-password {position:absolute;top: 10px;right:14px;}
        i.layui-icon-password:hover{
            background-color: lightgray;
            /* 鼠标手势出现箭头和问号 */
            cursor:help;
            /* 鼠标箭头出现一只手 */
            /*cursor:pointer;*/
        }
        .layui-icon-password:before{content:"\e673"}
    </style>
</head>
<body>
<%
    // 用户名
    String user = "";
    // 登录密码
    String pass = "";
    // 复选框是否选中
    String checked = "";
    // 获取的是请求里的所有cookie组成的数组
    Cookie[] cookies = request.getCookies();
    // 如果cookies数组不为空对其进行遍历
    if (cookies != null && cookies.length > 0) {
        // 循环遍历Cookie
        for (int i = 0; i < cookies.length; i++) {
            // 获取Cookie对象
            Cookie cookie = cookies[i];
            // 将创建的cookie名与获取的cookie数组中已经存在的cookie名进行比较
            if (Constant.LOGIN_NAME.equals(cookie.getName())) {
                // 获取名字叫做"name"的cookie的值
                user = cookie.getValue();

            }
            // 将创建的cookie名与获取的cookie数组中已经存在的cookie名进行比较
            if (Constant.LOGIN_PASSWORD.equals(cookie.getName())) {
                // 获取名字叫做"password"的cookie的值
                pass = cookie.getValue();
            }
            // 将创建的cookie名与获取的cookie数组中已经存在的cookie名进行比较
            if (Constant.LOGIN_CHECK.equals(cookie.getName())) {
                // 获取名字叫做"check"的cookie的值
                // 将“记住账号密码”设置为勾选
                checked = cookie.getValue();
            }

        }
    }
%>
<div class="layui-container">
    <div class="admin-login-background">
        <div class="layui-form login-form">
            <form class="layui-form" action="login" method="post">
                <div class="layui-form-item logo-title">
                    <h1>中兴CRM客户关系系统</h1>
                    <span>
                        <c:if test="${requestScope.error==null}">
                            <b style="color: red;font-weight: bolder">${error}</b>
                        </c:if>
                        <b style="color: red;font-weight: bolder">${requestScope.error}</b>
                    </span>
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-username" for="username"></label>
                    <input type="text" name="username" id="username" lay-verify="required|account" placeholder="用户名或者邮箱" autocomplete="off" class="layui-input" value="<%=user%>" maxlength="18">
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-password" for="password"></label>
                    <i class="layui-icon layui-icon-password admin-icon"></i>
                    <input type="password" name="password" id="password" lay-verify="required|password" placeholder="密码,不要使用空格和回车" autocomplete="off" class="layui-input" value="<%=pass%>" maxlength="18">
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-vercode" for="captcha"></label>
                    <input type="text" name="captcha" id="captcha" lay-verify="required|captcha" placeholder="图形验证码" autocomplete="off" class="layui-input verification captcha" value="" maxlength="6">
                    <div class="captcha-img">
                        <img id="captchaPic" src="images/captcha.jpg">
                    </div>
                </div>
                <div class="layui-form-item">
                    <input type="checkbox" name="rememberMe" lay-skin="primary" value="checked" title="记住账号密码" checked="<%=checked%>">

                </div>
                <div class="layui-form-item">
                    <button class="layui-btn layui-btn layui-btn-normal layui-btn-fluid" lay-submit="" lay-filter="login">登 入</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="lib/jquery-3.4.1/jquery-3.4.1.min.js" charset="utf-8"></script>
<script src="lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="lib/jq-module/jquery.particleground.min.js" charset="utf-8"></script>
<script>
    $(function () {
        $("#username").select();
        $(".layui-icon-password").click(function () {
            let arr = $("#password").attr("type");
            if (arr=="password")
                $("#password").prop("type","text");
            if (arr=="text")
                $("#password").prop("type","password");
        })
    })
    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer;

        // 登录过期的时候，跳出ifram框架
        if (top.location != self.location) top.location = self.location;

        // 粒子线条背景
        $(document).ready(function(){
            $('.layui-container').particleground({
                dotColor:'#7ec7fd',
                lineColor:'#7ec7fd'
            });
        });

        // 进行登录操作
        form.on('submit(login)', function (data) {
            data = data.field;
            if (data.username == '') {
                layer.msg('用户名不能为空');
                $("#username").select();
                return false;
            }
            if (!checkName(data.username)){
                layer.msg('姓名验证失败！');
                $("#username").select();
                return false;
            }
            if (data.password == '') {
                layer.msg('密码不能为空');
                $("#password").select();
                return false;
            }
            if (!checkUserpwd(data.password)){
                layer.msg('密码不符合要求，请重新输入,不要包含空格和回车符，长度不得少于5，不得长于8！');
                $("#password").select();
                return false;
            }
            if (data.captcha == '') {
                layer.msg('验证码不能为空');
                $("#captcha").select();
                return false;
            }
            if (data.captcha != 'xszg') {
                layer.msg('验证码错误');
                $("#captcha").select();
                return false;
            }

            layer.msg('正在登录');
            return true;
        });
    });
    function checkName(name){
        let regele=/^[\u4e00-\u9fa5\w]{1,5}$/
        if(!regele.test(name)){
            return false;
        }
        return  true;
    }
    function checkUserpwd(userpwd) {
        var reg=/^\s*\S+\s*$/
        let flag = reg.test(userpwd);//如果字符串不包含空格则为true
        if (!flag||userpwd.length<5||userpwd.length>8){
            return false;
        }
        return true;
    }
</script>
</body>
</html>