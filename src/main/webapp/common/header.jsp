<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ page import="ZTE.listener.OnlineCounter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE HTML >
<html>
  <head>
    
    <title>My JSP 'header.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${pageContext.request.contextPath}/statics/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${pageContext.request.contextPath}/statics/css/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="${pageContext.request.contextPath}/statics/css/green.css" rel="stylesheet">
    <!-- bootstrap-progressbar -->
    <link href="${pageContext.request.contextPath}/statics/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- JQVMap -->
    <link href="${pageContext.request.contextPath}/statics/css/jqvmap.min.css" rel="stylesheet"/>

    <!-- Custom Theme Style -->
    <link href="${pageContext.request.contextPath}/statics/css/custom.min.css" rel="stylesheet">
    
    <link href="${pageContext.request.contextPath}/statics/localcss/appinfolist.css" rel="stylesheet">
      <script>
          var myVar=setInterval(function(){myTimer()},1000);
          function myTimer(){
              var d=new Date();
              var t=d.toLocaleDateString()+ " "+d.toLocaleTimeString();
              document.getElementById("time").innerHTML=t;
          }
      </script>


  </head>
<body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="login-1.jsp" class="site_title"><span>中兴CRM系统</span></a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile">
              <div class="profile_info">
                  <h2>欢迎您:${ loginsession.logincode },登录!</h2>
              </div>
            </div>
            <!-- /menu profile quick info -->

            <br />

            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <h3>${devUserSession.devName}</h3>
                <ul class="nav side-menu" style="clear: both;">
          <!--         <li><a><i class="fa fa-home"></i> APP账户管理 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="index.html">APP开发者账户申请</a></li>
                      <li><a href="index2.html">个人账户信息维护</a></li>
                    </ul>
                  </li>
            --> 
                  <li><a><i class="fa fa-edit"></i> 学员管理 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="${ctx}/studentServlet?param=cha">学生管理</a></li>
                      <li><a href="${ctx}/studentServlet?param=stuRegister">学生注册</a></li>
<%--                      <li><a href="${ctx}/jsp/yichen/classManager.jsp">班级管理</a></li>--%>
                      <li><a href="${ctx}/classServlet.do?param=show">班级管理</a></li>
                       <li><a href="${ctx}/examServlet?operate=init&examType=1">晨考成绩录入</a></li>
                       <li><a href="${ctx}/examServlet?operate=init&examType=2">周考成绩录入</a></li>
                       <li><a href="${ctx}/attServlet?operate=init&aim=1">考勤管理</a></li>
                       <li><a href="${ctx}/workrecordservlet">每日作业完成统计录入</a></li>
                    </ul>
                  </li>
                  
                   <li><a><i class="fa fa-question-circle"></i> 报表查询<span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="${ctx}/resultServlet?operate=init&examType=1">晨考查询</a></li>
                      <li><a href="${ctx}/resultServlet?operate=init&examType=2">周考查询</a></li>
                      <li><a href="${ctx}/attServlet?operate=init&aim=2">考勤查询</a></li>
                      <li><a href="${ctx}/workinfoservlet">每日作业完成情况查询</a></li>
                    </ul>
                  </li>
                 <!--   <li><a><i class="fa fa-edit"></i> 权限管理 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="${ctx}/jsp/aaaa.jsp">角色管理</a></li>
                      <li><a href="${ctx}/chaStudentServlet.do">权限维护管理</a></li>
                    </ul>
                   </li> -->
                    <li><a><i class="fa fa-plug"></i>市场管理 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="${ctx}/MarketClassServlet.do?caozuo=cha">市场班级管理</a></li>
                      <li><a href="${ctx}/MarketStudentServlet.do?caozuo=cha">市场学生管理</a></li>
                    </ul>
                   </li>
                   <!--  
                   <li><a><i class="fa fa-plug"></i>宿舍管理 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="${ctx}/chaStudentServlet.do">高校日常管理</a></li>
                      <li><a href="${ctx}/chaStudentServlet.do">训练营日常管理</a></li>
                    </ul>
                   </li>-->
                </ul>
              </div>

            </div>
            <!-- /sidebar menu -->

            <!-- /menu footer buttons -->
            <div class="sidebar-footer hidden-small">
              <a data-toggle="tooltip" data-placement="top" title="Settings">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Lock">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" href="${ctx}/offservlet" data-placement="top" title="Logout">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
              </a>
            </div>
            <!-- /menu footer buttons -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">
            <nav>
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>

              <ul class="nav navbar-nav navbar-right">
                <li class="">
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    <img src="${pageContext.request.contextPath}/statics/images/img.jpg" alt="">
                      <h3 style="display: inline;">${loginsession.logincode}</h3>
                      <span class=" fa fa-angle-down"></span>
                  </a>
                  <ul class="dropdown-menu dropdown-usermenu pull-right">
                    <li><a href="${ctx}/logout" ><i class="fa fa-sign-out pull-right"></i> 注销</a></li>
                  </ul>
                </li>
                  <li>
                      <h4 style="padding-top: 10px">&nbsp;&nbsp;在线人数：<%=OnlineCounter.getOnline()%>&nbsp;</h4>
                  </li>
                <li>
                  <h3 id="time" style="padding-top: 8px"></h3>
                </li>
              </ul>
            </nav>
          </div>
        </div>
        <!-- /top navigation -->
        	<div class="right_col" role="main">
				<div class="">
