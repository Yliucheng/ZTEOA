<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../../common/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!-- appInfoList页面开始 -->
<script src="${ctx}/js/jquery-3.5.1.js" type="text/javascript"></script>
<div class="row">
    <div style="margin:0 auto; padding-top: 100px">
        <h1 style="text-align: center; font-size: 40px; font-weight: bold; color: #ed1c24; font-family: '楷体', '微软雅黑'">
            您的excel格式错误！请检查后重试！
        </h1>
        <form data-parsley-validate id="reback-Excel" method="post" style="margin:0 auto; text-align: center; padding-top: 50px;"
              action="${ctx }/workrecordservlet">
            <input type="hidden" name="major" value="${param.major}">
            <input type="hidden" name="classname" value="${param.classname}">
            <label style="text-align: center; font-size: 20px; font-weight: bold; font-family: '楷体', '微软雅黑'"><span id="second">7
                秒</span><span>后自动返回录入页面</span></label>
            <button type="submit" id="reback" class="btn btn-primary"
                    class="btn btn-success" style="display: inline; font-size: 20px; font-weight: normal; font-family:
                    '楷体', '微软雅黑'">立即返回录入页面
            </button>
            <button type="button" id="goMainPage" class="btn btn-primary"
                    class="btn btn-success" style="display: inline; font-size: 20px; font-weight: normal; font-family:
                    '楷体', '微软雅黑'">返回主页面
            </button>
        </form>
    </div>
</div>

<!-- appInfoList页面结束 -->
<!-- /page content -->
<%@ include file="../../common/footer.jsp" %>
<script src="${ctx}/statics/localjs/excelError.js"></script>


