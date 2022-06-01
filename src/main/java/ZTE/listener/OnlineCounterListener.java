package ZTE.listener;

import ZTE.utils.Constant;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * 定义一个监听程序在线人数统计
 * HttpSessionListener用于监听session的创建和销毁
 * HttpSessionAttributeListener 用于监听session属性的增加和移除
 */
@WebListener
public class OnlineCounterListener implements HttpSessionListener, HttpSessionAttributeListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("===用于监听session被创建===");
    }

    /**
     *
     * @param event 指Session的事件对象
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("session的名称："+event.getName());
        if (event.getName()!=null&&!"".equals(event.getName())){
            //判断增加的是登录的session，才给人数+1
            if (Constant.LOGIN_SESSION.equals(event.getName())){
//                System.out.println("登录人数+1");
                OnlineCounter.raise();
            }
        }
    }

    /**
     * 用于监听session被销毁
     * @param event 指session事件的对象
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("session的名称："+event.getName());
        if (event.getName()!=null&&!"".equals(event.getName())){
            //一定要判断，是移除的session是登录的session才-1
            if (Constant.LOGIN_SESSION.equals(event.getName())){
//                System.out.println("登录人数-1");
                OnlineCounter.reduce();
            }
        }
    }

}
