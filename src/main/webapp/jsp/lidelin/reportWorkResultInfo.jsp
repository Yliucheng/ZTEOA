<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../../common/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!-- 作业完成查询页面开始 -->
<script type="text/javascript" src="${ctx}/statics/js/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#hide").click(function () {
            $("#p1").hide();
        });
        $("#show").click(function () {
            $("#p1").show();
        });
    });

    function confirmAct() {
        if (confirm('确定要执行删除操作吗?')) {
            return true;
        }
        return false;
    }
</script>
<style>
    .p {
        background-color: #C5E2D0;
        height: 200px;
        display: none;
        border-radius: 10px;
    }
</style>
<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
        <h2>作业完成情况查询</h2>
        <div class="x_content">
            <br/>
            <form id="query" data-parsley-validate
                  class="form-horizontal form-label-left demo-form2" method="post"
                  action="workinfoservlet">
                <input type="hidden" name="pageIndex" value="1"/>
                <ul style="list-style: none; display: inline_block;">
                    <li><label class="control-label col-md-3 col-sm-3 col-xs-12"
                               for="first-name">培训专业： </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="major" value="" id="major"
                                    class="form-control col-md-7 col-xs-12">
                                <option value="">---请选择---</option>
                                <c:forEach var="major" items="${requestScope.majorList}">
                                    <option value="${major.major}"
                                        ${major.major==requestScope.major?'selected':''}>${major.major}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12"
                                   for="first-name">班级： </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select name="classname" id="classname" value=""
                                        class="form-control col-md-7 col-xs-12">
                                    <option value="">---请选择---</option>
                                    <c:forEach var="classInfo" items="${requestScope.classInfoList}">
                                        <option value="${classInfo.classname}"
                                            ${classInfo.classname==requestScope.classname?'selected':''}>
                                                ${classInfo.classname}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">姓名：
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="name" name="namec" value="${requestScope.Namec}"
                                       class="form-control col-md-7 col-xs-12" placeholder="输入2-5位中文或英文">
                            </div>
                            <span id="nameMes"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12"
                                   for="first-name">日期: </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="date" name="date"
                                       value="${requestScope.date}"
                                       id="querySoftWareName" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12"
                                   for="first-name">作业表现： </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select name="result" id="result" value=""
                                        class="form-control col-md-7 col-xs-12">
                                    <option value="">---请选择---</option>
                                    <c:forEach var="workResult" items="${sessionScope.workResultList}">
                                        <option value="${workResult.ckstatu}"
                                            ${workResult.ckstatu==requestScope.result?'selected':''}>
                                                ${workResult.ckstatu}</option>
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
                                <a href="${ctx}/excelworkservlet?major=${requestScope.major}&&classname=${requestScope.classname}&&Namec=${requestScope.Namec}&&date=${requestScope.date}&&result=${requestScope.result}"
                                   class="btn btn-primary"
                                   class="btn btn-success">导&nbsp;&nbsp;&nbsp;&nbsp;出</a>
                            </div>
                        </div>
                    </li>
                </ul>
            </form>
        </div>
    </div>
    <!-- 第二个面板开始 -->
    <div id="p1" class="p" class="x_panel" id="exportPanel">

        <button id="hide" class="btn btn-success btn-sm"
                style="margin-left: 1100px; margin-top: 20px;">关闭
        </button>

        <div class="x_content">
            <br/>
            <form id="demo-form2" data-parsley-validate
                  class="form-horizontal form-label-left" method="post"
                  action="${ctx}/excelworkservlet2">
                <ul style="list-style: none; display: inline_block;">
                    <li><label class="control-label col-md-3 col-sm-3 col-xs-12"
                               for="first-name">培训专业： </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="Emajor" value="" id="Emajor"
                                    class="form-control col-md-7 col-xs-12">
                                <option value="">---请选择---</option>
                                <c:forEach var="major" items="${requestScope.majorList}">
                                    <option value="${major.major}"
                                        ${major.major==requestScope.major?'selected':''}>${major.major}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12"
                                   for="first-name">班级： </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select name="Eclassname" value="" id="Eclassname"
                                        class="form-control col-md-7 col-xs-12">
                                    <option value="">---请选择---</option>
                                    <c:forEach var="classInfo" items="${requestScope.classInfoList}">
                                        <option value="${classInfo.classname}"
                                            ${classInfo.classname==requestScope.classname?'selected':''}>
                                                ${classInfo.classname}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12"
                                   for="first-name">作业月份: </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="month" name="month"
                                       value="${requestScope.month==null?'':requestScope.month}"
                                       id="querySoftWareName" class="form-control col-md-7 col-xs-12 require">
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                <button type="submit" class="btn btn-primary"
                                        class="btn btn-success">导&nbsp;&nbsp;&nbsp;&nbsp;出
                                </button>
                            </div>
                        </div>
                    </li>
                </ul>
            </form>
        </div>
    </div>

    <!-- 第二个面板结束 -->
    <!-- 表格 -->
    <c:if test="${!empty requestScope.workInfoList}">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <button id="show" class="btn btn-success btn-sm">按月份导出班级学生作业完成情况信息</button>
                <table id="datatable" class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>学生姓名</th>
                        <th>作业日期</th>
                        <th>作业完成表现</th>
                        <th style="width: 125px;">操作</th>
                    </tr>
                    </thead>
                    <c:forEach var="workInfo" items="${requestScope.workInfoList}">
                        <tbody>
                        <tr>
                            <td>${workInfo.id}</td>
                            <td>${workInfo.studentName}</td>
                            <td>${workInfo.cktime}</td>
                            <td>${workInfo.ckstatu}</td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-danger">点击操作</button>
                                    <button type="button" class="btn btn-danger dropdown-toggle"
                                            data-toggle="dropdown" aria-expanded="false">
                                        <span class="caret"></span> <span class="sr-only">Toggle
											Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li>
