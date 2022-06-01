package ZTE.controller.lidelin.student;

//import ZTE.controller.lidelin.listener.DemoDataListener;
//import ZTE.entity.lidelin.WorkInfo;
//import ZTE.service.lidelin.report.WorkInfoService;
//import ZTE.service.lidelin.report.impl.WorkInfoServiceImpl;
//import com.alibaba.excel.EasyExcel;

import ZTE.controller.lidelin.listener.DemoDataListener;
import ZTE.entity.lidelin.WorkInfo;
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

/**
 * 作业情况导入
 */
@MultipartConfig
@WebServlet("/excelworkservlet3")
public class ExcelWorkServlet3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=utf-8");
//        request.setCharacterEncoding("utf-8");
        String major = request.getParameter("major");
        String classname = request.getParameter("classname");
        // 文件上传
        // 首先要在Servlet上用 @MultipartConfig 标识支持文件上传
        // 获取part对象 参数为name属性的值
        Part part = request.getPart("file5");
        // 获取文件名
        String fileName = part.getSubmittedFileName();
        System.out.println(fileName);
        // 获取数据的流
        InputStream inputStream = part.getInputStream();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        try {
            EasyExcel.read(inputStream, WorkInfo.class, new DemoDataListener()).sheet().doRead();
            request.getRequestDispatcher("workrecordservlet?major=" + major + "&classname" + classname).forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("jsp/lidelin/excelError.jsp?major=" + major + "&classname" + classname).forward(request, response);
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
