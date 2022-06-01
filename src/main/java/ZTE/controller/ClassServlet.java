package ZTE.controller;


import ZTE.entity.Classes;
import ZTE.service.classes.ClassService;
import ZTE.service.classes.ClassServiceImpl;
import ZTE.utils.PageSupport;
import com.alibaba.fastjson.JSONArray;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@WebServlet("/classServlet")
public class ClassServlet extends HttpServlet {
    ClassService classService = new ClassServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String param = request.getParameter("param");
        if ("show".equals(param)){
            ClassShowServlet(request,response);
        }else if ("add".equals(param)){
            ClassAddServlet(request,response);
        }else if ("class".equals(param)){
            getClassList(request,response);
        }
    }

    /**
     * 获取班级列表做下拉框
     * @param request
     * @param response
     * @throws IOException
     */
    private void getClassList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int studyType = Integer.parseInt(request.getParameter("major"));
        List<Classes> classList = classService.getClassList(studyType);
        String result = JSONArray.toJSONString(classList);
        PrintWriter out = response.getWriter();
        out.write(result);
        out.flush();
        out.close();
    }

    private void ClassAddServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String classname = request.getParameter("classname");
        int studytype = Integer.parseInt(request.getParameter("studytype"));
        boolean falg=classService.addClass(classname,studytype);
        if (falg){
//            response.sendRedirect(request.getContextPath()+"/classServlet.do?param=show&&pageC");
            response.sendRedirect(request.getContextPath()+"/page/classes/classManager.jsp");

        }else {
            response.sendRedirect(request.getContextPath()+"/page/classes/classadd.jsp");
        }
    }

    private void ClassShowServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        Integer majorId = null;
        Integer currentPageNos=1;
        PageSupport pageSupport = new PageSupport();

        String currentPageNo = request.getParameter("pageC");
        String majorIds = request.getParameter("studyType");
        //查询条件
        if (majorIds!=null&&!"".equals(majorIds)){
            majorId=Integer.parseInt(request.getParameter("studyType"));
        }
        //当前页号获取
        if (currentPageNo!=null&&!"".equals(currentPageNo)){
            currentPageNos=Integer.parseInt(request.getParameter("pageC"));
        }

        //页面尺寸
        pageSupport.setPageSize(PageSupport.pageSizeVal);
        Integer pageSize = pageSupport.getPageSize();
        //总记录数
        int classListCout = classService.getClassListCount(majorId);

        //总页数
        pageSupport.setTotalCount(classListCout);
        int totalPageCount = pageSupport.getTotalPageCount();
        if (currentPageNos>totalPageCount){
            currentPageNos=totalPageCount;
        }
        if (currentPageNos<1){
            currentPageNos=1;
        }
        //获取所有部门
        ArrayList<Classes> studyTypeAll = classService.getStudyTypeAll();
        //查询条件 1.查询id是否空 2.当前页号  3.尺寸
        ArrayList<Classes> classList = classService.getClassList(majorId,currentPageNos,pageSize);
        HashMap<Object, Object> pageinfos = new HashMap<>();
        pageinfos.put("currentPageNo",currentPageNos);
        pageinfos.put("classListCout",classListCout);
        pageinfos.put("totalPageCount",totalPageCount);

        request.setAttribute("pageinfos",pageinfos);
        request.setAttribute("classList",classList);
        request.setAttribute("majorId",majorId);
        request.getSession().setAttribute("studyTypeAll",studyTypeAll);
        request.getRequestDispatcher("page/classes/classManager.jsp").forward(request,response);
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
//        response.setContentType("application/json;charset=utf-8");
//        PrintWriter out = response.getWriter();


//        PageSupport pageSupport = new PageSupport();
//        String s = JSONArray.toJSONString(classList);
//        System.out.println(s);
//        out.print(s);
//        out.flush();
//        out.close();


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
