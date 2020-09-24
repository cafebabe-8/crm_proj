package org.crm.web.controller;


import org.crm.entity.Activity;
import org.crm.entity.Clue;
import org.crm.entity.TblUser;
import org.crm.entity.Tran;
import org.crm.service.ActivityService;
import org.crm.service.ClueActivityRelationService;
import org.crm.service.ClueService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/clue")
public class ClueController {
    @Resource
    private ClueService clueService;

    @Resource
    private ActivityService activityService;

    @Resource
    private ClueActivityRelationService clueActivityRelationService;


    @RequestMapping("/insertOrUpdate")
    @ResponseBody
    public Map<String, Object> insertOrUpdate(HttpSession session, Clue clue) {
        TblUser userInSession = (TblUser) session.getAttribute("user");
        clueService.insertOrUpdate(clue, userInSession.getId());
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @RequestMapping("/queryAll")
    @ResponseBody
    public List<Clue> queryAll() {
       return clueService.queryAllWithUser();
    }

    // 跳转到详情页时将根据id查询到的clue对象放入request作用域
    @RequestMapping("/detail.html")
    public ModelAndView jumpToDetail(String clueId) {
        ModelAndView mav = new ModelAndView();
        System.out.println(clueId);
        System.out.println("===========================================");
        Clue clue = clueService.queryById(clueId);
        mav.addObject("clue", clue);
        mav.setViewName("/workbench/clue/detail");
        return mav;
    }

    // clueid => 对应的所有活动的列表
    @RequestMapping("/queryReferAct")
    @ResponseBody
    public List<Activity> queryReferAct(String clueId) {
        return activityService.queryReferAct(clueId);
    }

    // clueId, actId => 删除关系表的对应行
    @RequestMapping("/deleteOneRelation")
    @ResponseBody
    public Map<String, Object> deleteOneRelation(String clueId, String actId) {
        clueActivityRelationService.deleteOneRelation(clueId, actId);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    // clueId, actIds => 先删除已有的关系 再添加关系
    @RequestMapping("/insertRelation")
    @ResponseBody
    public Map<String, Object> insertRelation(String clueId, String[] actIds) {
        clueActivityRelationService.insertRelation(clueId, actIds);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    // 跳转到convert页面 并且回显信息
    @RequestMapping("/convert.html")
    public ModelAndView jump2ConvertPage(String clueId) {
        ModelAndView mav = new ModelAndView();
        Clue clue = clueService.queryById(clueId);
        mav.addObject("clue", clue);
        mav.setViewName("/workbench/clue/convert");
        return mav;
    }

    // 转换clueid对应的线索
    @RequestMapping("/convert")
    @ResponseBody
    public Map<String, Object> convertClue(HttpSession session, Tran tran, String clueid, Boolean tranflag) {
        // 获取当前user的id
        TblUser user = (TblUser) session.getAttribute("user");
        clueService.convert(user.getId(), tran, clueid, tranflag);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }





}
