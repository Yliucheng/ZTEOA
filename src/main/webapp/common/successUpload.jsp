<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="header.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        .txtbox {
            width: 400px;
            margin: 100px auto 0px;
            text-align: center;
        }

        p {
            margin-top: 20px;
            font-size: 28px;
        }

        a {
            color: #0C0C0C;
        }

        a:hover {
            color: #FC9D1D;
        }
    </style>
<div class="txtbox">
    <img src="https://img-blog.csdnimg.cn/20200618162857587.png" width="280px">

    <p>文件上传成功</p>

    <p>倒计时结束即将<a href="${ctx }/${servletName}">返回原页面</a></p>
    <h1 id="Meg"></h1>
    <input type="hidden" id="toLocation" value="${servletName}">
</div>
<script type="text/javascript">
    var num=5;
    myVar=setInterval(function () {
        if (num==0){
            clearInterval(myVar);
            jump()
        }
        document.getElementById("Meg").innerHTML=""+num;
        num--;
    },1000)
    var url = document.getElementById("toLocation").value;
    function jump(){
        window.location.href = '${ctx }/'+url;
    }

    // setTimeout(jump,1500);
</script>
<%@ include file="footer.jsp" %>