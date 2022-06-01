<%@page import="entity.Classinfo"%>
<%@page import="servlet.PageSupport"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../common/header.jsp"%>
<%@page import="biz.StudentServiceImpl"%>
<%@page import="biz.StudentService"%>
<%@page import="entity.Student"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<script src="js/jquery-3.5.1.js" type="text/javascript"></script>
<style>
.bbtt {
	margin-left: 87px;
	margin-top: 10px;
}
</style>
<script>
  function confirmAct(){
	  if(confirm('确定要执行删除操作吗?')){
		  return true;
	  }
	  return false;
  }
</script>
<!-- appInfoList页面开始 -->
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel">
			<h2>
				学生管理维护<i class="fa fa-user"></i> ${loginName } 
			</h2>
			<!--  -->
			<div class="x_content">
				<br />
				<form id="query" data-parsley-validate
					class="form-horizontal form-label-left" method="post"
					action="chaservlet">
					<ul style="list-style: none; display: inline_block;">
						<li><label class="control-label col-md-3 col-sm-3 col-xs-12"
							for="first-name">专业 </label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<select name="major" value="" id="major"
									class="form-control col-md-7 col-xs-12">
									<option value="">---请选择---</option>
									<c:forEach var="maj" items="${majors }">
										<option value="${maj.major}" ${major==maj.major?'selected':''}>${maj.major}</option>
									</c:forEach>
								</select>
							</div></li>
						<li>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="first-name">班级 </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<select name="classname" id="classname" value=""
										class="form-control col-md-7 col-xs-12">
										<option value="">---请选择---</option>
										<c:forEach var="clist" items="${cList }">
											<option value="${clist.classname}"
												${classname==clist.classname?'selected':''}>${clist.classname}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</li>
						<li>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">姓名
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" name="namec" value="${Name==null?'':Name}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
						</li>
						<br />
						<li>
							<div class="form-group">
								<div class="col-md-6 col-sm-6 col-xs-12">
									<button type="submit" class="btn btn-primary bbtt"
										class="btn btn-success ">查&nbsp;&nbsp;&nbsp;&nbsp;询</button>
								</div>
							</div>
						</li>
					</ul>
				</form>
				<!-- <form id="demo-form2" data-parsley-validate
					class="form-horizontal form-label-left" method="post"
					action="idServlet">
					<input type="hidden" name="pageIndex" value="1" />
					<ul style="list-style: none; display: inline_block;">
						<li>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="first-name">学号查询 </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" name="stuidc" id="querySoftWareName"
										class="form-control col-md-7 col-xs-12">
								</div>
								<button type="submit" class="btn btn-primary"
									class="btn btn-success">查&nbsp;&nbsp;&nbsp;&nbsp;询</button>
							</div>
						</li>
					</ul>
				</form>  -->
				<!--  		<li>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="first-name">软件名称 </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" name="querySoftWareName"
										id="querySoftWareName" class="form-control col-md-7 col-xs-12">
								</div>
							</div>
						</li>
						-->
				<!--  	<li>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="last-name">APP状态 </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<select name="queryStatus"
										class="form-control col-md-7 col-xs-12">
										<c:if test="${statusList!=null}">
											<option value="">---请选择---</option>
											<c:forEach var="dataDictionary" items="${statusList}">
												<option
													<c:if test="${dataDictionary.valueId==queryStatus}">selected="selected"</c:if>
													value="${dataDictionary.valueId}">${dataDictionary.valueName}</option>
											</c:forEach>
										</c:if>
									</select>
								</div>
							</div></li>    
						
						<li>
							<div class="form-group">
								<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
									<button type="submit" class="btn btn-primary"
										class="btn btn-success">查&nbsp;&nbsp;&nbsp;&nbsp;询</button>
								</div>
							</div></li>    
					</ul>
				</form>
				-->
			</div>
			<!--   -->
		</div>
	</div>
		<!-- 表格 -->
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="x_panel">
				<a href="jsp/studentadd.jsp" class="btn btn-success btn-sm">注册学生信息</a>
				<a href="${ctx}/excelservlet" class="btn btn-success btn-sm">导出学生信息</a>
				<c:if test="${! empty studentList}">
				<table id="datatable" class="table table-striped table-bordered">
					<tr>
						<th>学号</th>
						<th>姓名</th>
						<th>专业</th>
						<th>班级</th>
						<th>来自院校</th>
						<th>学历</th>
						<th style="width: 125px;">操作</th>
					</tr>
					<c:forEach var="stu" items="${ studentList }">
						<tr>
							<td>${ stu.studentId }</td>
							<td>${ stu.studentName }</td>
							<td>${ stu.major }</td>
							<td>${ stu.classname }</td>
							<td>${ stu.fromschool }</td>
							<td>${ stu.education }</td>
							<td>
								<div class="btn-group">
									<button type="button" class="btn btn-danger">点击操作</button>
									<button type="button" class="btn btn-danger dropdown-toggle"
										data-toggle="dropdown" aria-expanded="false">
										<span class="caret"></span> <span class="sr-only">Toggle
											Dropdown</span>
									</button>
									<ul class="dropdown-menu" role="menu">
										<li><a
											href="jsp/studentedit.jsp?stuNo=${ stu.studentId }&Name=${ stu.studentName }
									&LoginCode=${ stu.logincode }&LoginPwd=${ stu.password }
									&classname=${ stu.classname }"
											data-toggle="tooltip" data-placement="top"
											data-original-title="修改学生信息">修改</a></li>
										<li><a href="${ctx}/delservlet?stuNo=${ stu.studentId }"
											data-toggle="tooltip" data-placement="top"
											data-original-title="删除学生信息" onclick="return confirmAct();">删除</a></li>
									</ul>
								</div>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="10" style="text-align: right;"><span>共有${pageInfo.totalCount }条数据，${pageInfo.currentPageNo }/${pageInfo.totalPageCount }页</span>
							<a
							href="chaservlet?namec=${Name==null?'':Name}&classname=${classname==null?'':classname}&major=${major==null?'':major}&pageIndex=1">首页</a>&nbsp;
							<a
							href="chaservlet?namec=${Name==null?'':Name}&classname=${classname==null?'':classname}&major=${major==null?'':major}&pageIndex=${pageInfo.currentPageNo-1}">上一页</a>&nbsp;
							<a
							href="chaservlet?namec=${Name==null?'':Name}&classname=${classname==null?'':classname}&major=${major==null?'':major}&pageIndex=${pageInfo.currentPageNo+1}">下一页</a>&nbsp;
							<a
							href="chaservlet?namec=${Name==null?'':Name}&classname=${classname==null?'':classname}&major=${major==null?'':major}&pageIndex=${pageInfo.totalPageCount}">末页</a>
						</td>
					</tr>
				</table>
				</c:if>
			</div>
		</div>
	

	<c:if test="${ empty studentList }">
		<h1>没有学生信息</h1>
	</c:if>
	<!-- 表格 -->
	<!-- 分页 -->
	<div class="row">
		<div class="col-sm-7">
			<div class="dataTables_paginate paging_simple_numbers"
				id="datatable-responsive_paginate">
				<ul class="pagination">
					<c:if test="${pages.currentPageNo > 1}">
						<li class="paginate_button previous"><a
							href="javascript:page_nav(document.forms[0],1);"
							aria-controls="datatable-responsive" data-dt-idx="0" tabindex="0">首页</a>
						</li>
						<li class="paginate_button "><a
							href="javascript:page_nav(document.forms[0],${pages.currentPageNo-1});"
							aria-controls="datatable-responsive" data-dt-idx="1" tabindex="0">上一页</a>
						</li>
					</c:if>
					<c:if test="${pages.currentPageNo < pages.totalPageCount }">
						<li class="paginate_button "><a
							href="javascript:page_nav(document.forms[0],${pages.currentPageNo+1 });"
							aria-controls="datatable-responsive" data-dt-idx="1" tabindex="0">下一页</a>
						</li>
						<li class="paginate_button next"><a
							href="javascript:page_nav(document.forms[0],${pages.totalPageCount });"
							aria-controls="datatable-responsive" data-dt-idx="7" tabindex="0">最后一页</a>
						</li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<!-- 分页 -->
</div>

<!-- appInfoList页面结束 -->
<!-- /page content -->
<%@ include file="../common/footer.jsp"%>
<script src="${ctx }/statics/localjs/appinfoList.js"></script>

