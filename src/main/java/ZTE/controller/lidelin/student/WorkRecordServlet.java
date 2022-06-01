package ZTE.controller.lidelin.student;

import ZTE.entity.lidelin.ClassInfo;
import ZTE.entity.lidelin.Major;
import ZTE.entity.lidelin.Student;
import ZTE.service.lidelin.student.WorkRecordService;
import ZTE.service.lidelin.student.impl.WorkRecordServiceImpl;
import ZTE.utils.PageSupport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/workrecordservlet")
public class WorkRecordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        WorkRecordService workRecordService = new WorkRecordServiceImpl();
        String major = request.getParameter("major");
        String classname = request.getParameter("classname");
        // 把查询的专业和班级名称存到request去, 进行回显
        request.setAttribute("major", major);
        request.setAttribute("classname", classname);
        // 查询所有专业信息并存到request里
        List<Major> majorList = workRecordService.getMajorList();
        request.setAttribute("majorList", majorList);
        // 根据专业得到所有班级信息并存到request里
        List<ClassInfo> classInfoList = workRecordService.getClassInfoList(major);
        request.setAttribute("classInfoList", classInfoList);
        // 查询需要显示的学生总数, 存到request
        int totalStudentCount = workRecordService.getTotalCount(major, classname);
        request.setAttribute("totalStudentCount", totalStudentCount);
        // 把页面信息存储到对象里
        PageSupport pageSupport = new PageSupport();
        // 页面尺寸
        int pageSize = PageSupport.pageSizeVal;
        // 设置页面尺寸
        pageSupport.setPageSize(pageSize);
        // 设置学生总数是会自动计算并设置总页数
        pageSupport.setTotalCount(totalStudentCount);
        // 取到总页数
        int totalPageCount = pageSupport.getTotalPageCount();
        // 取到页面传过来的当前页数, 需要先做逻辑判断
        String pageIndex = request.getParameter("pageIndex");
        // 先判断传过来的页数是否为null, 若为空则为第一次登录, 当前页数赋为1
        if (pageIndex == null) {
            pageIndex = "1";
        }
        // 当前页数
        int currentPageCount = 1;
        // 把传过来的页数赋给当前页数
        currentPageCount = Integer.parseInt(pageIndex);
        // 如果当前页数<1, 则跳到第一页
        // 如果当前页数>总页数, 则跳到最后一页
        if (currentPageCount < 1) {
            currentPageCount = 1;
        }else if (currentPageCount > totalPageCount&&pageSupport.getTotalPageCount()!=0) {
            currentPageCount = totalPageCount;
        }
        if (totalPageCount == 0) {
            currentPageCount = 1;
        }

        // 设置当前页数并把页面信息存到request对象
        pageSupport.setCurrentPageNo(currentPageCount);
        request.setAttribute("pageSupport", pageSupport);
        // 多条件查询学生信息并存到request对象
        List<Student> studentList = workRecordService.getStudentList(major, classname, currentPageCount, pageSize);
        request.setAttribute("studentList", studentList);
        // 转发到展示页面
        request.getRequestDispatcher("jsp/lidelin/workRecord.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

}
