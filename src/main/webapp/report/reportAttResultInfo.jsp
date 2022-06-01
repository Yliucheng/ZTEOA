<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../common/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!-- 考勤查询页面开始 -->
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
        <h2>今日考勤情况查询</h2>
        <div class="x_content">
            <br/>
            <form id="query" data-parsley-validate
                  class="form-horizontal form-label-left demo-form2" method="post"
                  action="attServlet">
                <input type="hidden" name="pageIndex" value="1"/>
                <input type="hidden" name="operate" value="get"/>
                <input type="hidden" id="testQuery" value=${cName}>
                <ul style="list-style: none; display: inline-block;">
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
                                <input type="text" name="namec" id="name" value="${namec==null?'':namec}"
                                       class="form-control col-md-7 col-xs-12" placeholder="输入2-5位中文或英文">
                            </div>
                            <span id="nameMes"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">日期: </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="date" name="date" value="${date==null?'':date }"
                                       id="querySoftWareDate" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">考勤状态： </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select name="ckStatu" id="ckStatu" value=""
                                        class="form-control col-md-7 col-xs-12">
                                    <option value="">---请选择---</option>
                                    <c:forEach var="ckStatus" items="${attStatusList }">
                                        <option value="${ckStatus}"
                                            ${ckStatu==ckStatus?'selected':''}>${ckStatus}</option>
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
                                <a href="${ctx}/attServlet?operate=export&className=${cName}&namec=${namec}&date=${date}&ckStatu=${ckStatu}"
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

    <!-- 表格 -->
    <c:if test="${! empty attInfoList}">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <table id="datatable" class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>学生姓名</th>
                        <th>专业</th>
                        <th>班级</th>
                        <th>考勤日期</th>
                        <th>考勤状态</th>
                        <th style="width: 125px;">操作</th>
                    </tr>
                    </thead>
                    <c:forEach var="attInfo" items="${ attInfoList }">
                        <tbody>
                        <tr>
                            <td class="aId">${attInfo.id }</td>
                            <td>${attInfo.studentName }</td>
                            <td>${attInfo.major}</td>
                            <td>${attInfo.classes}</td>
                            <td>${attInfo.attDate }</td>
                            <td>${attInfo.ckStatus }</td>
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
                                            <a href="javascript:doPost('report/reportattedit.jsp',{'id':'${attInfo.id}','ckstatu':'${attInfo.ckStatus}','studentName':'${attInfo.studentName}','cktime':'${attInfo.attDate}',
                                                'className':'${cName}','namec':'${namec}','date':'${date}','ckStatu':'${ckStatu}','major':'${mId}'})"
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
                            <a href="attServlet?operate=get&namec=${namec==null?'':namec}&className=${cName==null?'':cName}&major=${mId==null?'':mId}&date=${date==null?'':date }&ckStatu=${ckStatu==null?'':ckStatu }&pageIndex=1">首页</a>&nbsp;
                            <a href="attServlet?operate=get&namec=${namec==null?'':namec}&className=${cName==null?'':cName}&major=${mId==null?'':mId}&date=${date==null?'':date }&ckStatu=${ckStatu==null?'':ckStatu }&pageIndex=${pageInfo.currentPageNo-1}">上一页</a>&nbsp;
                            <a href="attServlet?operate=get&namec=${namec==null?'':namec}&className=${cName==null?'':cName}&major=${mId==null?'':mId}&date=${date==null?'':date }&ckStatu=${ckStatu==null?'':ckStatu }&pageIndex=${pageInfo.currentPageNo+1}">下一页</a>&nbsp;
                            <a href="attServlet?operate=get&namec=${namec==null?'':namec}&className=${cName==null?'':cName}&major=${mId==null?'':mId}&date=${date==null?'':date }&ckStatu=${ckStatu==null?'':ckStatu }&pageIndex=${pageInfo.totalPageCount}">末页</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </c:if>

    <c:if test="${ empty attInfoList }">
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
<script src="${pageContext.request.contextPath }/statics/localjs/clickedWork.js"></script>
<script src="${pageContext.request.contextPath}/statics/localjs/student/showCheckName.js"></script>