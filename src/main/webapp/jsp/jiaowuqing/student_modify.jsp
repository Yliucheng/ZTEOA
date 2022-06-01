<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="../../common/header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<script src="js/jquery-3.5.1.js" type="text/javascript"></script>
<!-- 增加页面开始 -->
<div class="x_content">
	<form class="form-horizontal form-label-left" id="query"
		action="${ctx}/studentServlet" method="post" novalidate>
		<span class="section">学生${cao=="add"?"增加":"修改"}页面</span>
		<input type="hidden" name="param" value="${cao}">
		<input type="hidden" name="id" value="${student.studentid}">
		<input type="hidden" name="zhuanye" value="${zhuanye}">
		<input type="hidden" name="banji" value="${banji}">
		<input type="hidden" name="name" value="${name}">

		<div class="item form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">姓名
				<span class="required">*</span>
			</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<input type="text" id="name" name="Name" value="${student.studentName}"
					data-validate-linked="email" required="required"
					class="form-control col-md-7 col-xs-12" placeholder="请输入2-5位中文或英文字符">
			</div>
			<span id="nameMes"></span>
		</div>
		<div class="item form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="classes">班级
				<span class="required">*</span>
			</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<select name="classid" id="classes" required="required"
						class="form-control col-md-7 col-xs-12">
					<option value="">---请选择---</option>
					<c:forEach var="classes" items="${classList}">
						<option value="${classes.classId}" ${classes.classId eq student.classId?"selected":""}>
								${classes.className}
						</option>
					</c:forEach>
				</select>
			</div>
			<span id="classMes"></span>
		</div>
		<div class="item form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="occupation">来自院校
				<span class="required">*</span>
			</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<input id="occupation" type="tel" name="yuanxiao" value="${student.fromSchool}"
					data-validate-length-range="5,20" required="required"
					class="optional form-control col-md-7 col-xs-12" placeholder="请输入院校名称！">
			</div>
			<span id="yuanxiaoMes"></span>
		</div>
		<div class="item form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="education">学历(大专/本科)<span
				class="required">*</span>
			</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<select name="xueli" id="education" required="required"
					class="form-control col-md-7 col-xs-12">
					<option value="">---请选择---</option>
					<c:forEach var="item" items="${education}">
						<option value="${item}" ${item eq student.education?"selected":""}>
								${item}
						</option>
					</c:forEach>
				</select>
			</div>
			<span id="xueliMes"></span>
		</div>
		<div class="item form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="logincode">登录账号(姓名拼音)<span
				class="required">*</span>
			</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<input id="logincode" class="form-control col-md-7 col-xs-12" value="${student.logincode}"
					data-validate-length-range="6" data-validate-words="2"
					name="logincode" required="required" type="text" placeholder="请输入4-12位登录账号！">
			</div>
			<span id="logincodeMes"></span>
		</div>
		<div class="item form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="userpwd">登录密码
				<span class="required">*</span>
			</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<input type="password" id="userpwd" name="password" value="${student.password}"
					required="required" class="form-control col-md-7 col-xs-12" placeholder="请输入5-8位英文字母和数字！">
			</div>
			<span id="pwdMes"></span>
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
<%@ include file="../../common/footer.jsp"%>
<script src="${pageContext.request.contextPath}/statics/localjs/student/studentRegister.js"></script>
<script src="${pageContext.request.contextPath}/statics/localjs/student/student_modify.js"></script>