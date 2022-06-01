<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../common/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!-- 晨考成绩查询页面开始 -->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/statics/js/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#hide").click(function () {
            $("#p1").hide();
        });
        $("#show").click(function () {
            $("#p1").show();
        });
    });

    function confirmExport() {
        var classname = $("#testQuery").val();
        if (classname == "") {
            alert('当前未查询到数据，无法导出！')
            return false;
        }
        return true;
    }

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
        <h2>周考成绩查询</h2>
        <div class="x_content">
            <br/>
            <form id="query" data-parsley-validate
                  class="form-horizontal form-label-left demo-form2" method="post"
                  action="${pageContext.request.contextPath}/resultServlet">
                <input type="hidden" name="pageIndex" value="1"/>
                <input type="hidden" id="examType" name="examType" value="2"/>
                <input type="hidden" name="operate" value="cha"/>
                <input type="hidden" id="testQuery" value=${cName}>
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
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">姓名：
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="name" name="namec" value="${namec==null?'':namec}"
                                       class="form-control col-md-7 col-xs-12" placeholder="输入2-5位中文或英文">
                            </div>
                            <span id="nameMes"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">考试日期: </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="date" name="date" value="${date==null?'':date }"
                                       id="querySoftWareDate" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">成绩表现： </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select name="result" id="result" value=""
                                        class="form-control col-md-7 col-xs-12">
                                    <option value="">---请选择---</option>
                                    <c:forEach var="clist" items="${resultList }">
                                        <option value="${clist}"
                                            ${result==clist?'selected':''}>${clist}</option>
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
                                <a href="${ctx}/resultServlet?operate=export&examType=${examType}&className=${cName}&namec=${namec}&date=${date}&result=${result}"
                                   class="btn btn-primary"
                                   class="btn btn-success"
                                   onclick="return confirmExport()">导&nbsp;&nbsp;&nbsp;&nbsp;出</a>

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
                  action="resultServlet">
                <input type="hidden" name="operate" value="month"/>
                <input type="hidden" name="examType" value="2"/>
                <ul style="list-style: none; display: inline-block">
                    <li><label class="control-label col-md-3 col-sm-3 col-xs-12">培训专业： </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="Emajor" value="" id="qMajor"
                                    class="form-control col-md-7 col-xs-12">
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
                                <select name="qClassname" value="" id="qClassname"
                                        class="form-control col-md-7 col-xs-12">
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
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">考试月份: </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="month" name="qMonth" value="${month==null?'':month}"
                                       id="querySoftWareName" class="form-control col-md-7 col-xs-12">
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
    <c:if test="${! empty resList}">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <button id="show" class="btn btn-success btn-sm">按月份导出班级学生周考成绩信息</button>
                <table id="datatable" class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>考试ID</th>
                        <th>学生姓名</th>
                        <th>考试日期</th>
                        <th>考试类型</th>
                        <th>成绩</th>
                        <th style="width: 125px;">操作</th>
                    </tr>
                    </thead>
                    <c:forEach var="res" items="${ resList }">
                        <tbody>
                        <tr>
                            <td>${res.examId }</td>
                            <td>${res.studentName }</td>
                            <td>${res.examDate }</td>
                            <td>${res.examTypeNama }</td>
                            <td>${res.score }</td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-danger">点击操作</button>
                                    <button type="button" class="btn btn-danger dropdown-toggle"
                                            data-toggle="dropdown" aria-expanded="false">
                                        <span class="caret"></span> <span class="sr-only">Toggle
											Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="javascript:doPost('report/reportweekedit.jsp',{'id':'${res.examId}','examTypeNama':'${res.examTypeNama}','examType':'${examType}',
                                                'studentName':'${res.studentName}','examdate':'${res.examDate}','result':'${res.result}',
                                                'className':'${cName}','namec':'${namec}','date':'${date}','res':'${result}','major':'${mId}'})"
                                                data-toggle="tooltip" data-placement="top"
                                                data-original-title="修改学生信息">修改</a></li>
                                        <li><a href="#" data-toggle="tooltip" data-placement="top" class="toDel"
                                               data-original-title="删除学生信息" onclick="return confirmAct();">删除</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </c:forEach>
                    <tr>
                        <td colspan="10" style="text-align: right;">
                            <span>共有${pageInfo.totalCount }条数据，${pageInfo.currentPageNo }/${pageInfo.totalPageCount }页</span>
                            <a
                                    href="resultServlet?operate=cha&namec=${namec==null?'':namec}&className=${cName==null?'':cName}&major=${mId==null?'':mId}&date=${date==null?'':date }&result=${result==null?'':result }&examType=${examType==null?'':examType}&pageIndex=1">首页</a>&nbsp;
                            <a
                                    href="resultServlet?operate=cha&namec=${namec==null?'':namec}&className=${cName==null?'':cName}&major=${mId==null?'':mId}&date=${date==null?'':date }&result=${result==null?'':result }&examType=${examType==null?'':examType}&pageIndex=${pageInfo.currentPageNo-1}">上一页</a>&nbsp;
                            <a
                                    href="resultServlet?operate=cha&namec=${namec==null?'':namec}&className=${cName==null?'':cName}&major=${mId==null?'':mId}&date=${date==null?'':date }&result=${result==null?'':result }&examType=${examType==null?'':examType}&pageIndex=${pageInfo.currentPageNo+1}">下一页</a>&nbsp;
                            <a
                                    href="resultServlet?operate=cha&namec=${namec==null?'':namec}&className=${cName==null?'':cName}&major=${mId==null?'':mId}&date=${date==null?'':date }&result=${result==null?'':result }&examType=${examType==null?'':examType}&pageIndex=${pageInfo.totalPageCount}">末页</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </c:if>

    <c:if test="${ empty resList }">
        <h1>没有学生信息</h1>
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

<script src="${pageContext.request.contextPath }/statics/localjs/clickedResult.js"></script>
<script src="${pageContext.request.contextPath}/statics/localjs/student/showCheckName.js"></script>