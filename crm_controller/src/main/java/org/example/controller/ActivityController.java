package org.example.controller;

import org.example.entity.Activity;
import org.example.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Resource
    private ActivityService activityService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public List<Activity> queryAll() {
        List<Activity> temp = activityService.queryAll();
        System.out.println("=========================");
        System.out.println(temp);
        return activityService.queryAll();
    }
}
