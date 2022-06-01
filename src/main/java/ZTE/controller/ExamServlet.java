package ZTE.controller;

import ZTE.entity.Classes;
import ZTE.entity.Major;
import ZTE.entity.Student;
import ZTE.entity.exam.ExamUpload;
import ZTE.service.classes.ClassService;
import ZTE.service.classes.ClassServiceImpl;
import ZTE.service.exam.ExamService;
import ZTE.service.exam.ExamServiceImpl;
import ZTE.service.student.StudentService;
import ZTE.service.student.StudentServiceImpl;
import ZTE.utils.ExamUtil;
import ZTE.utils.PageSupport;
import ZTE.utils.excel.exam.ExamSheetSelectWriteHandler;
import ZTE.utils.excel.exam.ExamTemplate;
import ZTE.utils.excel.lisitener.ExamListener;
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

@WebServlet("/examServlet")
@MultipartConfig
public class ExamServlet extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();
    ClassService classService = new ClassServiceImpl();
    ExamService examService = new ExamServiceImpl();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String examDate = sdf.format(new Date());// 获取到今天的日期

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operate = request.getParameter("operate");
        if ("init".equals(operate)) {
            getMajorList(request, response);
        } else if ("cha".equals(operate)) {
            getStudentListWithoutResult(request, response);
        } else if ("input".equals(operate)) {
            addResultInfo(request, response);
        } else if ("template".equals(operate)) {
            downloadTemplate(request, response);
        } else if ("upload".equals(operate)) {
            uploadFile(request, response);
        }

    }

    /**
     * 上传成绩表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void uploadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //文件上传
        //首先要在Servlet上用 @MultipartConfig 标识支持文件上传
        //获取part对象 参数为name属性的值
        Part part = request.getPart("excelFile");
        // 获取考试类型
        int examType = Integer.parseInt(request.getParameter("examType"));
        //获取文件名
        String fileName = part.getSubmittedFileName();
        //获取数据的流
        InputStream inputStream = part.getInputStream();
        request.setAttribute("servletName","examServlet?operate=init&examType="+examType);
        try {
            EasyExcel.read(inputStream, ExamUpload.class, new ExamListener())
                    .sheet()
                    .doRead();

            request.getRequestDispatcher("/common/successUpload.jsp").forward(request,response);
        } catch (Exception e) {
            request.getRequestDispatcher("/common/FailUpload.jsp").forward(request,response);
            e.printStackTrace();
        }

    }

    /**
     * 提供下载考试成绩导入的模板
     * @param request
     * @param response
     * @throws IOException
     */
    private void downloadTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<ExamUpload> examUploadList  = new ArrayList<>();
        int examType = Integer.parseInt(request.getParameter("examType"));
        String examTypeName = ExamUtil.tansExamTypeIdToName(examType);
        examUploadList.add(new ExamUpload(1001,"张全蛋","2022-05-30",examTypeName,"非常好"));
        //设置Excel响应头，点击按钮时弹出保存框
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //下载的Excel文件名
        String fileName = URLEncoder.encode("考试成绩上传模板.xlsx","UTF-8");
        response.setHeader("Content-disposition","allachment;filename="+fileName);
        ExamSheetSelectWriteHandler examSheetSelectWriteHandler = new ExamSheetSelectWriteHandler();
        EasyExcel.write(response.getOutputStream(), ExamTemplate.class)
                .registerWriteHandler(examSheetSelectWriteHandler)
                .registerWriteHandler(ResultServlet.getWriteStyle())
                .sheet("考试成绩上传模版")
                .doWrite(examUploadList);
    }

    /**
     * 向成绩表添加数据
     * @param request
     * @param response
     * @throws IOException
     */
    private void addResultInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = request.getParameter("result");
        int examType = Integer.parseInt(request.getParameter("examType"));
        String[] studentNoData = request.getParameterValues("studentNoData[]");
        boolean flag = examService.addResult(result, studentNoData, examType, examDate);
        if (flag) {
            PrintWriter out = response.getWriter();
            out.print("success");
            out.flush();
            out.close();
        }
    }

    /**
     * 获取专业集合，用于做下拉框
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void getMajorList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Major> majorList = classService.getMajorList();
        int examType = Integer.parseInt(request.getParameter("examType"));
        request.setAttribute("majorList", majorList);
        if (examType == 1) {
            request.getRequestDispatcher("/jsp/inputMorningResultInfo.jsp").forward(request, response);
        } else if (examType == 2) {
            request.getRequestDispatcher("/jsp/inputWeekResultInfo.jsp").forward(request, response);
        }
    }

    /**
     * 查询到所有未录入成绩的学生并分页显示
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void getStudentListWithoutResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建页面对象，用于存储页面信息
        PageSupport pageInfo = new PageSupport();
        String className = request.getParameter("className");// 获取到需要查询的班级名称
        String pageIndex = request.getParameter("pageIndex");// 获取到当前页数
        int examType = Integer.parseInt(request.getParameter("examType"));// 获取到当前考试类型
        // 当前页数为空时，赋1
        if (pageIndex == null) {
            pageIndex = "1";
        }
        int currentPage = Integer.parseInt(pageIndex);
        int totalCount = studentService.getStudentCountByClassName(className, examType, examDate);// 获取总记录数
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
        List<Student> studentsList = studentService.getStudentListWithoutResult(className, examType, examDate, pageSize, currentPage);
        int resultCountToday = examService.getResultCountTodayByClass(examDate, className);

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
        request.setAttribute("examType", examType);
        if (examType == 1) {
            request.getRequestDispatcher("/jsp/inputMorningResultInfo.jsp").forward(request, response);
        } else if (examType == 2) {
            request.getRequestDispatcher("/jsp/inputWeekResultInfo.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
