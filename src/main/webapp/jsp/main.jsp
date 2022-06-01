<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../common/header.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>

<!-- /top navigation -->
<div class="right_col" role="main">
    <div class="">
        <div class="right_col" role="main">
            <h3 class="page-title">
                欢迎你：${ loginsession.logincode } | 登录中兴CRM系统
            </h3>
            <h4 class="page-title">目前还处于开发测试阶段...</h4>
        </div>
    </div>
</div>
<!-- /page content -->
<%@ include file="../common/footer.jsp" %>

