package org.crm.web.controller;


import org.crm.entity.TblUser;
import org.crm.exception.LoginException;
import org.crm.service.TblUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private TblUserService tblUserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> validateLogin(HttpServletRequest request, HttpServletResponse response,
                                             String username, String password, String autologin) throws LoginException {

        // 获取浏览器请求的ip地址 用来判断是否有权限访问
        String requestIPaddr = request.getRemoteAddr();
        // 调用service中的方法 该方法可能会抛出异常 由handler进行异常处理
        TblUser validatedUser = tblUserService.validateLogin(username, password, requestIPaddr);

        // 若没有抛异常 则表示用户信息是有效的 做进一步处理

        // 将用户的信息存储到cookie 用于免登录功能
        if (autologin != null){
            Cookie nameCookie = new Cookie("username", validatedUser.getLoginact());
            Cookie pwdCookie = new Cookie("pwd", validatedUser.getLoginpwd());
            // 设置cookie有效期为10天 并且路径设置为根目录 即登录页面所在路径
            nameCookie.setMaxAge(10*24*60*60);
            nameCookie.setPath("/");
            pwdCookie.setMaxAge(10*24*60*60);
            pwdCookie.setPath("/");

            response.addCookie(nameCookie);
            response.addCookie(pwdCookie);
        }

        // 将用户信息保存在session中 设置拦截器时使用
        request.getSession().setAttribute("user", validatedUser);


        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        return resultMap;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        Object user = session.getAttribute("user");
        if (user != null){
            session.removeAttribute("user"); // invalidate也行
        }
        return "redirect:/login.html";

    }

    @RequestMapping("/queryAll")
    @ResponseBody
    public List<TblUser> queryAll(){
        return tblUserService.queryAll();
    }

}
