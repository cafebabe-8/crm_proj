package org.crm.web.controller;


import org.crm.entity.TblUser;
import org.crm.entity.Tran;
import org.crm.service.TranService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/tran")
public class TranController {

    @Resource
    private TranService tranService;

    @RequestMapping("/detail.html")
    public ModelAndView jumpToDetailPage (String id) {
        ModelAndView mav = new ModelAndView();
        Tran tran = tranService.queryById(id);
        mav.addObject("tran", tran);
        mav.setViewName("/workbench/transaction/detail");
        return mav;
    }

    @RequestMapping("/changeState")
    @ResponseBody
    public Map<String, Object> changeState (Tran tran, HttpSession session) {
        TblUser user = (TblUser) session.getAttribute("user");
        tranService.changeState(user.getId(), tran);
        Map<String, Object> map = new HashMap<>();
        map.put("edittime", tran.getEdittime());
        map.put("editby", tran.getEditby());
        map.put("success", true);
        return map;
    }

}
