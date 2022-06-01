<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../../common/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!-- appInfoList页面开始 -->
<script src="${ctx}/js/jquery-3.5.1.js" type="text/javascript"></script>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <h2>作业完成情况录入</h2>
            <div class="x_content">
                <br/>
                <form id="query" data-parsley-validate
                      class="form-horizontal form-label-left" method="post"
                      action="workrecordservlet">
                    <!-- 隐藏域, 用于传递分页的当前页号 -->
                    <input type="hidden" name="pageIndex" value="1"/>
                    <ul style="list-style: none; display: inline-block">
                        <li><label class="control-label col-md-3 col-sm-3 col-xs-12">培训专业： </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select name="major" value="" id="major"
                                        class="form-control col-md-7 col-xs-12">
                                    <option value="">---请选择---</option>
                                    <c:forEach var="major" items="${requestScope.majorList}">
                                        <option value="${major.major}"
                                            ${requestScope.major==major.major?'selected':''}>
                                                ${major.major}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">班级： </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="classname" id="classname" value=""
                                            class="form-control col-md-7 col-xs-12">
                                        <option value="">---请选择---</option>
                                        <c:forEach var="classInfo" items="${requestScope.classInfoList}">
                                            <option value="${classInfo.classname}"
                                                ${requestScope.classname==classInfo.classname?'selected':''}>
                                                    ${classInfo.classname}</option>
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
                <form id="importExcel" action="${ctx }/excelworkservlet3" method="post" enctype="multipart/form-data"
                      style="display: inline; position: absolute; right: 150px; top: 21px">
                    <input type="hidden" name="major" value="${requestScope.major}">
                    <input type="hidden" name="classname" value="${requestScope.classname}">
                    <button type="button" id="importButton" class="btn btn-primary"
                            class="btn btn-success" style="display: inline">导&nbsp;&nbsp;&nbsp;&nbsp;入
                    </button>
                    <%-- 把文件输入框隐藏起来 只显示普通按钮 --%>
                    <input type="file" id="excelFile" name="file5" multiple="multiple"
                           style="display: none">
                </form>
            </div>
        </div>
    </div>

    <!-- 表格 -->
    <c:if test="${!empty requestScope.studentList}">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                请选择学生学号点击按钮录入作业情况：
                <button id="clickcha" value="完成" class="btn btn-success btn-sm">完成</button>
                <button id="clickzhong" value="未完成" class="btn btn-success btn-sm">未完成</button>
                <button id="clickhao" value="未全部完成" class="btn btn-success btn-sm">未全部完成</button>
                <button id="clickgood" value="请假未完成" class="btn btn-success btn-sm">请假未完成</button>
                <table id="datatable" class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th colspan="4"><input type="checkbox" id="selectall"
                                               value=""/>全选
                        </th>
                    </tr>
                    <tr>
                        <th>选择学号</th>
                        <th>培训专业</th>
                        <th>班级名称</th>
                        <th>学生姓名</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="student" items="${requestScope.studentList}">
                        <tr>
                            <th><input type="checkbox" name="studentNo"
                                       value="${student.studentId}"/>${student.studentId}</th>
                            <td>${student.major }</td>
                            <td>${student.className }</td>
                            <td>${student.studentName}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tr>
                        <td colspan="10" style="text-align: right;">
                            <span>共有${requestScope.pageSupport.totalCount }条数据，
                                    ${requestScope.pageSupport.currentPageNo}/
                                    ${requestScope.pageSupport.totalPageCount}页</span>&nbsp;&nbsp;
                            <a href="javascript:page_nav(document.forms[0],1);">首页</a>&nbsp;
                            <a href="javascript:page_nav(document.forms[0],${requestScope.pageSupport.currentPageNo}-1);">上一页
                            </a>&nbsp;
                            <a href="javascript:page_nav(document.forms[0],${requestScope.pageSupport.currentPageNo}+1);">下一页
                            </a>&nbsp;
                            <a href="javascript:page_nav(document.forms[0],${requestScope.pageSupport.totalPageCount});">末页</a>&nbsp;
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </c:if>

    <c:if test="${empty requestScope.studentList}">
        <h1>今日作业完成情况已录入！</h1>
    </c:if>
    <!-- 表格 -->
</div>

<!-- appInfoList页面结束 -->
<!-- /page content -->
<%@ include file="../../common/footer.jsp" %>
<script src="${ctx}/statics/localjs/appinfoList.js"></script>
<script src="${ctx}/statics/localjs/clickedJob.js"></script>
<script src="${ctx}/statics/localjs/rollpage.js"></script>
<script src="${ctx}/statics/localjs/checkExcel.js"></script>

