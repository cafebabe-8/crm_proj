package org.crm.web.listener;

import org.crm.entity.DicType;
import org.crm.service.DicTypeService;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.*;

@WebListener
public class ServletContextForDictListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public ServletContextForDictListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */


        // 读取数据字典 存放到servletcontext中 也就是web服务器application作用域中
        ServletContext servletContext = sce.getServletContext();
        // 在服务器初始化时 手动获得数类型的service代理类 因为这时springMVC可能没有加载
        DicTypeService typeService = WebApplicationContextUtils.getWebApplicationContext(servletContext)
                                                               .getBean(DicTypeService.class);
//        DicType dicType =(DicType)  WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("dicType");
        // 查询字典类型对应的字典值 并组装到context中
        List<DicType> typeList = typeService.queryAll(new DicType());
        for (DicType t : typeList) {
            servletContext.setAttribute(t.getCode()+"List", t.getValueList());
        }
        // 将各个交易阶段与对应的可能性放到map中
        Map<String, String> possibilityMap = new HashMap<>();
        // 读取本地的properties文件
        ResourceBundle poe = ResourceBundle.getBundle("possibilityOfEachStage");
        // 获取所有的键
        Enumeration<String> poeKeys = poe.getKeys();
        // 放入map中
        while (poeKeys.hasMoreElements()) {
            String key = poeKeys.nextElement();
            String value = poe.getString(key);
            possibilityMap.put(key, value);
        }
        servletContext.setAttribute("StagePossiMap", possibilityMap);
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attribute
         is replaced in a session.
      */
    }
}
