package ZTE.controller;

import ZTE.entity.Classes;
import ZTE.entity.Major;
import ZTE.entity.QueryParams;
import ZTE.entity.exam.ExamResult;
import ZTE.entity.exam.ResultInMonth;
import ZTE.service.classes.ClassService;
import ZTE.service.classes.ClassServiceImpl;
import ZTE.service.exam.ExamService;
import ZTE.service.exam.ExamServiceImpl;
import ZTE.service.student.StudentService;
import ZTE.service.student.StudentServiceImpl;
import ZTE.utils.ExamUtil;
import ZTE.utils.PageSupport;
import ZTE.utils.excel.exam.ExamCellColorWriterHandler;
import ZTE.utils.excel.exam.ExamResultExcel;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/resultServlet")
public class ResultServlet extends HttpServlet {
    ClassService classService = new ClassServiceImpl();
    StudentService studentService = new StudentServiceImpl();
    ExamService examService = new ExamServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operate = request.getParameter("operate");
        if ("init".equals(operate)) {
            getMajorList(request, response);
        } else if ("cha".equals(operate)) {
            getExamResultList(request, response);
        } else if ("change".equals(operate)) {
            changeExamResultInfo(request, response);
        } else if ("del".equals(operate)) {
            delExamResultInfoById(request, response);
        }else if ("export".equals(operate)) {
            exportExcel(request, response);
        }else if ("month".equals(operate)) {
            exportExcelByMonth(request, response);
        }
    }

    /**
     * 按月导出学生成绩
     * @param request
     * @param response
     * @throws IOException
     */
    private void exportExcelByMonth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int examType = Integer.parseInt(request.getParameter("examType"));
        String className = request.getParameter("qClassname");
        String date = request.getParameter("qMonth");
        List<String> dateList = examService.getDayHavingResult(examType,className,date);// 满足查询要求的日期集合
        List<ResultInMonth> dataList = examService.getExportData(dateList,examType,className);// 满足要求的数据集合
        // 表头数据
        List<List<String>> header = new ArrayList<List<String>>();
        List<String> header1 = new ArrayList<>();
        List<String> header2 = new ArrayList<>();
        List<String> header3 = new ArrayList<>();
        header1.add("编号");
        header2.add("学号");
        header3.add("姓名");
        header.add(header1);
        header.add(header2);
        header.add(header3);
        for (String s : dateList) {
            List<String> headerList = new ArrayList<>();
            headerList.add(s);
            header.add(headerList);
        }
        // 表中数据
        List<List<Object>> lastList = new ArrayList<>();
        for (ResultInMonth resultInMonth : dataList) {
            List<Object> objects = new ArrayList<>();
            objects.add(resultInMonth.getId());
            objects.add(resultInMonth.getStudentid());
            objects.add(resultInMonth.getStudentname());
            for (String result : resultInMonth.getResultlist()) {
                objects.add(result);
            }
            lastList.add(objects);
        }

        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取到时间，以时间来定义下载Excel的名称
        String downLoadTime=sf.format(new Date());
        //设置Excel响应头
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //下载的Excel文件名称
        String fileName = downLoadTime + ".xlsx";
        String typeName = "";
        if(examType==1) {
            typeName = "morningExamResult";
            fileName = typeName + fileName;
        }else if(examType==2){
            typeName = "weekExamResult";
            fileName = typeName + fileName;
        }

        // 自定义指定单元格样式工具类
        ExamCellColorWriterHandler examCellColorWriterHandler = new ExamCellColorWriterHandler();

        response.setHeader("Content-disposition","allachment;filename="+fileName);
        EasyExcel.write(response.getOutputStream())
                .head(header)
                .registerWriteHandler(getWriteStyle())
                .registerWriteHandler(examCellColorWriterHandler)
                .sheet(typeName)
                .doWrite(lastList);
    }

    /**
     * 导出所查询的学生成绩
     * @param request
     * @param response
     * @throws IOException
     */
    private void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String className = request.getParameter("className");// 获取到需要查询的班级名称
        String namec = request.getParameter("namec");// 获取到需要查询的学生姓名
        String date = request.getParameter("date");// 获取到需要查询的考试日期
        String result = request.getParameter("result");// 获取到需要查询的成绩等级
        int examType = Integer.parseInt(request.getParameter("examType"));// 获取到当前考试类型
        // 查询条件的对象
        QueryParams queryParams = new QueryParams(className, namec, date, result, examType);
        List<ExamResult> resList = examService.getResultList(queryParams);

        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取到时间，以时间来定义下载Excel的名称
        String downLoadTime=sf.format(new Date());


        //设置Excel响应头
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //下载的Excel文件名称
        String fileName = downLoadTime + ".xlsx";
        String typeName = "";
        if(examType==1) {
            typeName = "morningExamResult";
            fileName = typeName + fileName;
        }else if(examType==2){
            typeName = "weekExamResult";
            fileName = typeName + fileName;
        }
        response.setHeader("Content-disposition","allachment;filename="+fileName);
        EasyExcel.write(response.getOutputStream(),ExamResultExcel.class)
                .registerWriteHandler(ResultServlet.getWriteStyle())
                .sheet(typeName)
                .doWrite(resList);
    }

    /**
     * 从成绩表删除数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void delExamResultInfoById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean flag = examService.delExamResultInfoById(id);
        if (flag) {
            PrintWriter out = response.getWriter();
            out.print("success");
            out.flush();
            out.close();
        }
    }

    /**
     * 修改成绩表中的数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void changeExamResultInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String examDate = request.getParameter("examdate");
        String studentresult = request.getParameter("studentresult");
        int id = Integer.parseInt(request.getParameter("id"));
        boolean flag = examService.changeExamResultInfo(examDate, studentresult, id);
        if (flag) {
            PrintWriter out = response.getWriter();
            out.print("success");
            out.flush();
            out.close();
        }
    }

    /**
     * 获取到所有符合条件的学生集合
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void getExamResultList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageSupport pageInfo = new PageSupport();
        String className = request.getParameter("className");// 获取到需要查询的班级名称
        String namec = request.getParameter("namec");// 获取到需要查询的学生姓名
        String date = request.getParameter("date");// 获取到需要查询的考试日期
        String result = request.getParameter("result");// 获取到需要查询的成绩等级
        String pageIndex = request.getParameter("pageIndex");// 获取到当前页数
        int examType = Integer.parseInt(request.getParameter("examType"));// 获取到当前考试类型
        int majorId = Integer.parseInt(request.getParameter("major"));

        // 当前页数为空时，赋1
        if (pageIndex == null) {
            pageIndex = "1";
        }
        int currentPage = Integer.parseInt(pageIndex);
        // 查询条件的对象
        QueryParams queryParams = new QueryParams(className, namec, date, result, examType);
        int totalCount = examService.getTotalResultCount(queryParams);// 获取总记录数
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

        List<ExamResult> resList = examService.getResultList(queryParams, pageSize, currentPage);

        List<Major> majorList = classService.getMajorList();// 获取专业集合用于回显
        List<Classes> classList = classService.getClassList(majorId);// 获取班级集合用于回显
        List<String> resultList = ExamUtil.getResultList();// 获取成绩评级集合用于回显

        // 回显数据
        request.setAttribute("majorList", majorList);
        request.setAttribute("mId", majorId);
        request.setAttribute("classList", classList);
        request.setAttribute("cName", className);
        request.setAttribute("namec", namec);
        request.setAttribute("date", date);
        request.setAttribute("result", result);
        //request.setAttribute("resultList", resultList);

        request.setAttribute("examType", examType);
        request.setAttribute("resList", resList);
        request.setAttribute("pageInfo", pageInfo);
        if (examType == 1) {
            request.getRequestDispatcher("/report/reportMorningResultInfo.jsp").forward(request, response);
        } else if (examType == 2) {
            request.getRequestDispatcher("/report/reportWeekResultInfo.jsp").forward(request, response);
        }
    }

    /**
     * 第一次进页面是获取专业集合用于做下拉框
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void getMajorList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Major> majorList = classService.getMajorList();
        List<String> resultList = ExamUtil.getResultList();
        int examType = Integer.parseInt(request.getParameter("examType"));
        request.setAttribute("majorList", majorList);
        request.getSession().setAttribute("resultList", resultList);
        if (examType == 1) {
            request.getRequestDispatcher("/report/reportMorningResultInfo.jsp").forward(request, response);
        } else if (examType == 2) {
            request.getRequestDispatcher("/report/reportWeekResultInfo.jsp").forward(request, response);
        }
    }

    /**
     * 拦截器形式自定义样式
     * 自己写策略
     * 内容水平垂直居中
     * 头部字体20   内容字体18
     * 可以自己加背景颜色
     * @return 水平单元格样式策略
     */
    public static HorizontalCellStyleStrategy getWriteStyle(){
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置
//        headWriteCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());

        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)20);
        headWriteCellStyle.setWriteFont(headWriteFont);
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //内容水平居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //内容垂直居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short)18);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
