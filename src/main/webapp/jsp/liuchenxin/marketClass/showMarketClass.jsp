<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="ZTE.dao.liuchenxin.marketClass.MarketClassDao" %>
<%@ page import="ZTE.dao.liuchenxin.marketClass.MarketClassImpl" %>

<%@ include file="../../../common/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    MarketClassDao marketClassDao = new MarketClassImpl();
    List<String> allMarketType = marketClassDao.getAllMarketType();//获取所有的市场类型
    request.setAttribute("allMarketType",allMarketType);

%>

<!-- appInfoList页面开始 -->
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_content">
                <br/>
                <form id="demo-form2" data-parsley-validate
                      class="form-horizontal form-label-left"
                      method="post" action="MarketClassServlet.do?caozuo=cha">
                    <!-- 隐藏域，用于传递分页的当前页号 -->
                    <!-- 这个pageIndex隐藏域是当前页号-->
                    <input type="hidden" name="pageIndex" value="1"/>
                    <ul style="list-style:none;display:inline-block;">

                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">专业类型 </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="type"
                                            class="form-control col-md-7 col-xs-12">
                                        <option value="">---请选择---</option>
                                        <c:forEach var="st" items="${allMarketType}">
                                            <option value="${st}"
                                                    <c:if test="${st==showType}">
                                                        selected
                                                    </c:if>
                                            >${st}</option>
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
            <a href="${pageContext.request.contextPath}/jsp/liuchenxin/marketClass/addMarketClass.jsp" class="btn btn-success btn-sm">新增班级信息</a>
            <table id="datatable" class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>班级编号</th>
                    <th>班级名称</th>
                    <th>市场类型</th>
                    <th style="width:125px;">操作</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="classes" items="${marketClassList}">
                    <tr>

                        <td>${classes.id}</td>
                        <td>${classes.name}</td>
                        <td>${classes.type}</td>

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
                                           href="MarketClassServlet.do?caozuo=update_cha&&id=${classes.id}"
                                           data-toggle="tooltip" data-placement="top" title=""
                                           data-original-title="修改班级信息">修改</a></li>
                                    <li><a href="#">查看</a></li>
                                    <li><a href="MarketClassServlet.do?caozuo=delete&id=${classes.id}"
                                           data-toggle="tooltip" data-placement="top"
                                           data-original-title="删除班级信息">删除</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <tr>
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
<script src="${pageContext.request.contextPath}/statics/localjs/rollpage.js"></script>
<%--<script src="${pageContext.request.contextPath}/statics/localjs/classshow.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/statics/localjs/classdelete.js"></script>--%>
