package ZTE.controller;

import ZTE.entity.Admin;
import ZTE.service.jiaowuqing.admin.AdminService;
import ZTE.service.jiaowuqing.admin.AdminServicesImpl;
import ZTE.utils.Constant;
import ZTE.utils.MD5Utils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    AdminService adminService = new AdminServicesImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        //加密前端传过来的密码
        String password = MD5Utils.getMD5Result(pwd);
        Admin userInfo = adminService.login(username);
//        Admin admin = adminService.login(username,pwd);

        if (userInfo==null){
            request.setAttribute("error","抱歉，没有 "+username+" 管理员用户!");
            request.getRequestDispatcher("login-1.jsp").forward(request,response);
        }else {
            //加密后端数据库取到的密码
            String userpwd = MD5Utils.getMD5Result(userInfo.getPassword());
            //加密后验证是否一样
            boolean flag = userpwd.equals(password);
            if (flag){
                request.getSession().setAttribute(Constant.LOGIN_SESSION,userInfo);
                //创建cookie对象保存用户信息
                Cookie cookieName = new Cookie(Constant.LOGIN_NAME,username);
                Cookie cookiePassword = new Cookie(Constant.LOGIN_PASSWORD,pwd);
                Cookie cookieCheck = new Cookie(Constant.LOGIN_CHECK,rememberMe);
                //如果用户勾选了记住密码则设置时间暂时将cookie保存的信息保存在客户端；
                if ("checked".equals(rememberMe)){
                    cookieName.setMaxAge(7*24*60*60);//单位秒
                    cookiePassword.setMaxAge(7*24*60*60);//相当于一星期
                    cookieCheck.setMaxAge(7*24*60*60);//保存于一星期
                } else {
                    //设置将不保存cookie
                    cookieName.setMaxAge(0);
                    cookiePassword.setMaxAge(0);
                    cookieCheck.setMaxAge(0);
                }
                //输出到客户端
                response.addCookie(cookieName);
                response.addCookie(cookiePassword);

                response.sendRedirect("jsp/main.jsp");
            }else {
                request.setAttribute("error","登录失败，密码错误!");
                request.getRequestDispatcher("login-1.jsp").forward(request,response);
            }
        }
    }
}
