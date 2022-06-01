<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../../../common/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>

<!-- 增加页面开始 -->
<div class="x_content">
    <form id="query" class="form-horizontal form-label-left" action="${ctx}/MarketStudentServlet.do?caozuo=update_gai"
          method="post" novalidate>
        <input type="hidden" name="id" value="${student.id}">
        <span class="section">修改市场类学生信息</span>
        <!-- 学生姓名 -->
        <div class="item form-group">
            <%--			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">班级名称--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name" >姓名
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="name" name="name" value="${student.name}" readonly
                       data-validate-linked="email" required
                       class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <!-- 市场类型 -->
        <div class="item form-group">
            <%--		  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">专业类型--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >市场类型
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" value="${student.stuType}" readonly class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <!-- 班级 -->
        <div class="item form-group">
            <%--		  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">专业类型--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >班级
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input  readonly type="text" value="${student.className}" class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <!-- 来自学校 -->
        <div class="item form-group">
            <%--			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">班级名称--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="fromSchool" >来自学校
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input readonly type="text" id="fromSchool" name="fromSchool" value="${student.fromSchool}"
                       data-validate-linked="email" required
                       class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <!-- 学历 -->
        <div class="item form-group">
            <%--		  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">专业类型--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12"  >学历
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input readonly type="text" value="${student.education}" class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <!-- 电话 -->
        <div class="item form-group">
            <%--			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">班级名称--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="phone" >电话
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input readonly type="text" id="phone" name="phone" value="${student.phone}"
                       data-validate-linked="email" required
                       class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <!-- QQ -->
        <div class="item form-group">
            <%--			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">班级名称--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="QQ" >QQ
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input readonly type="text" id="QQ" name="QQ" value="${student.qq}"
                       data-validate-linked="email" required
                       class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <!-- 性格特点 -->
        <div class="item form-group">
            <%--		  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">专业类型--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12">性格特点
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input readonly type="text" value="${student.xingge}" class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <!-- 备注 -->
        <div class="item form-group">
            <%--			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">班级名称--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="beizhu" >备注
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input readonly type="text" id="beizhu" name="beizhu" value="${student.beizhu}"
                       data-validate-linked="email" required
                       class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <!-- 培训意向 -->
        <div class="item form-group">
            <%--		  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">专业类型--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12">培训意向
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input readonly type="text" value="${student.willTrain}" class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <!-- 创建人 -->
        <div class="item form-group">
            <%--		  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">专业类型--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12">创建人
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input readonly type="text" value="${student.createAuthorId}" class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <!-- 创建时间 -->
        <div class="item form-group">
            <%--		  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">专业类型--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12">创建时间
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input readonly type="text" value="${student.createTime}" class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <!-- 修改人 -->
        <div class="item form-group">
            <%--		  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">专业类型--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12">修改人
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input readonly type="text" value="${student.modifyAuthor}" class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <!-- 修改时间 -->
        <div class="item form-group">
            <%--		  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">专业类型--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12">修改时间
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input readonly type="text" value="${student.modifyAuthorTime}" class="form-control col-md-7 col-xs-12">
            </div>
        </div>


        <div class="form-group">
            <div class="col-md-6 col-md-offset-3">
                <a href="${ctx}/MarketStudentServlet.do?caozuo=cha" id="sends" type="button"
                <%--				<a href="${ctx}/page/classes/classManager.jsp" id="sends" type="button"--%>
                   class="btn btn-success">返回</a>
            </div>
        </div>
    </form>
</div>
<!-- 增加页面结束 -->
<%@ include file="../../../common/footer.jsp"%>
<script src="${pageContext.request.contextPath}/statics/localjs/marketStudent.js"></script>
<%--<script src="${ctx }/statics/localjs/classYanZheng.js"></script>--%>
