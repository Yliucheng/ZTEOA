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
        //获取文件对象
        Part excelFile = request.getPart("excelFile");
        //读取文件名
        String fileName = excelFile.getSubmittedFileName();
        System.out.println(fileName);
        //
        InputStream inputStream = excelFile.getInputStream();
        /**
         * 参数1 inputSteam 获取文件路径
         * 参数2 ClassImportData.class 读取每一行的实体类型
         * 参数3 listener 用于操作每行读取的数据
         */

        request.setAttribute("servletName","classServlet.do?param=show");
        try {
            //返回一个工作普对象
            ExcelReaderBuilder read = EasyExcel.read(inputStream, ClassImportData.class, new ClassListener());
            //返回一个工作表对象
            ExcelReaderSheetBuilder sheet = read.sheet();
            //读取
            sheet.doRead();
            request.getRequestDispatcher("/common/successUpload.jsp").forward(request,response);
        } catch (Exception e) {
            request.getRequestDispatcher("/common/FailUpload.jsp").forward(request,response);
            e.printStackTrace();
        }

    }

    private void excelModelServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");//post 设置编码utf8编码格式防止 excel导入乱码
        response.setCharacterEncoding("utf-8");
        //获取现在时间来命名Excel表格的名字

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        //获取查询员工表数据
        List<Classes> employeeListss = new ArrayList<>();
        employeeListss.add(new Classes("java1班",1));
        //设置excel响应头
//        System.out.println(employeeLi.get(0).getClassName());
        //下载excel名称
//        String filename="employee"+format+".xlsx";
        String filename= URLEncoder.encode("下载班级导入模板"+format+".xlsx","UTF-8");
        response.setHeader("Content-disposition","allachment;filename="+filename);
        EasyExcel.write(response.getOutputStream(),ClassTemplateExport.class)
                .registerWriteHandler(new ClassSheetSelectWriteHandler())
                .registerWriteHandler(StudentServlet.getWriteStyle())
                .sheet("班级信息")
                .doWrite(employeeListss);
    }

    private void excelExportServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");//post 设置编码utf8编码格式防止 excel导入乱码
        response.setCharacterEncoding("utf-8");
        //获取现在时间来命名Excel表格的名字

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        //获取查询员工表数据
//        request.getSession().setAttribute("classList",classList);//班级信息
        List<Classes> employeeLi =(ArrayList<Classes>) request.getSession().getAttribute("classList2");
        //设置excel响应头
        System.out.println(employeeLi.get(0).getClassName());
        //下载excel名称
//        String filename="employee"+format+".xlsx";
        String filename="classes"+simpleDateFormat+".xlsx";
        response.setHeader("Content-disposition","allachment;filename="+filename);
        EasyExcel.write(response.getOutputStream(),ClassExport.class)
                .registerWriteHandler(StudentServlet.getWriteStyle())
                .sheet("班级信息")
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
        表单验证
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
     * 删除指定班级
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
     *删除之前查询该班级是否还存在学生
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
     * 修改班级信息
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
     * 修改之前获取专业类型
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
     * 添加之前获取专业类型
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
     * 班级添加
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
     * 查询所有的编辑信息
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
        //查询条件
        if (majorIds!=null&&!"".equals(majorIds)){
            majorId=Integer.parseInt(request.getParameter("studyType"));
        }
        //当前页号获取
        if (currentPageNo!=null&&!"".equals(currentPageNo)){
            currentPageNos=Integer.parseInt(request.getParameter("pageIndex"));
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
        //设置当前页号
        pageSupport.setCurrentPageNo(currentPageNos);
        //获取所有部门
        ArrayList<Classes> studyTypeAll = classService.getStudyTypeAll();
        //查询条件 1.查询id是否空 2.当前页号  3.尺寸
        ArrayList<Classes> classList = classService.getClassList(majorId,currentPageNos,pageSize);
        //导出不分页导出所有数据
        ArrayList<Classes> classList2 = classService.getClassList2(majorId);

        request.setAttribute("pageInfo",pageSupport);//页面信息
//        request.setAttribute("classList",classList);//班级信息
        request.getSession().setAttribute("classList",classList);//班级信息
        request.getSession().setAttribute("classList2",classList2);//班级信息
        request.setAttribute("majorId",majorId);//上一次的查询结果
        request.getSession().setAttribute("studyTypeAll",studyTypeAll);//查询条件
        request.getRequestDispatcher("jsp/yichen/classManager.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
