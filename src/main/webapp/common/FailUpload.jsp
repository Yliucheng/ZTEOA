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
    <img src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg95.699pic.com%2Fxsj%2F0m%2Fkm%2Fxl.jpg%21%2Ffw%2F700%2Fwatermark%2Furl%2FL3hzai93YXRlcl9kZXRhaWwyLnBuZw%2Falign%2Fsoutheast&refer=http%3A%2F%2Fimg95.699pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1656603041&t=00bcd5791c405ac5d27b88cf70061dfd" width="280px" title="上传失败图片" alt="上传失败！">

    <p><span style="color: red;font-weight: bolder;font-size: 50px">Fail</span>文件上传失败</p>

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