package ZTE.controller.yichen;


import ZTE.controller.jiaowuqing.StudentServlet;
import ZTE.entity.yichen.Classes;
import ZTE.service.yichen.classes.ClassService;
import ZTE.service.yichen.classes.ClassServiceImpl;
import ZTE.utils.PageSupport;
import ZTE.utils.excel.classes.*;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.fastjson.JSONArray;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@WebServlet("/classServlet.do")
@MultipartConfig
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
        }else if ("toadd".equals(param)){
            toaddClassServlet(request,response);
        }else if ("toModify".equals(param)){
            toModifyClassServlet(request,response);
        }else if ("modify".equals(param)){
            modifyClassServlet(request,response);
        }else if ("todelete".equals(param)){
            toDeleteClassServlet(request,response);
        }else if("delete".equals(param)){
            deleteClassServlet(request,response);
        }else if("pd".equals(param)){
            SearchPdServlet(request,response);
        }else if ("chaClassNameJson".equals(param)) {
            chaClassNameJsonServlet(request,response);
        }else if ("excelExport".equals(param)){
            excelExportServlet(request,response);
        }else if("excelModel".equals(param)){
            excelModelServlet(request,response);
        }else if("excelImport".equals(param)){
            excelImportServlet(request,response);
        }

    }

    private void excelImportServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //??????????????????
        Part excelFile = request.getPart("excelFile");
        //???????????????
        String fileName = excelFile.getSubmittedFileName();
        System.out.println(fileName);
        //
        InputStream inputStream = excelFile.getInputStream();
        /**
         * ??????1 inputSteam ??????????????????
         * ??????2 ClassImportData.class ??????????????????????????????
         * ??????3 listener ?????????????????????????????????
         */

        request.setAttribute("servletName","classServlet.do?param=show");
        try {
            //???????????????????????????
            ExcelReaderBuilder read = EasyExcel.read(inputStream, ClassImportData.class, new ClassListener());
            //???????????????????????????
            ExcelReaderSheetBuilder sheet = read.sheet();
            //??????
            sheet.doRead();
            request.getRequestDispatcher("/common/successUpload.jsp").forward(request,response);
        } catch (Exception e) {
            request.getRequestDispatcher("/common/FailUpload.jsp").forward(request,response);
            e.printStackTrace();
        }

    }

    private void excelModelServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");//post ????????????utf8?????????????????? excel????????????
        response.setCharacterEncoding("utf-8");
        //???????????????????????????Excel???????????????

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        //???????????????????????????
        List<Classes> employeeListss = new ArrayList<>();
        employeeListss.add(new Classes("java1???",1));
        //??????excel?????????
//        System.out.println(employeeLi.get(0).getClassName());
        //??????excel??????
//        String filename="employee"+format+".xlsx";
        String filename= URLEncoder.encode("????????????????????????"+format+".xlsx","UTF-8");
        response.setHeader("Content-disposition","allachment;filename="+filename);
        EasyExcel.write(response.getOutputStream(),ClassTemplateExport.class)
                .registerWriteHandler(new ClassSheetSelectWriteHandler())
                .registerWriteHandler(StudentServlet.getWriteStyle())
                .sheet("????????????")
                .doWrite(employeeListss);
    }

    private void excelExportServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");//post ????????????utf8?????????????????? excel????????????
        response.setCharacterEncoding("utf-8");
        //???????????????????????????Excel???????????????

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        //???????????????????????????
//        request.getSession().setAttribute("classList",classList);//????????????
        List<Classes> employeeLi =(ArrayList<Classes>) request.getSession().getAttribute("classList2");
        //??????excel?????????
        System.out.println(employeeLi.get(0).getClassName());
        //??????excel??????
//        String filename="employee"+format+".xlsx";
        String filename="classes"+simpleDateFormat+".xlsx";
        response.setHeader("Content-disposition","allachment;filename="+filename);
        EasyExcel.write(response.getOutputStream(),ClassExport.class)
                .registerWriteHandler(StudentServlet.getWriteStyle())
                .sheet("????????????")
                .doWrite(employeeLi);

    }


    /*

     */
    private void chaClassNameJsonServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Classes> classList = classService.getClassList();
        String s = JSONArray.toJSONString(classList);
        PrintWriter writer = response.getWriter();
        writer.print(s);
        writer.flush();
        writer.close();
    }
    /*
        ????????????
     */
    private void SearchPdServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String classname = request.getParameter("classname");
        boolean flag=classService.PanDuanClassName(classname);
        String s = JSONArray.toJSONString(flag);
        out.print(s);
        out.flush();
        out.close();
    }

    /**
     * ??????????????????
     * @param request
     * @param response
     * @throws IOException
     */
    private void deleteClassServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        int classid = Integer.parseInt(request.getParameter("classid"));
        boolean b = classService.DeleteClass(classid);
        System.out.println(b);
        out.println(b);
        out.flush();
        out.close();
    }

    /**
     *????????????????????????????????????????????????
     * @param request
     * @param response
     * @throws IOException
     */
    private void toDeleteClassServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        int classid = Integer.parseInt(request.getParameter("classid"));
        boolean b = classService.getClassStduentCount(classid);
