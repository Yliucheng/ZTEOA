
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../../../common/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<c:set var="studyTypeAll" value="${sessionScope.studyTypeAll}"></c:set>
<!-- 修改页面开始 -->
<div class="x_content">
    <form id="query" class="form-horizontal form-label-left" action="${ctx}/MarketClassServlet.do?caozuo=update_gai"
          method="post" novalidate>
        <input type="hidden" name="cid" value="${marketClass.id}">
        <span class="section">修改市场类班级</span>
        <div class="item form-group">
            <%--			<label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">班级名称--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="classname" >班级名称
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="classname" name="name"
                       data-validate-linked="email" required
                       class="form-control col-md-7 col-xs-12" value="${marketClass.name}">
            </div>
        </div>
        <div class="item form-group">
            <%--		  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">专业类型--%>
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="studytype" >市场类型
                <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <select name="type" value="" id="studytype"
                        class="form-control col-md-7 col-xs-12">
                    <option value="" >---请选择---</option>
                    <c:forEach var="st" items="${allMarketType}">
                        <option value="${st}"
                        <c:if test="${st==marketClass.type}">
                            selected
                        </c:if>
                        >${st}</option>
                    </c:forEach>
                </select>
            </div>
        </div>


        <div class="form-group">
            <div class="col-md-6 col-md-offset-3">
                <button type="submit" class="btn btn-primary">提交</button>
                <button id="send" type="reset" class="btn btn-success">重置</button>
                <a href="${ctx}/classServlet.do?param=show&&pageC" id="sends" type="button"
                <%--				<a href="${ctx}/page/classes/classManager.jsp" id="sends" type="button"--%>
                   class="btn btn-success">取消</a>
            </div>
        </div>
    </form>
</div>
<!-- 修改页面结束 -->
<%@ include file="../../../common/footer.jsp"%>
<script src="${ctx }/statics/localjs/ClaAdd.js"></script>
