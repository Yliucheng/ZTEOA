package ZTE.filter;

import ZTE.utils.Constant;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;


/**
 * 验证所有请求是否登录后的session，如果没有则是非法的
 */
@WebFilter(urlPatterns = "/*")
//@WebFilter(urlPatterns = "/*.do")
//@WebFilter(urlPatterns = "/*.jsp")
public class LoginCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 实现过滤器的代码
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        HttpSession session= request.getSession();

        //设置允许不经过过滤器的请求
        ArrayList<String> allowPathList=new ArrayList<>();
        allowPathList.add("/login-1.jsp");
        allowPathList.add("/login");
        //获取到请求的路径
        String currentUrl=request.getServletPath();
        //如果没有取到登录的session就说明不是从登录进来的！非法的！
        if(session.getAttribute(Constant.LOGIN_SESSION)==null&&!allowPathList.contains(currentUrl)
        &&!currentUrl.contains("/statics/")&&!currentUrl.contains("/lib/")&&!currentUrl.contains("/images/")){
            System.out.println("拦截");
            response.sendRedirect(request.getContextPath()+"/login-1.jsp");
        }

        //过渡到过滤链上其他的过滤器上
        filterChain.doFilter(request,response);
    }
}
