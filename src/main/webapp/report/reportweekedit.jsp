<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ include file="../common/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!-- 修改页面开始 -->
<div class="x_content">
	<form class="form-horizontal form-label-left"
		  action="${ctx}/resultServlet"
		  method="post" novalidate>
		<input type="hidden" name="operate" value="change">
		<input type="hidden" name="id" value="${param.id}">
		<input type="hidden" name="examType" value="${param.examType}">
		<input type="hidden" name="className" value="${param.className}">
		<input type="hidden" name="namec" value="${param.namec}">
		<input type="hidden" name="date" value="${param.date}">
		<input type="hidden" name="result" value="${param.res}">
		<input type="hidden" name="major" value="${param.major}">
		<span class="section">周考成绩修改页面</span>
		<div class="form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12">考试ID:</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<input type="text" disabled="disabled" name=""
					   value="${param.id==null?'':param.id }" id="querySoftWareId"
					   class="form-control col-md-7 col-xs-12">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12">学生姓名:</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<input type="text" disabled="disabled" name=""
					   value="${param.studentName==null?'':param.studentName }"
					   id="querySoftWareName" class="form-control col-md-7 col-xs-12">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12">考试类型:</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<input type="text" disabled="disabled" name=""
					   value="${param.examTypeNama==null?'':param.examTypeNama}"
					   id="querySoftWareType" class="form-control col-md-7 col-xs-12">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12">考试日期:</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<input type="date" name="examdate"
					   value="${param.examdate==null?'':param.examdate }"
					   id="querySoftWareDate" class="form-control col-md-7 col-xs-12">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12">成绩： </label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<select name="studentresult" value=""
						class="form-control col-md-7 col-xs-12">
					<option value="">---请选择---</option>
					<c:forEach var="clist" items="${resultList }">
						<option value="${clist}"
							${param.result==clist?'selected':''}>${clist}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-6 col-md-offset-3">
				<button type="submit" id="toSubmit" class="btn btn-primary">提交</button>
				<a href="javascript:doPost('${ctx}/resultServlet',{'operate':'cha','examType':'${param.examType}','className':'${param.className}','namec':'${param.namec}',
				'date':'${param.date}','result':'${param.res}','major':'${param.major}'})"
				   id="send" type="button" class="btn btn-success">取消</a>
			</div>
		</div>
	</form>
</div>
<!-- 修改页面结束 -->
<%@ include file="../common/footer.jsp" %>
<script src="${pageContext.request.contextPath }/statics/localjs/clickedResult.js"></script>