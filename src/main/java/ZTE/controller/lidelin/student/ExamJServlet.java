package ZTE.controller.lidelin.student;

import ZTE.service.lidelin.student.WorkRecordService;
import ZTE.service.lidelin.student.impl.WorkRecordServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 每日作业统计
 */
@WebServlet("/examJServlet")
public class ExamJServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String[] studentNoData = request.getParameterValues("studentNoData");
        String results = request.getParameter("result");
        WorkRecordService workRecordService = new WorkRecordServiceImpl();
        boolean flag = workRecordService.getJExam(studentNoData, results);
        String result = null;
        if (flag) {
            result = "success";
            PrintWriter out = response.getWriter();
            out.print(result);
            out.flush();
            out.close();
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
