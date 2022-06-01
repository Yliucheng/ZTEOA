package ZTE.controller.jiaowuqing;


import ZTE.entity.jiaowuqing.Classes;
import ZTE.entity.jiaowuqing.Major;
import ZTE.entity.jiaowuqing.Student;
import ZTE.service.jiaowuqing.classes.ClassService;
import ZTE.service.jiaowuqing.classes.ClassServiceImpl;
import ZTE.service.jiaowuqing.major.MajorService;
import ZTE.service.jiaowuqing.major.MajorServiceImpl;
import ZTE.service.jiaowuqing.student.StudentService;
import ZTE.service.jiaowuqing.student.StudentServiceImpl;
import ZTE.utils.PageSupport;
import ZTE.utils.PinYin;
import ZTE.utils.excel.CellColorWriterHandlerByStudent;
import ZTE.utils.excel.StudentExcel;
import ZTE.utils.excel.StudentTemplateExcel;
import ZTE.utils.excel.lisitener.StudentListener;
import ZTE.utils.excel.student.StudentData;
import ZTE.utils.excel.student.StudentSheetSelectWriteHandler;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSONArray;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/studentServlet")
//用 @MultipartConfig 标识支持文件上传
@MultipartConfig
public class StudentServlet extends HttpServlet {
    //业务层对象
    ClassService classService = new ClassServiceImpl();
    StudentService studentService = new StudentServiceImpl();
    MajorService majorService = new MajorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("param");
        if ("stuRegister".equals(param)){
            registerJSPServlet(request,response);
        } else if ("register".equals(param)) {
            registerSaveServlet(request,response);
        } else if ("cha".equals(param)) {
            chaServlet(request,response);
        } else if ("exportexcel".equals(param)) {
            excelServlet(request,response);
        } else if ("delete".equals(param)) {
            deleteServlet(request,response);
        } else if ("toUpdate".equals(param)) {
            updateServlet(request,response);
        } else if ("update".equals(param)) {
            updateSavaServlet(request,response);
        } else if ("toAdd".equals(param)) {
            addServlet(request,response);
        } else if ("add".equals(param)){
            addSaveServlet(request,response);
        } else if ("getBanjiList".equals(param)) {
            getBanjiList(request,response);
        } else if ("pinyin".equals(param)) {
            getPinYinServlet(request,response);
        } else if ("getLoginCode".equals(param)){
            getLoginCodeServlet(request,response);
        } else if ("templateExcel".equals(param)) {
            templateExcelServlet(request,response);
        } else if ("upload".equals(param)) {
            uploadServlet(request,response);
        }
    }

    private void uploadServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //文件上传
        //首先要在Servlet上用 @MultipartConfig 标识支持文件上传
        //获取part对象 参数为name属性的值
        Part part = request.getPart("excelFile");
        //获取文件名
        String fileName = part.getSubmittedFileName();
        System.out.println(fileName);
        //获取数据的流
        InputStream inputStream = part.getInputStream();
        //获得一个工作簿对象
        // EasyExcel.read(inputStream, StudentData.class,null);
        /*ExcelReaderBuilder readWorkBook = EasyExcel.read(inputStream, StudentData.class, new StudentListener());
        //获得一个工作表对象
        ExcelReaderSheetBuilder sheet = readWorkBook.sheet();
        //读取工作表内容
        sheet.doRead();*/
        request.setAttribute("servletName","studentServlet?param=cha");
        try {
            EasyExcel.read(inputStream, StudentData.class, new StudentListener())
                    .sheet()
                    .doRead();
//        String servletName  = "studentServlet?param=cha";
//        getJumpHtml(response,servletName);
            request.getRequestDispatcher("/common/successUpload.jsp").forward(request,response);
        } catch (Exception e) {
            request.getRequestDispatcher("/common/FailUpload.jsp").forward(request,response);
            e.printStackTrace();
        }
    }
    private void templateExcelServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Student> studentList  = new ArrayList<>();
        studentList.add(new Student("张三",11,"北京大学","研究生","yourName","yourPassword"));
        //设置Excel响应头，点击按钮时弹出保存框
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //下载的Excel文件名
        String fileName = URLEncoder.encode("学生表上传模板.xlsx","UTF-8");
        response.setHeader("Content-disposition","allachment;filename="+fileName);
        EasyExcel.write(response.getOutputStream(), StudentTemplateExcel.class)
                .registerWriteHandler(getWriteStyle())
                .registerWriteHandler(new StudentSheetSelectWriteHandler())
                .sheet("学生表导入模版")
                .doWrite(studentList);
    }

    private void getLoginCodeServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> loginCodeList = studentService.getLoginCodeList();
        String s = JSONArray.toJSONString(loginCodeList);
        PrintWriter writer = response.getWriter();
        writer.print(s);
        writer.flush();
        writer.close();
    }

    private void getPinYinServlet(HttpServletRequest request, HttpServletResponse response) {
        String name = "";
        String name1 = request.getParameter("name");
        if (name1!=null&&!"".equals(name1)){
            name=name1;
        }
        PrintWriter writer = null;
        try {
            String nameEn = PinYin.getNameEn(name);
            String s = JSONArray.toJSONString(nameEn);
            writer = response.getWriter();
            writer.print(s);
            writer.flush();

        } catch (BadHanyuPinyinOutputFormatCombination | IOException e) {
            e.printStackTrace();
        } finally {
            if (writer!=null){
                writer.close();
            }
        }
    }

    private void getBanjiList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer majorId =null;
        String pas = request.getParameter("pas");
        if (pas!=null&&!"".equals(pas)){
            majorId=Integer.parseInt(pas);
        }
        List<Classes> classesList = classService.getClassListByMajorId(majorId);
        String s = JSONArray.toJSONString(classesList);
        PrintWriter writer = response.getWriter();
        writer.print(s);
        writer.flush();
        writer.close();
    }

    private void addSaveServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        Integer classid = Integer.parseInt(request.getParameter("classid"));
        String yuanxiao = request.getParameter("yuanxiao");
        String xueli = request.getParameter("xueli");
        String logincode = request.getParameter("logincode");
        String password = request.getParameter("password");
        password=password.trim();
        Student student = new Student(null,name,classid,yuanxiao,xueli,logincode,password);
        boolean add = studentService.addStudent(student);
        if (add){
            System.out.println("增加成功");
        }
        response.sendRedirect("studentServlet?param=cha");
    }

    private void addServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Classes> classList = classService.getClassList();
        List<String> education = new ArrayList<>();
        education.add("专科");
        education.add("本科");
        Student student = new Student();
        request.setAttribute("student",student);
        request.setAttribute("education",education);
        request.setAttribute("classList",classList);
        request.setAttribute("cao","add");
        request.getRequestDispatcher("jsp/jiaowuqing/student_modify.jsp").forward(request,response);
    }

    private void updateSavaServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("Name");
        Integer classid = Integer.parseInt(request.getParameter("classid"));
        String yuanxiao = request.getParameter("yuanxiao");
        String xueli = request.getParameter("xueli");
        String logincode = request.getParameter("logincode");
        String password = request.getParameter("password");
        password=password.trim();
        Student student = new Student(id,name,classid,yuanxiao,xueli,logincode,password);
        boolean update = studentService.updateStudentById(student);
        if (update){
            System.out.println("修改成功");
        }
        response.sendRedirect("studentServlet?param=cha");
    }

    private void updateServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Student student = studentService.getStudentById(id);
        List<Classes> classList = classService.getClassList();
        List<String> education = new ArrayList<>();
        education.add("专科");
        education.add("本科");
        request.setAttribute("education",education);
        request.setAttribute("classList",classList);
        request.setAttribute("student",student);
        request.setAttribute("cao","update");
        request.getRequestDispatcher("jsp/jiaowuqing/student_modify.jsp").forward(request,response);
    }

    private void deleteServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        boolean delete = studentService.deleteStudentById(id);
        if (delete){
            System.out.println("删除成功");
        }
        response.sendRedirect("studentServlet?param=cha");
    }

    private void excelServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        Date date = new Date();
        String downLoadTime = sm.format(date);
        Integer major=null;
        Integer classId=null;
        String name =null;
        String major1 = request.getParameter("major");
        String classes = request.getParameter("classes");
        String name1 = request.getParameter("name");
        if (major1!=null&&!"".equals(major1)){
            major=Integer.parseInt(major1);
        }
        if (classes!=null&&!"".equals(classes)){
            classId=Integer.parseInt(classes);
        }
        if (name1!=null&&!"".equals(name1)){
            name=name1;
        }
        List<Student> studentList  = studentService.getStudentList(major,classId,name);
        //设置Excel响应头，点击按钮时弹出保存框
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //下载的Excel文件名
        String fileName = URLEncoder.encode("学生表"+downLoadTime+".xlsx","UTF-8") ;
        response.setHeader("Content-disposition","allachment;filename="+fileName);
        EasyExcel.write(response.getOutputStream(), StudentExcel.class)
                .registerWriteHandler(getWriteStyle())
                .registerWriteHandler(new CellColorWriterHandlerByStudent())
                .sheet("学生信息")
                .doWrite(studentList);
    }

    private void chaServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer zhuanye =null;
        Integer banji = null;
        String name="";
        String zhuanye1 = request.getParameter("zhuanye");
        String banji1 = request.getParameter("banji");
        String name1 = request.getParameter("name");

        //获取页面查询数据
        if (zhuanye1!=null&&!"".equals(zhuanye1)){
            zhuanye=Integer.parseInt(zhuanye1);
        }
        if (banji1!=null&&!"".equals(banji1)){
            banji=Integer.parseInt(banji1);
        }
        if (name1!=null&&!"".equals(name1)){
            name=name1;
        }
        int currentpage=1;
        String pageIndex = request.getParameter("pageIndex");
        if (pageIndex!=null&&!"".equals(pageIndex)){
            currentpage=Integer.parseInt(pageIndex);
        }
        int pageSize = PageSupport.pageSizeVal;
        int totalCount = studentService.getStudentTotalCount(zhuanye,banji,name);
        PageSupport pages = new PageSupport();
        pages.setPageSize(pageSize);
        pages.setTotalCount(totalCount);
        // 当前页为首页(第一页)再点上一页始终保持第一页，当前页为尾页点击下一页始终保持最后一页
        if (currentpage<1){
            currentpage=1;
        } else if (currentpage>pages.getTotalPageCount()&&pages.getTotalPageCount()!=0) {
            currentpage=pages.getTotalPageCount();
        }
        // 当查询没有数据的时候当前页数始终保持为1
        if (pages.getTotalPageCount()==0){
            currentpage=1;
        }
        pages.setCurrentPageNo(currentpage);
        List<Student> studentList = studentService.getStudentListByPage(zhuanye,banji,name,currentpage,pageSize);
        List<Major> majorList = majorService.getMajorList();
        List<Classes> classesList = classService.getClassListByMajorId(zhuanye);
        //回显
        request.setAttribute("zhuanye",zhuanye);
        request.setAttribute("banji",banji);
        request.setAttribute("name",name);

        //查询结果
        request.setAttribute("majorList",majorList);
        request.setAttribute("classesList",classesList);
        request.getSession().setAttribute("studentList",studentList);
        //页码对象
        request.setAttribute("pageInfo",pages);
        request.getRequestDispatcher("jsp/jiaowuqing/showStudent.jsp").forward(request,response);
    }

    private void registerSaveServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer classId = Integer.parseInt(request.getParameter("classId")) ;
        String name = request.getParameter("name");
        String yuanxiao = request.getParameter("yuanxiao");
        String xueli = request.getParameter("xueli");
        String logincode = request.getParameter("logincode");
        String password = request.getParameter("password");
        Student student = new Student(null,name,classId,yuanxiao,xueli,logincode,password);
        boolean add = studentService.addStudentRegister(student);
        if (add){
            System.out.println("注册成功");
        }
        //跳转学生管理页面
        response.sendRedirect("studentServlet?param=cha");
    }

    private void registerJSPServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Classes> classesList = classService.getClassList();
        List<String> education = new ArrayList<>();
        education.add("专科");
        education.add("本科");
        request.setAttribute("classesList",classesList);
        request.setAttribute("education",education);
        request.setAttribute("cao","register");
        request.getRequestDispatcher("jsp/jiaowuqing/studentRegister.jsp").forward(request,response);
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
        contentWriteCellStyle.setWrapped(true);


        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short)16);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }
}
