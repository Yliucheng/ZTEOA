<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exception-出现未知异常</title>
 
<style type="text/css">
 
.head404{ width:580px; height:234px; margin:50px auto 0 auto; background:url(https://www.daixiaorui.com/Public/images/head404.png) no-repeat; }
 
.txtbg404{ width:499px; height:169px; margin:10px auto 0 auto; background:url(https://www.daixiaorui.com/Public/images/txtbg404.png) no-repeat;}
 
.txtbg404 .txtbox{ width:390px; position:relative; top:30px; left:60px;color:#eee; font-size:13px;}
 
.txtbg404 .txtbox p {margin:5px 0; line-height:18px;}
 
.txtbg404 .txtbox .paddingbox { padding-top:15px;}
 
.txtbg404 .txtbox p a { color:#eee; text-decoration:none;}
 
.txtbg404 .txtbox p a:hover { color:#FC9D1D; text-decoration:underline;}
 
</style>
 
</head>
 
<body bgcolor="#494949">
 
   	<div class="head404"></div>
 
   	<div class="txtbg404">
 
  <div class="txtbox">
 
      <p>对不起，出现未知异常，请联系程序员解决！</p>
 
      <p class="paddingbox">请点击以下链接继续浏览网页</p>
 
      <p> >>>>>> <a href="${ctx }/jsp/main.jsp">返回网站首页</a></p>
 
    </div>
 
  </div>
 
</body>
</html>