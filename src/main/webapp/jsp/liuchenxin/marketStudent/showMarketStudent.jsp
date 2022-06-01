<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="ZTE.dao.liuchenxin.marketClass.MarketClassDao" %>
<%@ page import="ZTE.dao.liuchenxin.marketClass.MarketClassImpl" %>
<%@ page import="ZTE.dao.liuchenxin.marketStudent.MarketStudentDao" %>
<%@ page import="ZTE.dao.liuchenxin.marketStudent.MarketStudentImpl" %>

<%@ include file="../../../common/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
//得到所有的学生类型
    MarketClassDao marketClassDao = new MarketClassImpl();
    MarketStudentDao marketStudentDao = new MarketStudentImpl();
    //获取所有的学生类型
    List<String> allStuType = marketClassDao.getAllMarketType();
    request.setAttribute("allStuType",allStuType);
    //获取到所有的培训意向
    List<String> willTrainList = marketStudentDao.getAllWillTrain();
    request.setAttribute("willTrainList",willTrainList);
%>

<!-- appInfoList页面开始 -->
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_content">
                <br/>
                <form id="demo-form2" data-parsley-validate
                      class="form-horizontal form-label-left demo-form2"
                      method="post" action="MarketStudentServlet.do?caozuo=cha">
                    <!-- 这个pageIndex隐藏域是当前页号-->
                    <input type="hidden" name="pageIndex" value="1"/>
                    <ul style="list-style:none;display:inline-block;">
                        <!--学生类型-->
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">学生类型 </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="type" id="stuType1"
                                            class="form-control col-md-7 col-xs-12">
                                        <option value="">---请选择---</option>
                                        <c:forEach var="stuType" items="${allStuType}">
                                            <option class="typeneirong" value="${stuType}"
                                            <c:if test="${showType==stuType}">
                                                selected
                                            </c:if>
                                            >${stuType}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <!--班级-->
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">班级 </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="className" id="stuClass1"
                                            class="form-control col-md-7 col-xs-12">
                                        <option value="">---请选择---</option>
                                            <c:forEach items="${classList}" var="c">
                                                <option  class="neirong" value="${c}"
                                                <c:if test="${showClassName==c}">
                                                    selected
                                                </c:if>
                                                >${c}</option>
                                            </c:forEach>
<%--                                        <c:forEach var="studyType" items="${sessionScope.studyTypeAll}">--%>
<%--                                            <option--%>
<%--                                                    <c:if test="${studyType.majorId eq requestScope.majorId}">--%>
<%--                                                        selected--%>
<%--                                                    </c:if>--%>
<%--                                                    value="${studyType.majorId}">--%>
<%--                                                    ${studyType.majorName}</option>--%>
<%--                                        </c:forEach>--%>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <br>
                        <!--培训意向-->
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">培训意向 </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="willTrain"
                                            class="form-control col-md-7 col-xs-12">
                                        <option value="">---请选择---</option>
                                        <c:forEach var="willTrain" items="${willTrainList}">
                                            <option value="${willTrain}"
                                            <c:if test="${willTrain==showWillTrain}">
                                                selected
                                            </c:if>
                                            >${willTrain}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <!-- 姓名 -->
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">姓名 </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input id="name" type="text" class="form-control col-md-7 col-xs-12" name="name" value="${showName}" placeholder="输入2-5位中文或英文">
                                </div>
                                <span id="nameMes"></span>
                            </div>
                        </li>
                        <li>
                            <div class="form-group" style="margin-left: -100px">
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <span id="spanName" hidden style="color: red ;font-size:15px" >当前姓名不合法!</span>
                                </div>
                            </div>
                        </li>

                        <br>
                        <!-- 查询按钮 -->
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
            <a href="${pageContext.request.contextPath}/jsp/liuchenxin/marketStudent/addMarketStudent.jsp" class="btn btn-success btn-sm">新增学生信息</a>
            <a href="${pageContext.request.contextPath}/MarketStudentServlet.do?caozuo=daochu&stuType=${showType}&className=${showClassName}&stuName=${showName}&willTrain=${showWillTrain}"
               class="btn btn-success btn-sm">导出学生信息</a>
            <form id="upload" action="MarketStudentServlet.do?caozuo=daoru" enctype="multipart/form-data" method="post" style="display: inline">
                <button href="#" style="display: inline" id="shangchuan" class="btn btn-success btn-sm">上传</button>
                <input type="file" id="excelFile" name="excelFile" style="display: none" multiple value="点击上传学生表">
                <button type="submit" id="toSubmit" onclick="" style="display: none" class="btn btn-success btn-sm">上传</button>
            </form>
            <table id="datatable" class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>班级名称</th>
                    <th>来自学校</th>
                    <th>学历</th>
                    <th style="width:125px;">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="stu" items="${marketStudentList}">
                    <tr>
                        <td>${stu.id}</td>
                        <td>${stu.name}</td>
                        <td>${stu.className}</td>
                        <td>${stu.fromSchool}</td>
                        <td>${stu.education}</td>
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
                                           href="MarketStudentServlet.do?caozuo=update_cha&&id=${stu.id}"
                                           data-toggle="tooltip" data-placement="top" title=""
                                           data-original-title="修改班级信息">修改</a></li>
                                    <li><a href="MarketStudentServlet.do?caozuo=show&&id=${stu.id}">查看</a></li>
                                    <li><a href="MarketStudentServlet.do?caozuo=delete&id=${stu.id}"
                                           data-toggle="tooltip" data-placement="top"
                                           data-original-title="删除班级信息">删除</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <tr>
                    <c:set var="pageinfos" value="${requestScope.pageinfos}"></c:set>
                    <td colspan="10" style="text-align: right;">
				   	  <span>共有${pages.totalCount}条数据,
				   	  ${pages.currentPageNo}/${pages.totalPageCount}页</span>
                        <a href="javascript:page_nav(document.forms[0],1);">首页</a>&nbsp;
                        <a href="javascript:page_nav(document.forms[0],${pages.currentPageNo}-1);">上一页</a>
                        <a href="javascript:page_nav(document.forms[0],${pages.currentPageNo}+1);">下一页</a>
                        <a href="javascript:page_nav(document.forms[0],${pages.totalPageCount});">末页</a>
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
<%@ include file="../../../common/footer.jsp" %>
<script src="${pageContext.request.contextPath}/statics/localjs/marketStudent.js"></script>
<script src="${pageContext.request.contextPath}/statics/localjs/rollpage.js"></script>
<script src="${pageContext.request.contextPath}/statics/localjs/student/showCheckName.js"></script>
<%--<script src="${pageContext.request.contextPath}/statics/localjs/classshow.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/statics/localjs/classdelete.js"></script>--%>
