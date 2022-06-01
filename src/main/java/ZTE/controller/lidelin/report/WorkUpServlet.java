package ZTE.controller.lidelin.report;

import ZTE.entity.lidelin.StudentResult;
import ZTE.service.lidelin.report.WorkInfoService;
import ZTE.service.lidelin.report.impl.WorkInfoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 作业情况修改页面
 */
@WebServlet("/workupservlet")
public class WorkUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String result = request.getParameter("newResult");
        String date = request.getParameter("newDate");
        StudentResult studentResult = new StudentResult(id, date, result);
        WorkInfoService workInfoService = new WorkInfoServiceImpl();
        boolean flag = workInfoService.updateWorkInfo(studentResult);
        if (!flag) {
            session.setAttribute("ups", "修改失败！");
        }
        request.getRequestDispatcher("workinfoservlet").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
