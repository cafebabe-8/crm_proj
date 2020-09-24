package org.crm.web.controller;

import org.crm.entity.ActivityRemark;
import org.crm.entity.TblUser;
import org.crm.service.ActivityRemarkService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/activity_remark")
public class ActivityRemarkController {

    @Resource
    private ActivityRemarkService activityRemarkService;

    @RequestMapping("/queryByActivityId")
    @ResponseBody
    public List<ActivityRemark> queryByActivityId(String activityid) {
        return activityRemarkService.queryByActivityId(activityid);
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public Map<String, Object> deleteById(String id) {
        activityRemarkService.deleteById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> update(HttpSession session, ActivityRemark activityRemark) {
        TblUser user = (TblUser) session.getAttribute("user");
        activityRemark.setEditby(user.getId());
        activityRemarkService.update(activityRemark);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Map<String, Object> insert(HttpSession session, ActivityRemark activityRemark) {
        TblUser user = (TblUser) session.getAttribute("user");
        activityRemark.setCreateby(user.getId());
        activityRemarkService.insert(activityRemark);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }



    @RequestMapping("/queryById")
    @ResponseBody
    public ActivityRemark queryById(String id) {
        return activityRemarkService.queryById(id);
    }




}
