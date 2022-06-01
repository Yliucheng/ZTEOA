<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>

<%@ include file="../../common/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- appInfoList页面开始 -->
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_content">
                <br/>
                <form id="demo-form2" data-parsley-validate
                      class="form-horizontal form-label-left"
                      method="post" action="classServlet.do?param=show">
                    <!-- 隐藏域，用于传递分页的当前页号 -->
<%--                    <input type="hidden" name="param" value="cha"/>--%>
                    <!-- 这个pageIndex隐藏域是当前页号-->
                    <input type="hidden" name="pageIndex" value="1"/>
                    <ul style="list-style:none;display:inline_block;">

                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">专业类型 </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="studyType"
                                            class="form-control col-md-7 col-xs-12">
                                        <option value="">---请选择---</option>
                                        <c:forEach var="studyType" items="${sessionScope.studyTypeAll}">
                                            <option
                                                    <c:if test="${studyType.majorId eq requestScope.majorId}">
                                                        selected
                                                    </c:if>
                                                    value="${studyType.majorId}">
                                                    ${studyType.majorName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </li>

                        <li>
                            <div class="form-group">
                                <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                    <button type="submit" class="btn btn-primary"
                                            class="btn btn-success">查&nbsp;&nbsp;&nbsp;&nbsp;询
                                    </button>
                                </div>
                            </div>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>

    <!-- 表格 -->
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <a href="${pageContext.request.contextPath}/classServlet.do?param=toadd" class="btn btn-success btn-sm">新增班级信息</a>
<%--            <a href="${pageContext.request.contextPath}/classServlet.do?param=excelImport" class="btn btn-success btn-sm">导入班级信息</a>--%>

            <a href="${pageContext.request.contextPath}/classServlet.do?param=excelExport" class="btn btn-success btn-sm">导出班级信息</a>
            <a href="${pageContext.request.contextPath}/classServlet.do?param=excelModel" class="btn btn-success btn-sm">下载导入模板</a>
            <form id="upload" action="${pageContext.request.contextPath}/classServlet.do?param=excelImport" enctype="multipart/form-data" method="post" style="display: inline">
                <button href="#" style="display: inline" id="shangchuan" class="btn btn-success btn-sm">上传</button>
                <input type="file" id="excelFile" name="excelFile" style="display: none" multiple value="点击上传学生表">
                <button type="submit" id="toSubmit" onclick="" style="display: none" class="btn btn-success btn-sm">上传</button>
            </form>
            <table id="datatable" class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>班级编号</th>
                    <th>班级名称</th>
                    <th>专业类型</th>
                    <th style="width:125px;">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="classes" items="${sessionScope.classList}">
                    <tr class="r">
                        <td id="c">${classes.classId}</td>
                        <td>${classes.className}</td>
                        <td>${classes.majorName}</td>
                        <td>
                            <div class="btn-group">
                                <button type="button" class="btn btn-danger">点击操作</button>
                                <button type="button" class="btn btn-danger dropdown-toggle"
                                        data-toggle="dropdown" aria-expanded="false">
                                    <span class="caret"></span> <span class="sr-only">Toggle
											Dropdown</span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="modifyAppInfo"
                                           data-toggle="tooltip" data-placement="top" title=""
                                           data-original-title="修改APP基础信息"
                                           href="${pageContext.request.contextPath}/classServlet.do?param=toModify&&id=${classes.classId}">修改</a></li>
                                    <input type="hidden" name="deleteId" value="${classes.classId}">
                                    <li><a href="#" class="delete" value="${classes.classId}">删除</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <tr>
                    <c:set var="pageinfos" value="${requestScope.pageinfos}"></c:set>
                    <td colspan="10" style="text-align: right;">
				   	  <span>共有${pageInfo.totalCount}条数据,
				   	  ${pageInfo.currentPageNo}/${pageInfo.totalPageCount}页</span>
                        <a href="javascript:page_nav(document.forms[0],1);">首页</a>&nbsp;
                        <a href="javascript:page_nav(document.forms[0],${pageInfo.currentPageNo}-1);">上一页</a>&nbsp;
                        <a href="javascript:page_nav(document.forms[0],${pageInfo.currentPageNo}+1);">下一页</a>&nbsp;
                        <a href="javascript:page_nav(document.forms[0],${pageInfo.totalPageCount});">末页</a>&nbsp;
                    </td>
                </tr>

            </table>
        </div>
    </div>
    <!-- 表格 -->
    <!-- 分页 -->
</div>

<!-- appInfoList页面结束 -->
<!-- /page content -->
<%@ include file="../../common/footer.jsp" %>
<script src="${pageContext.request.contextPath}/statics/localjs/rollpage.js"></script>
<script src="${pageContext.request.contextPath}/statics/localjs/classes/classshow.js"></script>
<script src="${pageContext.request.contextPath}/statics/localjs/classes/classdelete.js"></script>
