package ZTE.controller.lidelin.report;

import ZTE.entity.lidelin.ClassInfo;
import ZTE.entity.lidelin.Major;
import ZTE.entity.lidelin.WorkInfo;
import ZTE.entity.lidelin.WorkResult;
import ZTE.service.lidelin.report.WorkInfoService;
import ZTE.service.lidelin.report.impl.WorkInfoServiceImpl;
import ZTE.service.lidelin.student.WorkRecordService;
import ZTE.service.lidelin.student.impl.WorkRecordServiceImpl;
import ZTE.utils.PageSupport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

/**
 * @description 作业完成情况查询
 */
@WebServlet("/workinfoservlet")
public class WorkInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        WorkInfoService workInfoService = new WorkInfoServiceImpl();
        WorkRecordService workRecordService = new WorkRecordServiceImpl();
        // 取到页面的表单信息
        // 专业
        String major = request.getParameter("major");
        request.setAttribute("major", major);
        // 查询所有专业信息并存到request里
        List<Major> majorList = workRecordService.getMajorList();
        request.setAttribute("majorList", majorList);
        // 班级
        String classname = request.getParameter("classname");
        request.setAttribute("classname", classname);
        // 根据专业得到所有班级信息并存到request里
        List<ClassInfo> classInfoList = workRecordService.getClassInfoList(major);
        request.setAttribute("classInfoList", classInfoList);
        // 姓名
        String Namec = request.getParameter("namec");
        request.setAttribute("Namec", Namec);
        // 日期
        String date = request.getParameter("date");
        request.setAttribute("date", date);
        // 作业表现
        String result = request.getParameter("result");
        request.setAttribute("result", result);
        // 查询到所有的作业表现信息
        List<WorkResult> workResultList = workInfoService.getWorkResultList();
        session.setAttribute("workResultList", workResultList);
        // 多条件查询需要显示的学生总数, 存到request
        int totalStudentCount = workInfoService.getTotalCount(major, classname, Namec, date, result);
        request.setAttribute("totalStudentCount", totalStudentCount);
        // 把页面信息存储到对象里
        PageSupport pageSupport = new PageSupport();
        // 页面尺寸
        int pageSize = PageSupport.pageSizeVal;
        // 设置页面尺寸
        pageSupport.setPageSize(pageSize);
        // 设置学生总数是会自动计算并设置总页数
        pageSupport.setTotalCount(totalStudentCount);
        // 获取到总页数
        int totalPageCount = pageSupport.getTotalPageCount();
        // 取到页面传过来的当前页数, 需要先做逻辑判断
        String pageIndex = request.getParameter("pageIndex");
        // 先判断传过来的页数是否为null, 若为空则为第一次登录, 当前页数赋为1
        if (pageIndex == null) {
            pageIndex = "1";
        }
        // 当前页数
        int currentPageNo = 1;
        // 把传过来的页数赋给当前页数
        currentPageNo = Integer.parseInt(pageIndex);
        // 如果当前页数<1, 则跳到第一页
        // 当前页为首页(第一页)再点上一页始终保持第一页，当前页为尾页点击下一页始终保持最后一页
        if (currentPageNo<1){
            currentPageNo=1;
        } else if (currentPageNo>pageSupport.getTotalPageCount()&&pageSupport.getTotalPageCount()!=0) {
            currentPageNo=pageSupport.getTotalPageCount();
        }
        // 当查询没有数据的时候当前页数始终保持为1
        if (pageSupport.getTotalPageCount()==0){
            currentPageNo=1;
        }
        // 设置当前页数并把页面信息存到request对象
        pageSupport.setCurrentPageNo(currentPageNo);
        request.setAttribute("pageSupport", pageSupport);
        // 查询需要显示的作业完成情况学生信息(分页)(显示用), 存到request
        List<WorkInfo> workInfoList = workInfoService.getWorkInfoList(major, classname, Namec, date, result, currentPageNo, pageSize);
        request.setAttribute("workInfoList", workInfoList);

//        // 查询到学生列表后存入request并且转发
//        HttpSession session = request.getSession();

        request.getRequestDispatcher("jsp/lidelin/reportWorkResultInfo.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}