//        String s = JSON.toJSONString(b);
        System.out.println(b);
        out.println(b);
        out.flush();
        out.close();


    }

    /**
     * ??????????????????
     * @param request
     * @param response
     * @throws IOException
     */
    private void modifyClassServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        int classid = Integer.parseInt(request.getParameter("classid"));
        String classname = request.getParameter("classname");
        int studytype = Integer.parseInt(request.getParameter("studytype"));
        Classes classes = new Classes(classid, classname, studytype);
        boolean falg=classService.ModifyClass(classes);
        if (falg){
            response.sendRedirect(request.getContextPath()+"/classServlet.do?param=show");

        }else {
            response.sendRedirect(request.getContextPath()+"/jsp/yichen/classadd.jsp");
        }
    }

    /**
     * ??????????????????????????????
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void toModifyClassServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        request.setAttribute("params","modify");
        int id = Integer.parseInt(request.getParameter("id"));
        Classes classes=classService.getClassList(id);
        request.setAttribute("classes",classes);
        request.getRequestDispatcher("jsp/yichen/classadd.jsp").forward(request,response);
    }

    /**
     * ??????????????????????????????
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void toaddClassServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        request.setAttribute("params","add");
        request.getRequestDispatcher("jsp/yichen/classadd.jsp").forward(request,response);
    }

    /**
     * ????????????
     * @param request
     * @param response
     * @throws IOException
     */
    private void ClassAddServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String classname = request.getParameter("classname");
        int studytype = Integer.parseInt(request.getParameter("studytype"));
        boolean falg=classService.addClass(classname,studytype);
        if (falg){
            response.sendRedirect(request.getContextPath()+"/classServlet.do?param=show");
//            response.sendRedirect(request.getContextPath()+"/page/classes/classManager.jsp");

        }else {
            response.sendRedirect(request.getContextPath()+"/jsp/yichen/classadd.jsp");
        }
    }

    /**
     * ???????????????????????????
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void ClassShowServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        Integer majorId = null;
        Integer currentPageNos=1;
        PageSupport pageSupport = new PageSupport();

        String currentPageNo = request.getParameter("pageIndex");
        String majorIds = request.getParameter("studyType");
        //????????????
        if (majorIds!=null&&!"".equals(majorIds)){
            majorId=Integer.parseInt(request.getParameter("studyType"));
        }
        //??????????????????
        if (currentPageNo!=null&&!"".equals(currentPageNo)){
            currentPageNos=Integer.parseInt(request.getParameter("pageIndex"));
        }

        //????????????
        pageSupport.setPageSize(PageSupport.pageSizeVal);
        Integer pageSize = pageSupport.getPageSize();
        //????????????
        int classListCout = classService.getClassListCount(majorId);

        //?????????
        pageSupport.setTotalCount(classListCout);
        int totalPageCount = pageSupport.getTotalPageCount();
        if (currentPageNos>totalPageCount){
            currentPageNos=totalPageCount;
        }
        if (currentPageNos<1){
            currentPageNos=1;
        }
        //??????????????????
        pageSupport.setCurrentPageNo(currentPageNos);
        //??????????????????
        ArrayList<Classes> studyTypeAll = classService.getStudyTypeAll();
        //???????????? 1.??????id????????? 2.????????????  3.??????
        ArrayList<Classes> classList = classService.getClassList(majorId,currentPageNos,pageSize);
        //?????????????????????????????????
        ArrayList<Classes> classList2 = classService.getClassList2(majorId);

        request.setAttribute("pageInfo",pageSupport);//????????????
//        request.setAttribute("classList",classList);//????????????
        request.getSession().setAttribute("classList",classList);//????????????
        request.getSession().setAttribute("classList2",classList2);//????????????
        request.setAttribute("majorId",majorId);//????????????????????????
        request.getSession().setAttribute("studyTypeAll",studyTypeAll);//????????????
        request.getRequestDispatcher("jsp/yichen/classManager.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
