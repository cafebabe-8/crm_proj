package org.crm.web.controllerAdvice;


import com.fasterxml.jackson.databind.json.JsonMapper;
import org.crm.exception.LoginException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(LoginException.class)
    @ResponseBody
    public Map<String, Object> LoginExceptionHandler(Exception e){
        Map<String, Object> map = new HashMap<>();
        // 进入异常 表示用户登录失败 返回失败信息
        map.put("success", false);
        // 返回错误信息
        map.put("msg", e.getMessage());

        return map;
    }

    // 为了避免页面显示500 服务器出现异常时跳转到错误页面
    @ExceptionHandler(Exception.class)
    public String ExceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response){
        e.printStackTrace();
        response.setContentType("text/html;charset=utf-8");
        // 通过请求头信息来判断是否是ajax请求
        if (request.getHeader("X-Requested-With") != null) {
            Map<String,Object> map = new HashMap<>();
            map.put("success", false);
            map.put("msg", "该功能正在维护...");

            JsonMapper mapper = new JsonMapper();
            try(PrintWriter out = response.getWriter()) {
                String json = mapper.writeValueAsString(map);
                out.write(json);
                out.flush();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return null;
        }
        return "redirect:/errorpage.html";
    }
}
