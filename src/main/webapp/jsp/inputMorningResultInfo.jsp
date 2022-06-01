<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="ZTE.entity.Student" %>
<%@ include file="../common/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!-- 晨考成绩录入页面开始 -->
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
        <h2>晨考成绩录入</h2>
        <div class="x_content">
            <br/>
            <form id="query" data-parsley-validate
                  class="form-horizontal form-label-left" method="post"
                  action="${pageContext.request.contextPath}/examServlet">
                <input type="hidden" name="pageIndex" value="1"/>
                <input type="hidden" id="examType" name="examType"  value="1"/>
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
            <form id="upload" action="examServlet?operate=upload" enctype="multipart/form-data" method="post" style="display: inline;position: absolute;right: 118px;top: 20px">
                <a href="${ctx}/examServlet?operate=template&examType=1"
                   class="btn btn-primary"
                   class="btn btn-success"
                   onclick="return confirmExport()">下载模板</a>
                <button href="#" style="display: inline" id="shangchuan" class="btn btn-primary">上&nbsp;&nbsp;传</button>
                <input type="file" id="excelFile" name="excelFile" style="display: none" multiple>
                <input type="hidden" name="examType"  value="1"/>
                <button type="submit" id="toUpload" style="display: none"></button>
            </form>
        </div>
    </div>

    <!-- 表格 -->
    <c:if test="${! empty sList}">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <span>请选择学生学号点击按钮评分</span>
                <button id="cha" class="btn btn-success btn-sm" value="差">差</button>
                <button id="zhong" class="btn btn-success btn-sm" value="中等">中等</button>
                <button id="hao" class="btn btn-success btn-sm" value="好">好</button>
                <button id="good" class="btn btn-success btn-sm" value="非常好">非常好</button>
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
                            <a href="${pageContext.request.contextPath}/examServlet?operate=cha&className=${cName}&examType=${examType}&major=${mId}&pageIndex=1">首页</a>&nbsp;
                            <a href="${pageContext.request.contextPath}/examServlet?operate=cha&className=${cName}&examType=${examType}&major=${mId}&pageIndex=${pageInfo.currentPageNo-1}">上一页</a>&nbsp;
                            <a href="${pageContext.request.contextPath}/examServlet?operate=cha&className=${cName}&examType=${examType}&major=${mId}&pageIndex=${pageInfo.currentPageNo+1}">下一页</a>&nbsp;
                            <a href="${pageContext.request.contextPath}/examServlet?operate=cha&className=${cName}&examType=${examType}&major=${mId}&pageIndex=${pageInfo.totalPageCount}">末页</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </c:if>

    <c:if test="${empty sList}">
        <c:choose>
            <c:when test="${resultCountToday>0}">
                <h1>该班今日晨试成绩已录入完毕！</h1>
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
<script src="${ctx }/statics/localjs/clickedResult.js"></script>
<%--<script src="${pageContext.request.contextPath }/statics/localjs/exportMoringResult.js"></script>--%>