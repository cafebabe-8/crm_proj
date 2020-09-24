package org.crm.web.controller;

import org.crm.entity.TblUser;
import org.crm.service.TblUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

//用来做页面的跳转
@Controller
public class PageController {
    @Resource
    private TblUserService tblUserService;

    @RequestMapping(value = "/**/*.html", method = RequestMethod.GET)
    public String getPage(HttpServletRequest request){
        // /xx/xx.html
        String requestURI = request.getRequestURI();
        String url = "";
        if (requestURI != null){
             url = requestURI.substring(0, requestURI.lastIndexOf("."));
        }
        return url;
    }

    // 在登录界面登录时 需要利用本地的cookie来实现免登录功能
    @RequestMapping("/")
    public String autoLogin(HttpServletRequest request){
        // 获取cookie
        Cookie[] cookies = request.getCookies();
        // 本地没有cookie 那么返回到login页面
        if (cookies == null){
            return "/login";
        }
        String username = "";
        String password = "";
        for (Cookie c : cookies){
            if ("username".equals(c.getName()))
                username = c.getValue();
            if ("pwd".equals(c.getName()))
                password = c.getValue();
        }
        // username和password都有非空值 才能进行进一步验证
        // 否则返回登录界面
        if ("".equals(username) || "".equals(password)){
            return "/login";
        }
        String reqIp = request.getRemoteAddr();
        TblUser user = tblUserService.validateLoginforAutoLogin(username, password, reqIp);

        // 如果user为空 代表cookie中的信息已经失效 比如已经用户已经过期 密码修改了等等
        if (user == null){
            return "/login";
        }

        // 验证通过后同样要将用户信息保存到session中
        request.getSession().setAttribute("user", user);
        // 重定向到工作台
        return "redirect:/workbench/index.html";


    }
  // 在登录界面登录时 需要利用本地的cookie来实现免登录功能
    @RequestMapping("/login.html")
    public String autoLoginL(HttpServletRequest request){
        // 获取cookie
        Cookie[] cookies = request.getCookies();
        // 本地没有cookie 那么返回到login页面
        if (cookies == null){
            return "/login";
        }
        String username = "";
        String password = "";
        for (Cookie c : cookies){
            if ("username".equals(c.getName()))
                username = c.getValue();
            if ("pwd".equals(c.getName()))
                password = c.getValue();
        }
        // username和password都有非空值 才能进行进一步验证
        // 否则返回登录界面
        if ("".equals(username) || "".equals(password)){
            return "/login";
        }
        String reqIp = request.getRemoteAddr();
        TblUser user = tblUserService.validateLoginforAutoLogin(username, password, reqIp);

        // 如果user为空 代表cookie中的信息已经失效 比如已经用户已经过期 密码修改了等等
        if (user == null){
            return "/login";
        }

        // 验证通过后同样要将用户信息保存到session中
        request.getSession().setAttribute("user", user);
        // 重定向到工作台
        return "redirect:/workbench/index.html";


    }
}
