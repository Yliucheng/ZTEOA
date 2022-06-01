<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="../../common/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!-- 修改页面开始 -->
<div class="x_content">
	<form class="form-horizontal form-label-left"
		  action="${ctx}/workupservlet?major=${requestScope.major}&&classname=${requestScope.classname}&&namec=${requestScope.namec}&&date=${requestScope.date}&&result=${requestScope.result}"
		  method="post" novalidate>
		<span class="section">作业情况修改页面</span>
		<div class="form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12"
				   for="first-name">ID:</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<input type="text" readonly="readonly" name="id"
					   value="${requestScope.workInfo.id}" id="querySoftWareName"
					   class="form-control col-md-7 col-xs-12">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12"
				   for="first-name">学生姓名:</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<input type="text" readonly="readonly" name="studentName"
					   value="${requestScope.workInfo.studentName}"
					   id="querySoftWareName" class="form-control col-md-7 col-xs-12">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12"
				   for="first-name">作业日期:</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<input type="date" name="newDate"
					   value="${requestScope.workInfo.cktime}"
					   id="querySoftWareName" class="form-control col-md-7 col-xs-12">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12"
				   for="first-name">作业完成表现： </label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<select name="newResult" value=""
						class="form-control col-md-7 col-xs-12">
					<option value="">---请选择---</option>
					<c:forEach var="workResult" items="${sessionScope.workResultList}">
						<option value="${workResult.ckstatu}"
							${workResult.ckstatu==requestScope.workInfo.ckstatu?'selected':''}>
								${workResult.ckstatu}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-6 col-md-offset-3">
				<button type="submit" class="btn btn-primary">提交</button>
				<button id="send" type="reset" class="btn btn-success">重置</button>
				<a href="${ctx }/workinfoservlet" id="send" type="button"
				   class="btn btn-success">取消</a>
			</div>
		</div>
	</form>
</div>
<!-- 修改页面结束 -->
<%@ include file="../../common/footer.jsp"%>
