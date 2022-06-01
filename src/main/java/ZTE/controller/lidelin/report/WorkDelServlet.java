package ZTE.controller.lidelin.report;

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
 * 每日作业删除页面
 */
@WebServlet("/workdelservlet")
public class WorkDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        WorkInfoService workInfoService = new WorkInfoServiceImpl();
        boolean flag = workInfoService.deleteWorkInfoById(id);
        HttpSession session = request.getSession();
        if (!flag) {
            session.setAttribute("del", "删除失败！");
        }
        request.getRequestDispatcher("workinfoservlet").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
