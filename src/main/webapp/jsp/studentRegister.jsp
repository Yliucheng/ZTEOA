<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<script src="js/jquery-3.5.1.js" type="text/javascript"></script>
<!-- 增加页面开始 -->
<div class="x_content">
	<form class="form-horizontal form-label-left" id="query"
		action="${ctx }/studentServlet" method="post" novalidate>
		<span class="section">学生注册页面</span>
		<input type="hidden" name="param" value="${cao}">
		<div class="item form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="classes">班级
				<span class="required">*</span>
			</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<select name="classId" id="classes" required="required"
					class="form-control col-md-7 col-xs-12">
					<option value="">---请选择---</option>
					<c:forEach var="maj" items="${classesList }">
						<option value="${maj.classId}">${maj.className}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="item form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">姓名
				<span class="required">*</span>
			</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<input type="text" id="name" name="name"
					data-validate-linked="email" required="required"
					class="form-control col-md-7 col-xs-12">
			</div>
		</div>
		<div class="item form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="occupation">来自院校
				<span class="required">*</span>
			</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<input id="occupation" type="tel" name="yuanxiao"
					data-validate-length-range="5,20" required="required"
					class="optional form-control col-md-7 col-xs-12">
			</div>
		</div>
		<div class="item form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="education">学历(大专/本科)<span
				class="required">*</span>
			</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<select name="xueli" id="education" required="required"
					class="form-control col-md-7 col-xs-12">
					<option value="">---请选择---</option>
					<option value="大专">大专</option>
					<option value="本科">本科</option>
				</select>
			</div>
		</div>
		<div class="item form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="logincode">登录账号(姓名拼音)<span
				class="required">*</span>
			</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<input id="logincode" class="form-control col-md-7 col-xs-12"
					data-validate-length-range="6" data-validate-words="2"
					name="logincode" required="required" type="text">
			</div>
		</div>
		<div class="item form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="userpwd">登录密码
				<span class="required">*</span>
			</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<input type="password" id="userpwd" name="password"
					required="required" class="form-control col-md-7 col-xs-12">
			</div>
		</div>

		<div class="form-group">
			<div class="col-md-6 col-md-offset-3">
				<input type="submit" value="提交" class="btn btn-primary" />
				<button type="reset" class="btn btn-success">重置</button>
				<a href="javascript:window.history.back()" id="send" type="button"
					class="btn btn-success">取消</a>
			</div>
		</div>
	</form>
</div>
<!-- 增加页面结束 -->
<%@ include file="../common/footer.jsp"%>
<script src="${ctx }/statics/localjs/studentadd.js"></script>
<script src="${ctx }/statics/localjs/StuAdd.js"></script>