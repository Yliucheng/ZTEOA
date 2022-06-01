package ZTE.controller.lidelin.student;

import ZTE.entity.lidelin.ClassInfo;
import ZTE.service.lidelin.student.WorkRecordService;
import ZTE.service.lidelin.student.impl.WorkRecordServiceImpl;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @ClassName GetBanJi
 * @Description 根据专业去查询到班级
 * @Version 1.0
 */
@WebServlet("/getBanJi")
public class GetBanJi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String major = request.getParameter("major");
        WorkRecordService workRecordService = new WorkRecordServiceImpl();
        // 根据专业得到所有班级信息
        List<ClassInfo> classInfoList = workRecordService.getClassInfoList(major);
        // fastjoson转换json字符串并输出到客户端
        String josonResult = JSON.toJSONString(classInfoList);
        PrintWriter out = response.getWriter();
        out.print(josonResult);
        out.flush();
        out.close();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
