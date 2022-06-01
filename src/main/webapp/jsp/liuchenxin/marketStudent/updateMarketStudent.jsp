
<%@ page import="java.util.List" %>
<%@ page import="ZTE.dao.liuchenxin.marketClass.MarketClassDao" %>
<%@ page import="ZTE.dao.liuchenxin.marketClass.MarketClassImpl" %>
<%@ page import="ZTE.dao.liuchenxin.marketStudent.MarketStudentDao" %>
<%@ page import="ZTE.dao.liuchenxin.marketStudent.MarketStudentImpl" %>
<%@ page import="ZTE.entity.liuchenxin.MarketClass" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../../../common/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<%
    //创建方法对象
    MarketClassDao marketClassDao = new MarketClassImpl();
    MarketStudentDao marketStudentDao = new MarketStudentImpl();
    //获取所有的学生类型
    List<String> allStuType = marketClassDao.getAllMarketType();
    request.setAttribute("allStuType",allStuType);
    //获取到所有的学历类型
    List<String> allEducation = marketStudentDao.getAllEducation();
    request.setAttribute("allEducation",allEducation);
    //获取到所有的性格类型
    List<String> xinge = marketStudentDao.getAllXingge();
    request.setAttribute("xinge",xinge);
    //获取到所有的培训意向类型
    List<String> allWillTrain = marketStudentDao.getAllWillTrain();
    request.setAttribute("allWillTrain",allWillTrain);
    //获取到所有的班级
    List<MarketClass> MarketClassList = marketClassDao.getAllMarketClass();
    request.setAttribute("MarketClassList",MarketClassList);
%>
<!-- 增加页面开始 -->
<div class="x_content">
    <form id="query" class="form-horizontal form-label-left" action="${ctx}/MarketStudentServlet.do?caozuo=update_gai"
          method="post" novalidate>
        <input type="hidden" name="id" value="${student.id}">
        <input type="hidden" name="oldname" value="${student.name}">
        <span class="section">修改市场类学生信息</span>
        <!-- 学生姓名 -->
        <div class="item form-group">
            <%--			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">班级名称--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name" >姓名
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="name" name="name" value="${student.name}"
                       data-validate-linked="email" required
                       class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <!-- 市场类型 -->
        <div class="item form-group">
            <%--		  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">专业类型--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="stuType1" >市场类型
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <select name="marketType" value="" id="stuType1"
                        class="form-control col-md-7 col-xs-12">
                    <option value="">---请选择---</option>
                    <c:forEach var="stuType11" items="${allStuType}">
                        <option value="${stuType11}"
                                <c:if test="${stuType11==student.stuType}">
                                    selected
                                </c:if>
                        >${stuType11}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <!-- 班级 -->
        <div class="item form-group">
            <%--		  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">专业类型--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="stuClass1" >班级
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <select name="className" value="" id="stuClass1"
                        class="form-control col-md-7 col-xs-12">
                    <option value="">---请选择---</option>
                    <c:forEach var="marketClass" items="${MarketClassList}">
                        <option class="neirong" value="${marketClass.name}"
                                <c:if test="${marketClass.name==student.className}">
                                    selected
                                </c:if>
                        >${marketClass.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <!-- 来自学校 -->
        <div class="item form-group">
            <%--			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">班级名称--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="fromSchool" >来自学校
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="fromSchool" name="fromSchool" value="${student.fromSchool}"
                       data-validate-linked="email" required
                       class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <!-- 学历 -->
        <div class="item form-group">
            <%--		  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">专业类型--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="education" >学历
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <select name="education" value="" id="education"
                        class="form-control col-md-7 col-xs-12">
                    <option value="">---请选择---</option>
                    <c:forEach var="education" items="${allEducation}">
                        <option value="${education}"
                        <c:if test="${education==student.education}">
                            selected
                        </c:if>
                        >${education}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <!-- 电话 -->
        <div class="item form-group">
            <%--			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">班级名称--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="phone" >电话
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="phone" name="phone" value="${student.phone}"
                       data-validate-linked="email" required
                       class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <!-- QQ -->
        <div class="item form-group">
            <%--			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">班级名称--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="QQ" >QQ
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="QQ" name="QQ" value="${student.qq}"
                       data-validate-linked="email" required
                       class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <!-- 性格特点 -->
        <div class="item form-group">
            <%--		  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">专业类型--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="xingge" >性格特点
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <select name="xingge" value="" id="xingge"
                        class="form-control col-md-7 col-xs-12">
                    <option value="">---请选择---</option>
                    <c:forEach var="x" items="${xinge}">
                        <option value="${x}"
                                <c:if test="${x==student.xingge}">
                                    selected
                                </c:if>
                        >${x}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <!-- 备注 -->
        <div class="item form-group">
            <%--			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">班级名称--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="beizhu" >备注
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="beizhu" name="beizhu" value="${student.beizhu}"
                       data-validate-linked="email" required
                       class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <!-- 培训意向 -->
        <div class="item form-group">
            <%--		  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">专业类型--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="willTrain" >培训意向
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <select name="willTrain" value="" id="willTrain"
                        class="form-control col-md-7 col-xs-12">
                    <option value="">---请选择---</option>
                    <c:forEach var="willTrain" items="${allWillTrain}">
                        <option value="${willTrain}"
                                <c:if test="${willTrain==student.willTrain}">
                                    selected
                                </c:if>
                        >${willTrain}</option>
                    </c:forEach>
                </select>
            </div>
        </div>


        <div class="form-group">
            <div class="col-md-6 col-md-offset-3">
                <button type="submit" class="btn btn-primary">保存</button>
                <button id="send" type="reset" class="btn btn-success">重置</button>
                <a href="${ctx}/MarketStudentServlet.do?caozuo=cha"
                <%--				<a href="${ctx}/page/classes/classManager.jsp" id="sends" type="button"--%>
                   class="btn btn-success">取消</a>
            </div>
        </div>
    </form>
</div>
<!-- 增加页面结束 -->
<%@ include file="../../../common/footer.jsp"%>
<script src="${pageContext.request.contextPath}/statics/localjs/updateMarketStudent.js"></script>
<script src="${pageContext.request.contextPath}/statics/localjs/marketStudent.js"></script>
<%--<script src="${ctx }/statics/localjs/classYanZheng.js"></script>--%>
