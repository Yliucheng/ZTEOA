package ZTE.controller;

import ZTE.entity.Classes;
import ZTE.entity.Major;
import ZTE.entity.QueryParams;
import ZTE.entity.Student;
import ZTE.entity.att.AttInfo;
import ZTE.entity.att.AttUpload;
import ZTE.service.att.AttService;
import ZTE.service.att.AttServiceImpl;
import ZTE.service.classes.ClassService;
import ZTE.service.classes.ClassServiceImpl;
import ZTE.service.student.StudentService;
import ZTE.service.student.StudentServiceImpl;
import ZTE.utils.ExamUtil;
import ZTE.utils.PageSupport;
import ZTE.utils.excel.att.AttInfoExcel;
import ZTE.utils.excel.att.AttSheetSelectWriteHandler;
import ZTE.utils.excel.att.AttTemplate;
import ZTE.utils.excel.lisitener.AttListener;
import com.alibaba.excel.EasyExcel;
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

@WebServlet("/attServlet")
@MultipartConfig
public class AttServlet extends HttpServlet {
    ClassService classService = new ClassServiceImpl();
    StudentService studentService = new StudentServiceImpl();
    AttService attService = new AttServiceImpl();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String attDate = sdf.format(new Date());// 获取到今天的日期

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operate = request.getParameter("operate");
        if ("init".equals(operate)) {
            getMajorList(request, response);
        } else if ("cha".equals(operate)) {
            getStudentListWithoutInfo(request, response);
        } else if ("input".equals(operate)) {
            addAttInfo(request, response);
        } else if ("get".equals(operate)) {
            getStudentListWithInfo(request, response);
        } else if ("change".equals(operate)) {
            changeAttInfo(request, response);
        } else if ("del".equals(operate)) {
            delAttInfo(request, response);
        } else if ("export".equals(operate)) {
            exportAttInfoExcel(request, response);
        } else if ("template".equals(operate)) {
            downloadTemplate(request, response);
        } else if ("upload".equals(operate)) {
            uploadFile(request, response);
        }
    }

    private void uploadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //文件上传
        //首先要在Servlet上用 @MultipartConfig 标识支持文件上传
        //获取part对象 参数为name属性的值
        Part part = request.getPart("excelFile");
        //获取文件名
        String fileName = part.getSubmittedFileName();
        //获取数据的流
        InputStream inputStream = part.getInputStream();
        request.setAttribute("servletName","attServlet?operate=init&aim=1");
        try {
            EasyExcel.read(inputStream, AttUpload.class, new AttListener())
                    .sheet()
                    .doRead();
            request.getRequestDispatcher("/common/successUpload.jsp").forward(request,response);
        } catch (Exception e) {
            request.getRequestDispatcher("/common/FailUpload.jsp").forward(request,response);
            e.printStackTrace();
        }

    }

    /**
     * 提供导入模板下载
     * @param request
     * @param response
     * @throws IOException
     */
    private void downloadTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<AttUpload> attUploadList  = new ArrayList<>();
        attUploadList.add(new AttUpload(1001,"张全蛋","2022-05-30","正常"));
        //设置Excel响应头，点击按钮时弹出保存框
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //下载的Excel文件名
        String fileName = URLEncoder.encode("考勤表上传模板.xlsx","UTF-8");
        response.setHeader("Content-disposition","allachment;filename="+fileName);
        AttSheetSelectWriteHandler attSheetSelectWriteHandler = new AttSheetSelectWriteHandler();
        EasyExcel.write(response.getOutputStream(), AttTemplate.class)
                .registerWriteHandler(attSheetSelectWriteHandler)
                .registerWriteHandler(ResultServlet.getWriteStyle())
                .sheet("考勤表上传模版")
                .doWrite(attUploadList);
    }

    /**
     * 导出表格
     *
     * @param request
     * @param response
     */
    private void exportAttInfoExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String className = request.getParameter("className");// 获取到需要查询的班级名称
        String namec = request.getParameter("namec");// 获取到需要查询的学生姓名
        String date = request.getParameter("date");// 获取到需要查询的日期
        String ckStatus = request.getParameter("ckStatu");// 获取到需要查询的考勤状态
        QueryParams queryParams = new QueryParams(className, namec, date, ckStatus);
        List<AttInfo> attInfoList = attService.getAttInfoList(queryParams);

        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取到时间，以时间来定义下载Excel的名称
        String downLoadTime=sf.format(new Date());
        //设置Excel响应头
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //下载的Excel文件名称
        String fileName="attendence"+downLoadTime+".xlsx";
        response.setHeader("Content-disposition","allachment;filename="+fileName);
        EasyExcel.write(response.getOutputStream(),AttInfoExcel.class)
                .registerWriteHandler(ResultServlet.getWriteStyle())
                .sheet("考勤信息")
                .doWrite(attInfoList);
    }

    /**
     * 删除数据
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void delAttInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean flag = attService.delAttInfo(id);
        if (flag) {
            PrintWriter out = response.getWriter();
            out.print("success");
            out.flush();
            out.close();
        }
    }

    /**
     * 修改考勤表
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void changeAttInfo(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String attDate = request.getParameter("attDate");
        String ckStatus = request.getParameter("attStatus");
        boolean flag = attService.changeAttInfo(attDate, ckStatus, id);
        if (flag) {
            PrintWriter out = response.getWriter();
            out.print("success");
            out.flush();
            out.close();
        }
    }

    /**
     * 查询考勤表信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void getStudentListWithInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageSupport pageInfo = new PageSupport();
        String className = request.getParameter("className");// 获取到需要查询的班级名称
        String namec = request.getParameter("namec");// 获取到需要查询的学生姓名
        String date = request.getParameter("date");// 获取到需要查询的日期
        String ckStatus = request.getParameter("ckStatu");// 获取到需要查询的考勤状态
        String pageIndex = request.getParameter("pageIndex");// 获取到当前页数
        int majorId = Integer.parseInt(request.getParameter("major"));

        // 当前页数为空时，赋1
        if (pageIndex == null) {
            pageIndex = "1";
        }
        int currentPage = Integer.parseInt(pageIndex);
        // 查询条件的对象
        QueryParams queryParams = new QueryParams(className, namec, date, ckStatus);
        int totalCount = attService.getTotalAttCount(queryParams);// 获取总记录数
        int pageSize = PageSupport.pageSizeVal;// 获取页面尺寸
        pageInfo.setPageSize(pageSize);// 存入页面尺寸
        pageInfo.setTotalCount(totalCount);// 存入总记录数，同时会计算出总页面数
        int totalPageCount = pageInfo.getTotalPageCount();
        // 如果当前页小于1或者没有查询到数据(即页面总数量为0)，设置为1,否则第二个条件成立会将currentPage置为0；如果大于页面总数量，则设置为页面总数量
        if (currentPage < 1 || totalPageCount == 0) {
            currentPage = 1;
        } else if (currentPage > totalPageCount) {
            currentPage = totalPageCount;
        }
        pageInfo.setCurrentPageNo(currentPage);

        List<AttInfo> attInfoList = attService.getAttInfoList(queryParams, pageSize, currentPage);

        List<Major> majorList = classService.getMajorList();// 获取专业集合用于回显
        List<Classes> classList = classService.getClassList(majorId);// 获取班级集合用于回显

        // 回显数据
        request.setAttribute("majorList", majorList);
        request.setAttribute("mId", majorId);
        request.setAttribute("classList", classList);
        request.setAttribute("cName", className);
        request.setAttribute("namec", namec);
        request.setAttribute("date", date);
        request.setAttribute("ckStatu", ckStatus);

        request.setAttribute("attInfoList", attInfoList);
        request.setAttribute("pageInfo", pageInfo);
        request.getRequestDispatcher("/report/reportAttResultInfo.jsp").forward(request, response);
    }

    /**
     * 向考勤表添加信息
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void addAttInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = request.getParameter("result");
        String[] studentNoData = request.getParameterValues("studentNoData[]");
        boolean flag = attService.addAttInfo(result, studentNoData, attDate);
        if (flag) {
            PrintWriter out = response.getWriter();
            out.print("success");
            out.flush();
            out.close();
        }
    }

    /**
     * 获取到没有考勤信息的学生集合
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void getStudentListWithoutInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建页面对象，用于存储页面信息
        PageSupport pageInfo = new PageSupport();
        String className = request.getParameter("className");// 获取到需要查询的班级名称
        String pageIndex = request.getParameter("pageIndex");// 获取到当前页数
        // 当前页数为空时，赋1
        if (pageIndex == null) {
            pageIndex = "1";
        }
        int currentPage = Integer.parseInt(pageIndex);
        int totalCount = studentService.getStudentCountByClassName(className, attDate);// 获取总记录数
        int pageSize = PageSupport.pageSizeVal;// 获取页面尺寸
        pageInfo.setPageSize(pageSize);// 存入页面尺寸
        pageInfo.setTotalCount(totalCount);// 存入总记录数，同时会计算出总页面数
        int totalPageCount = pageInfo.getTotalPageCount();
        // 如果当前页小于1或者没有查询到数据(即页面总数量为0)，设置为1,否则第二个条件成立会将currentPage置为0；如果大于页面总数量，则设置为页面总数量
        if (currentPage < 1 || totalPageCount == 0) {
            currentPage = 1;
        } else if (currentPage > totalPageCount) {
            currentPage = totalPageCount;
        }
        pageInfo.setCurrentPageNo(currentPage);

        // 获取到没有成绩的学生集合
        List<Student> studentsList = studentService.getStudentListWithoutAtt(className, attDate, pageSize, currentPage);
        int resultCountToday = attService.getAttCountTodayByClass(attDate, className);

        List<Major> majorList = classService.getMajorList();// 获取专业集合用于回显
        int majorId = Integer.parseInt(request.getParameter("major"));
        List<Classes> classList = classService.getClassList(majorId);// 获取班级集合用于回显

        request.setAttribute("sList", studentsList);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("mId", majorId);
        request.setAttribute("majorList", majorList);
        request.setAttribute("cName", className);
        request.setAttribute("classList", classList);
        request.setAttribute("resultCountToday", resultCountToday);
        request.getRequestDispatcher("/jsp/inputAttResultInfo.jsp").forward(request, response);
    }

    /**
     * 获取到专业集合用于做下拉框
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void getMajorList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Major> majorList = classService.getMajorList();
        List<String> attStatusList = ExamUtil.getAttStatusList();
        int aim = Integer.parseInt(request.getParameter("aim"));
        request.setAttribute("majorList", majorList);
        request.getSession().setAttribute("attStatusList", attStatusList);
        if (aim == 1) {// aim=1为录入
            request.getRequestDispatcher("/jsp/inputAttResultInfo.jsp").forward(request, response);
        } else if (aim == 2) {// aim=2为查询
            request.getRequestDispatcher("/report/reportAttResultInfo.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
