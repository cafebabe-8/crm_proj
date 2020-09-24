package org.crm.web.interceptor;


import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        // 判断用户是否登录，登录就放行，否则强制跳转到登录页面
        Object user = request.getSession().getAttribute("user");

        // 没登录
        if ( user == null ) {
            // 如果是ajax请求 登录超时时给与提示信息
            if (request.getHeader("X-Requested-With") != null){

                Map<String, Object> map = new HashMap<>();
                map.put("success", false);
                map.put("relogin", true);
                map.put("msg", "登录超时，请重新登录");

                // 手动将map转换成json

                try(PrintWriter out = response.getWriter()) {
                    JsonMapper jsonMapper = new JsonMapper();
                    String json = jsonMapper.writeValueAsString(map);
                    out.write(json);
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }





            // 一定不可以直接跳转jsp，因为没有访问权限
            try {
                response.sendRedirect("/login.html");
            } catch (IOException e) {
                e.printStackTrace();
            }


            return false;
        }

        return true;
    }
}