<%--                                            <a href="${ctx}/report/reportworkedit.jsp?id=${workInfo.id}&&studentName=${workInfo.studentName}&&cktime=${workInfo.cktime}&&ckstatu=${workInfo.ckstatu}&&major=${requestScope.major}&&classname=${requestScope.classname}&&namec=${requestScope.Namec}&&date=${requestScope.date}&&result=${requestScope.result}" data-toggle="tooltip" data-placement="top" data-original-title="修改学生信息">修改</a>--%>
                                            <a href="workToUpservlet?id=${workInfo.id}&&major=${requestScope.major}&&classname=${requestScope.classname}&&namec=${requestScope.Namec}&&date=${requestScope.date}&&result=${requestScope.result}" data-toggle="tooltip" data-placement="top"
                                               data-original-title="修改学生信息">修改</a>
                                        </li>
                                        <li>
                                            <a href="${ctx}/workdelservlet?id=${workInfo.id}&&major=${requestScope.major}&&classname=${requestScope.classname}&&namec=${requestScope.Namec}&&date=${requestScope.date}&&result=${requestScope.result}"
                                               data-toggle="tooltip" data-placement="top"
                                               data-original-title="删除学生信息" onclick="return confirmAct();">删除</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </c:forEach>
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

    <c:if test="${ empty requestScope.workInfoList }">
        <h1>没有学生信息</h1>
    </c:if>
    <!-- 表格 -->
    <!-- 分页 -->
    <!-- 分页 -->
</div>

<!-- appInfoList页面结束 -->
<!-- /page content -->
<%@ include file="../../common/footer.jsp" %>
<script src="${ctx}/statics/localjs/appinfoList.js"></script>
<script src="${ctx}/statics/localjs/reportLDL.js"></script>
<script src="${ctx}/statics/localjs/rollpage.js"></script>
<script src="${pageContext.request.contextPath}/statics/localjs/student/showCheckName.js"></script>
<%--<script src="${ctx}/statics/localjs/exportMoringResult.js"></script>--%>