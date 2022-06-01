
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<c:set var="studyTypeAll" value="${sessionScope.studyTypeAll}"></c:set>
<!-- 增加页面开始 -->
<div class="x_content">
	<form id="query" class="form-horizontal form-label-left" action="${ctx}/classServlet.do?param=${requestScope.params}"
		method="post" novalidate>
		<span class="section">班级信息 页面</span>
		<input type="hidden" name="classid" value="${requestScope.classes.classId}">
		<div class="item form-group">
			<%--			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">班级名称--%>
			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="classes" >班级名称
				<span class="required">*</span>
			</label>

			<div class="col-md-4 col-sm-6 col-xs-12">
				<input type="text" id="classes" name="classname"
					   data-validate-linked="email" required
					   class="form-control col-md-7 col-xs-12" value="${requestScope.classes.className}" onblur="check(this)">

			</div>
			<span id="classMes"></span>
		</div>
		<div class="item form-group">
<%--		  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">专业类型--%>
		  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="major" >专业类型
				<span class="required">*</span>
			</label>
			<div class="col-md-4 col-sm-6 col-xs-12">
				<select  name="studytype" value="" id="major"
					class="form-control col-md-7 col-xs-12">
					<option value="">---请选择---</option>
					<c:forEach var="studyType" items="${sessionScope.studyTypeAll}">
						<option
								<c:if test="${studyType.majorId eq requestScope.classes.studyType}">
									selected
								</c:if>
								value="${studyType.majorId}">${studyType.majorName}</option>
					</c:forEach>
				</select>
			</div>
			<span id="majorMes"></span>
		</div>

		
		<div class="form-group">
			<div class="col-md-6 col-md-offset-3">
				<button type="submit" name="sub"  class="btn btn-primary">提交</button>
				<button id="send" type="reset" class="btn btn-success">重置</button>
				<a href="${ctx}/classServlet.do?param=show&&pageC" id="sends" type="button"
<%--				<a href="${ctx}/page/classes/classManager.jsp" id="sends" type="button"--%>
					class="btn btn-success">取消</a>
			</div>
		</div>
	</form>
</div>
<!-- 增加页面结束 -->
<%@ include file="../../common/footer.jsp"%>
<%--<script src="${ctx }/statics/localjs/classes/classYanZheng.js"></script>--%>
<script src="${pageContext.request.contextPath}/statics/localjs/classes/classes_update.js"></script>
<%--<script src="${ctx }/statics/localjs/classes/classdelete.js"></script>--%>
