package ZTE.controller.lidelin.report;

import ZTE.entity.lidelin.WorkInfo;
import ZTE.service.lidelin.report.WorkInfoService;
import ZTE.service.lidelin.report.impl.WorkInfoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 作业情况修改页面的展示数据
 */
@WebServlet("/workToUpservlet")
public class WorkToUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String major = request.getParameter("major");
        String classname = request.getParameter("classname");
        String namec = request.getParameter("namec");
        String date = request.getParameter("date");
        String result = request.getParameter("result");
        WorkInfoService workInfoService = new WorkInfoServiceImpl();
        WorkInfo workInfo = workInfoService.getWorkInfoById(id);
        request.setAttribute("workInfo", workInfo);
        request.setAttribute("major", major);
        request.setAttribute("classname", classname);
        request.setAttribute("namec", namec);
        request.setAttribute("date", date);
        request.setAttribute("result", result);
        request.getRequestDispatcher("jsp/lidelin/reportworkedit.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
