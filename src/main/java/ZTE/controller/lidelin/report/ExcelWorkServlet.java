package ZTE.controller.lidelin.report;

import ZTE.entity.lidelin.WorkInfo;
import ZTE.service.lidelin.report.WorkInfoService;
import ZTE.service.lidelin.report.impl.WorkInfoServiceImpl;
import ZTE.utils.excel.CellColorWriterHandlerByStudent;
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
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 作业情况导出
 */
@WebServlet("/excelworkservlet")
public class ExcelWorkServlet extends HttpServlet {

    /**
     * 拦截器形式自定义样式
     * 自己写策略
     * 内容水平垂直居中
     * 头部字体20   内容字体18
     * 可以自己加背景颜色
     *
     * @return 水平单元格样式策略
     */
    private HorizontalCellStyleStrategy getWriteStyle() {
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置
//        headWriteCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 20);
        headWriteCellStyle.setWriteFont(headWriteFont);
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 内容水平居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 内容垂直居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentWriteCellStyle.setWrapped(true);
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short) 16);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        WorkInfoService workInfoService = new WorkInfoServiceImpl();
        // 取到传过来的数据去取到学生信息列表
        String major = request.getParameter("major");
        String classname = request.getParameter("classname");
        String Namec = request.getParameter("Namec");
        String date = request.getParameter("date");
        String result = request.getParameter("result");
        // 查询需要显示的作业完成情况学生信息(所有)(导出用)
        List<WorkInfo> workInfoListAll = workInfoService.getWorkInfoListAll(major, classname, Namec, date, result);
        // 获取到时间, 以时间来定义下载Excel的名称
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String downloadTime = sdf.format(new Date());
        // 设置Excel响应头
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 下载的Excel文件名称
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("WorkInfo_" + downloadTime, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), WorkInfo.class).registerWriteHandler(getWriteStyle()).registerWriteHandler(new CellColorWriterHandlerByStudent()).sheet("作业完成情况学生信息表").doWrite(workInfoListAll);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
