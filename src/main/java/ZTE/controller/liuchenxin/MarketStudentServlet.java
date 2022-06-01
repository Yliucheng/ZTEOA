package ZTE.controller.liuchenxin;

import ZTE.dao.liuchenxin.marketStudent.MarketStudentDao;
import ZTE.dao.liuchenxin.marketStudent.MarketStudentImpl;
import ZTE.entity.Admin;
import ZTE.entity.liuchenxin.MarketStudent;
import ZTE.service.liuchenxin.marketStudent.MarketStudentService;
import ZTE.service.liuchenxin.marketStudent.MarketStudentServiceImpl;
import ZTE.utils.Constant;
import ZTE.utils.excel.MarketStudentExcel;
import ZTE.utils.excel.lisitener.StudentListener;
import ZTE.utils.excel.student.StudentData;
import ZTE.utils.liuchenxin.PageSupport;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONArray;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 控制层
 * 市场类型学生
 */
@WebServlet("/MarketStudentServlet.do")
@MultipartConfig
public class MarketStudentServlet extends HttpServlet {
    MarketStudentService marketStudentService = new MarketStudentServiceImpl();
    MarketStudentDao marketStudentDao = new MarketStudentImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //取到需要执行的操作信息
        String caozuo = req.getParameter("caozuo");
        if("cha".equals(caozuo)){//查询
            cha(req,resp);
        }else if("add".equals(caozuo)){//增加
            add(req,resp);
        }else if("update_cha".equals(caozuo)){//修改-查询要修改对象
            update_cha(req,resp);
        }else if("delete".equals(caozuo)){//删除
            delete(req,resp);
        }else if("update_gai".equals(caozuo)){//修改-得到新的信息修改
            update_gai(req,resp);
        }else if("jilian".equals(caozuo)){//根据学生类型级联班级
            jilian(req,resp);
        } else if ("show".equals(caozuo)) {//显示单个学生的信息
            show(req,resp);
        }else if("setStuType_ByClass".equals(caozuo)){//根据班级名称得到的类型
            setStuType_ByClass(req,resp);
        }else if("daochu".equals(caozuo)){//导出学生信息
            daochu(req,resp);
        }else if("daoru".equals(caozuo)){//导入学生信息
            daoru(req,resp);
        }
    }

    /**
     * 导入学生信息
     * @param req
     * @param resp
     */
    private void daoru(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //文件上传
        //首先要在Servlet上用 @MultipartConfig 标识支持文件上传
        //获取part对象 参数为name属性的值
        Part part = req.getPart("excelFile");
        //获取文件名
        String fileName = part.getSubmittedFileName();
        System.out.println(fileName);
        //获取数据的流
        InputStream inputStream = part.getInputStream();
        req.setAttribute("servletName","MarketStudentServlet.do?caozuo=cha");
        try {
            EasyExcel.read(inputStream, StudentData.class, new StudentListener())
                    .sheet()
                    .doRead();
            req.getRequestDispatcher("/common/successUpload.jsp").forward(req,resp);
        } catch (Exception e) {
            req.getRequestDispatcher("/common/FailUpload.jsp").forward(req,resp);
            e.printStackTrace();
        }

    }

    /**
     * 导出学生信息
     * @param req
     * @param resp
     */
    private void daochu(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //取到参数
        String name = req.getParameter("stuName");
        String className = req.getParameter("className");
        String stuType = req.getParameter("stuType");
        String willTrain = req.getParameter("willTrain");
        //获取到学生列表
        List<MarketStudent> marketStudents = marketStudentService.showMarketStudent(stuType, name, className, willTrain, 0, 0);
        //获取当前系统时间
        SimpleDateFormat sm = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        Date date = new Date();
        String downLoadTime = sm.format(date);
        //设置Excel响应头，点击按钮时弹出保存框
        resp.setContentType("application/vnd.ms-excel");
        resp.setCharacterEncoding("utf-8");
        //下载的Excel文件名
        String fileName = "student"+downLoadTime+".xlsx";
        resp.setHeader("Content-disposition","allachment;filename="+fileName);
        EasyExcel.write(resp.getOutputStream(),MarketStudentExcel.class).sheet("学生信息").doWrite(marketStudents);
    }

    /**
     * 根据选择的班级，自动锁定学生类型
     * @param req
     * @param resp
     */
    private void setStuType_ByClass(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取到班级名称
        String className = req.getParameter("className");
        //调用方法得到班级所对应的学生类型
        String stuType  = marketStudentService.getStuTypeByClass(className);
        String s = JSONArray.toJSONString(stuType);
        req.getSession().removeAttribute("stuType1");
        req.getSession().setAttribute("stuType1",stuType);
        PrintWriter writer = resp.getWriter();
        writer.print(s);
        writer.flush();
        writer.close();
    }

    /**
     * 根据学生类型级联班级
     * @param req
     * @param resp
     */
    private void jilian(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取到学生类型
        String stuType = req.getParameter("stuType");
        //调用方法得到同类型的所有班级
        List<String> listClass = marketStudentService.getMarketClassByStuType(stuType);
        String s = JSONArray.toJSONString(listClass);
        PrintWriter writer = resp.getWriter();
        writer.print(s);
        writer.flush();
        writer.close();

    }

    private void update_gai(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取到要修改的学生id
        int id = Integer.parseInt(req.getParameter("id"));
        //获取当前系统时间和修改人
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String modifyTime = df.format(new Date());//设置修改时间

        //获取需要修改的参数
        String name = req.getParameter("name");
        String oldname = req.getParameter("oldname");
        String modifyAdmin =oldname;//修改人
        //调用方法，根据班级名称得到班级id
        int classId = marketStudentDao.getClassIdByClassName(req.getParameter("className"));
        String fromSchool = req.getParameter("fromSchool");
        String education = req.getParameter("education");
        String phone = req.getParameter("phone");
        String QQ = req.getParameter("QQ");
        String xingge = req.getParameter("xingge");
        String beizhu = req.getParameter("beizhu");
        String willTrain = req.getParameter("willTrain");
        MarketStudent student= new MarketStudent(name,classId,fromSchool,education,phone,QQ,xingge,beizhu,willTrain,modifyTime,modifyAdmin);
        //调用修改方法
        marketStudentService.updateMarketStudent(id,student);
        //重定向至查询页面
        resp.sendRedirect("MarketStudentServlet.do?caozuo=cha");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取到要删除的学生id
        int id = Integer.parseInt(req.getParameter("id"));
        //调用删除方法
        marketStudentService.deleteMarketStudent(id);
        //重定向回到查询页面
        resp.sendRedirect("MarketStudentServlet.do?caozuo=cha");
    }

    private void update_cha(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        int id = Integer.parseInt(req.getParameter("id"));
        //通过学生的id，获取到学生对象
        MarketStudent student = marketStudentDao.getStudentById(id);
        //将对象存入域中
        req.setAttribute("student",student);
        //转发到修改页面
        req.getRequestDispatcher("jsp/liuchenxin/marketStudent/updateMarketStudent.jsp").forward(req,resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取参数，创建学生对象
        String name = req.getParameter("name");//姓名
        String marketType = req.getParameter("marketType");//市场类型
        String className = req.getParameter("className");//班级名称
        int classId = marketStudentDao.getClassIdByClassName(className);//班级id
        String fromSchool = req.getParameter("fromSchool");//来自学校
        String education = req.getParameter("education");//学历
        String phone = req.getParameter("phone");//电话
        String QQ = req.getParameter("QQ");//QQ
        String xingge = req.getParameter("xingge");//性格特点
        String beizhu = req.getParameter("beizhu");//备注
        String willTrain = req.getParameter("willTrain");//培训意向
        Admin admin  = (Admin) req.getSession().getAttribute(Constant.LOGIN_SESSION);//创建者
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String createTime = df.format(new Date());//设置创建时间
        MarketStudent marketStudent = new MarketStudent(name,marketType,classId,fromSchool,education,phone,QQ,xingge,beizhu,willTrain,admin.getLogincode(),createTime);
        //调用添加方法
        marketStudentService.addMarketStudent(marketStudent);
        //重定向至页面
        resp.sendRedirect("MarketStudentServlet.do?caozuo=cha");
    }

    private void cha(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=null;//学生姓名
        String willTrain=null;//培训意向
        String type=null;//学生类型（班级类型）
        String className=null;//班级名称
        //判断是否为条件查询
        if(null!=req.getParameter("type")&&!"".equals(req.getParameter("type"))){//学生类型（班级类型）
            //如果有条件，就给type赋值
            type = req.getParameter("type");
        }
        if(null!=req.getParameter("name")&&!"".equals(req.getParameter("name"))){//学生姓名
            //如果有条件，就给type赋值
            name = req.getParameter("name");
        }
        if(null!=req.getParameter("willTrain")&&!"".equals(req.getParameter("willTrain"))){//培训意向
            //如果有条件，就给type赋值
            willTrain = req.getParameter("willTrain");
        }
        if(null!=req.getParameter("className")&&!"".equals(req.getParameter("className"))){//班级名称
            //如果有条件，就给type赋值
            className = req.getParameter("className");
        }
        //获取到当前页号
        String pageIndex = req.getParameter("pageIndex");
        //判断是不是第一次访问，如果是，页号默认为1
        if(null==pageIndex){
            pageIndex="1";
        }
        //获取到总记录数
        int count  = marketStudentDao.getInfoCount(type,name,className,willTrain);
        //创建页面信息对象
        PageSupport pages = new PageSupport();
        //设置总记录数，也会自动得到总页数并且设置
        pages.setTotalCount(count);
        int currentPageNo = Integer.parseInt(pageIndex);//当前页号（如果是第一次，那么会默认为1）
        //校验页号是否合理
        if(currentPageNo<1){//如果在第一页点击了上一页，则默认赋值第一页
            currentPageNo=1;
        }else if(currentPageNo>pages.getTotalPageCount()){//如果在最后一页点击了下一页，则默认赋值最后一页
            currentPageNo=pages.getTotalPageCount();
        }
        //设置当前页号
        pages.setCurrentPageNo(currentPageNo);
        int pageSize = PageSupport.pageSizeVal;//页面尺寸
        pages.setPageSize(pageSize);
        //查询得到结果
        List<MarketStudent> marketStudentList = marketStudentService.showMarketStudent(type,name,className,willTrain,currentPageNo,pageSize);
        //将信息存入域中
        req.setAttribute("classList",marketStudentService.getMarketClassByStuType(type));
        req.setAttribute("marketStudentList",marketStudentList);
        req.setAttribute("showType",type);
        req.setAttribute("showName",name);
        req.setAttribute("showClassName",className);
        req.setAttribute("showWillTrain",willTrain);
        req.setAttribute("pages",pages);
        req.getRequestDispatcher("jsp/liuchenxin/marketStudent/showMarketStudent.jsp").forward(req, resp);
    }
    /**
     * 展示单个学生的信息
     * @param req
     * @param resp
     */
    private void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取到要展示的学生的id
        int id = Integer.parseInt(req.getParameter("id"));
        //根据id得到学生的完整信息
        MarketStudent student = marketStudentDao.getStudentById(id);
        //将信息存入域中
        req.setAttribute("student",student);
        //转发到show页面，显示信息
        req.getRequestDispatcher("jsp/liuchenxin/marketStudent/showStudent.jsp").forward(req,resp);

    }
}
