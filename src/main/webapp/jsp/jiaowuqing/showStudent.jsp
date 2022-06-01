<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../../common/header.jsp" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    function deleteAct(item){
        return confirm("确认删除 【"+item+"】 这个学生吗？");
    }
</script>
<!-- appInfoList页面开始 -->
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_content">
                <br/>
                <form id="query" data-parsley-validate
                      class="form-horizontal form-label-left demo-form2"
                      method="post" action="studentServlet">
                    <!-- 隐藏域，用于传递分页的当前页号 -->
                    <input type="hidden" name="pageIndex" value="1"/>
                    <input type="hidden" name="param" value="cha"/>
                    <ul style="list-style:none;display:inline_block;">
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">专业 </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="zhuanye"
                                        class="form-control col-md-7 col-xs-12">
                                        <option value="">---请选择---</option>
                                        <c:forEach var="major" items="${majorList}">
                                            <option value="${major.id}" ${major.id eq zhuanye?"selected":""}>${major.major} </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">班级 </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="banji"
                                        class="form-control col-md-7 col-xs-12">
                                        <option value="">---请选择---</option>
                                        <c:forEach var="classes" items="${classesList}">
                                            <option value="${classes.classId}" ${classes.classId eq banji?"selected":""}>${classes.className} </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">姓名</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" name="name" id="name" placeholder="输入2-5位中文或英文"
                                           class="form-control col-md-7 col-xs-12"
                                           value="${name}">
                                </div>
                                <span id="nameMes"></span>
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
            <a href="studentServlet?param=stuRegister" class="btn btn-success btn-sm">注册学生信息</a>
            <a href="studentServlet?param=exportexcel&major=${zhuanye}&classes=${banji}&name=${name}" class="btn btn-success btn-sm">导出当前查询信息</a>
            <a href="studentServlet?param=templateExcel" class="btn btn-success btn-sm">下载导入模版</a>&nbsp;
            <form id="upload" action="studentServlet?param=upload" enctype="multipart/form-data" method="post" style="display: inline">
                <button href="#" style="display: inline" id="shangchuan" class="btn btn-success btn-sm">上传</button>
                <input type="file" id="excelFile" name="excelFile" style="display: none" multiple value="点击上传学生表">
                <button type="submit" id="toSubmit" onclick="" style="display: none" class="btn btn-success btn-sm">上传</button>
            </form>
            <table id="datatable" class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>专业</th>
                    <th>班级</th>
                    <th>来自院校</th>
                    <th>学历</th>
                    <th style="width:125px;">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="student" items="${studentList}">
                    <tr>
                        <td>${student.studentid}</td>
                        <td>${student.studentName}</td>
                        <td>${student.major}</td>
                        <td>${student.className}</td>
                        <td>${student.fromSchool}</td>
                        <td>${student.education}</td>
                        <td>
                            <div class="btn-group">
                                <button type="button" class="btn btn-danger" data-toggle="tooltip">点击操作</button>
                                <button type="button" class="btn btn-danger dropdown-toggle"
                                        data-toggle="dropdown" aria-expanded="false">
                                    <span class="caret"></span> <span class="sr-only">Toggle
											Dropdown</span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="modifyAppInfo"
                                           data-toggle="tooltip" data-placement="top" title=""
                                           data-original-title="修改APP基础信息" href="studentServlet?param=toUpdate&&id=${student.studentid}">修改</a></li>
                                    <li><a href="studentServlet?param=toAdd">增加</a></li>
                                    <li><a href="studentServlet?param=delete&&id=${student.studentid}"
                                           onclick="return deleteAct('${student.studentName}');">删除</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <tr>
                    <td colspan="10" style="text-align: right;">
				   	  <span>共有${pageInfo.totalCount}条数据,
				   	  ${pageInfo.currentPageNo}/${pageInfo.totalPageCount}页</span>
                        <a href="javascript:page_nav(document.forms[0],1);">首页</a>&nbsp;
                        <a href="javascript:page_nav(document.forms[0],${pageInfo.currentPageNo}-1);">上一页</a>&nbsp;
                        <a href="javascript:page_nav(document.forms[0],${pageInfo.currentPageNo}+1);">下一页</a>&nbsp;
                        <a href="javascript:page_nav(document.forms[0],${pageInfo.totalPageCount});">末页</a>&nbsp;
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
<%@ include file="../../common/footer.jsp" %>
<script src="${pageContext.request.contextPath}/statics/localjs/rollpage.js"></script>
<script src="${pageContext.request.contextPath}/statics/localjs/student/showStudent.js"></script>
<script src="${pageContext.request.contextPath}/statics/localjs/student/showCheckName.js"></script>

