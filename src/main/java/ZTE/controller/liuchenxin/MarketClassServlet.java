package ZTE.controller.liuchenxin;

import ZTE.dao.BaseDao;
import ZTE.dao.liuchenxin.marketClass.MarketClassDao;
import ZTE.dao.liuchenxin.marketClass.MarketClassImpl;
import ZTE.entity.liuchenxin.MarketClass;
import ZTE.service.liuchenxin.marketClass.MarketClassService;
import ZTE.service.liuchenxin.marketClass.MarketClassServiceImpl;
import ZTE.utils.liuchenxin.PageSupport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * 控制层
 * 市场类型班级
 */
@WebServlet("/MarketClassServlet.do")
public class MarketClassServlet extends HttpServlet {
    BaseDao baseDao = new BaseDao();
    MarketClassService marketClassService = new MarketClassServiceImpl();
    MarketClassDao marketClassDao = new MarketClassImpl();
    List<MarketClass> allMarketClass = marketClassDao.getAllMarketClass();//所有的市场类型班级对象
    List<String> allMarketType = marketClassDao.getAllMarketType();//获取所有的市场类型

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
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
        }
    }

    private void update_gai(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取参数
        int id = Integer.parseInt(req.getParameter("cid"));
        String name = req.getParameter("name");
        String type = req.getParameter("type");
        //调用修改方法
        marketClassService.updateMarketClass(id,name,type);
        //重定向回到查询页面
        resp.sendRedirect("MarketClassServlet.do?caozuo=cha");
    }

    /**
     * 根据班级的编号来删除
     * @param req
     * @param resp
     */
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取到班级的id
        int id = Integer.parseInt(req.getParameter("id"));
        //调用删除方法，传入班级的id
        marketClassService.deleteMarketClass(id);
        //重定向回到查询页面
        resp.sendRedirect("MarketClassServlet.do?caozuo=cha");
    }

    private void update_cha(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取到要修改的班级的id
        int id = Integer.parseInt(req.getParameter("id"));
        //根据id获取到原班级对象
        MarketClass marketClass = marketClassService.getMarketClass(id);
        //将对象存入域中
        req.setAttribute("marketClass",marketClass);
        req.setAttribute("allMarketType",allMarketType);//将所有的市场类型存入域中
        //转发到修改页面
        req.getRequestDispatcher("jsp/liuchenxin/marketClass/updateMarketClass.jsp").forward(req, resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取到参数
        String name = req.getParameter("name");
        String type = req.getParameter("type");
        //调用添加方法
        marketClassService.addMarketClass(name,type);
        //重定向至查询页面
        resp.sendRedirect("MarketClassServlet.do?caozuo=cha");
    }

    /**
     * 查询
     * @param req
     * @param resp
     */
    private void cha(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type=null;
        //判断是否为条件查询
        if(null!=req.getParameter("type")&&!"".equals(req.getParameter("type"))){
            //如果有条件，就给type赋值
            type = req.getParameter("type");
        }
        //获取到当前页号
        String pageIndex = req.getParameter("pageIndex");
        //判断是不是第一次访问，如果是，页号默认为1
        if(null==pageIndex){
            pageIndex="1";
        }
        //获取到总记录数
        int count  = marketClassDao.getInfoCount(type);
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
        List<MarketClass> marketClassList = marketClassService.showMarketClass(type,pages.getCurrentPageNo(),pageSize);
        //将信息存入域中
        req.setAttribute("marketClassList",marketClassList);
        req.setAttribute("showType",type);
        req.setAttribute("pages",pages);
        req.getRequestDispatcher("jsp/liuchenxin/marketClass/showMarketClass.jsp").forward(req, resp);
    }
}
