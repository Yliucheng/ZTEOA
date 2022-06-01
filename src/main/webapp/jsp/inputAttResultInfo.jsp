<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../common/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!-- 考勤录入页面开始 -->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/statics/js/jquery.min.js"></script>
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
        <h2>考勤录入</h2>
        <div class="x_content">
            <br/>
            <form id="query" data-parsley-validate
                  class="form-horizontal form-label-left" method="post"
                  action="attServlet">
                <input type="hidden" name="pageIndex" value="1"/>
                <input type="hidden" name="operate" value="cha"/>
                <ul style="list-style: none; display: inline-block">
                    <li><label class="control-label col-md-3 col-sm-3 col-xs-12">培训专业： </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="major" value="" id="Emajor"
                                    class="form-control col-md-7 col-xs-12 require">
                                <option value="">---请选择---</option>
                                <c:forEach var="maj" items="${ majorList }">
                                    <option value="${maj.majorId}" ${mId==maj.majorId?'selected':''}>${maj.majorName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">班级： </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select name="className" id="Eclassname" value=""
                                        class="form-control col-md-7 col-xs-12 require">
                                    <option value="">---请选择---</option>
                                    <c:forEach var="clist" items="${ classList }">
                                        <option value="${clist.className}"
                                            ${cName==clist.className?'selected':''}>${clist.className}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                                <button type="submit" class="btn btn-primary"
                                        class="btn btn-success">查&nbsp;&nbsp;询
                                </button>
                            </div>
                        </div>
                    </li>
                </ul>
            </form>
            <form id="upload" action="attServlet?operate=upload" enctype="multipart/form-data" method="post" style="display: inline;position: absolute;right: 120px;top: 20px">
                <a href="${ctx}/attServlet?operate=template"
                   class="btn btn-primary"
                   class="btn btn-success"
                   onclick="return confirmExport()">下载模板</a>
                <button href="#" style="display: inline" id="shangchuan" class="btn btn-primary">上&nbsp;&nbsp;传</button>
                <input type="file" id="excelFile" name="excelFile" style="display: none" multiple>
                <button type="submit" id="toUpload" style="display: none"></button>
            </form>
        </div>
    </div>

    <!-- 表格 -->
    <c:if test="${! empty sList}">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <span>请选择学生学号点击按钮等级考勤</span>
                <button id="normal" class="btn btn-success btn-sm" value="正常">正常</button>
                <button id="late" class="btn btn-success btn-sm" value="迟到">迟到</button>
                <button id="absent" class="btn btn-success btn-sm" value="旷课">旷课</button>
                <button id="excused" class="btn btn-success btn-sm" value="早退">早退</button>
                <table id="datatable" class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>选择学号</th>
                        <th>培训专业</th>
                        <th>班级名称</th>
                        <th>学生姓名</th>
                    </tr>
                    </thead>
                    <c:forEach var="stu" items="${ sList }">
                        <tbody>
                        <tr>
                            <td>
                                <input type="checkbox" name="studentNo" value="${ stu.studentid }">${ stu.studentid }
                            </td>
                            <td>${ stu.major }</td>
                            <td>${ stu.className }</td>
                            <td>${ stu.studentName }</td>
                        </tr>
                        </tbody>
                    </c:forEach>
                    <tr>
                        <td>
                            <input type="checkbox" name="selectall" id="selectall">全选
                        </td>
                        <td colspan="10" style="text-align: right;">
                            <span>共有${pageInfo.totalCount }条数据，${pageInfo.currentPageNo }/${pageInfo.totalPageCount }页</span>
                            <a href="attServlet?operate=cha&className=${cName}&major=${mId}&pageIndex=1">首页</a>&nbsp;
                            <a href="attServlet?operate=cha&className=${cName}&major=${mId}&pageIndex=${pageInfo.currentPageNo-1}">上一页</a>&nbsp;
                            <a href="attServlet?operate=cha&className=${cName}&major=${mId}&pageIndex=${pageInfo.currentPageNo+1}">下一页</a>&nbsp;
                            <a href="attServlet?operate=cha&className=${cName}&major=${mId}&pageIndex=${pageInfo.totalPageCount}">末页</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </c:if>

    <c:if test="${empty sList}">
        <c:choose>
            <c:when test="${resultCountToday>0}">
                <h1>该班今日考勤已录入完毕！</h1>
            </c:when>
            <c:otherwise>
                <h1>没有学生信息</h1>
            </c:otherwise>
        </c:choose>
    </c:if>
    <!-- 表格 -->
    <!-- 分页 -->
    <!-- 分页 -->
</div>

<!-- appInfoList页面结束 -->
<!-- /page content -->
<%@ include file="../common/footer.jsp" %>
<script src="${ctx }/statics/localjs/appinfoList.js"></script>
<script src="${ctx }/statics/localjs/report.js"></script>
<script src="${pageContext.request.contextPath }/statics/localjs/clickedWork.js"></script>