package org.crm.web.controller;


import org.crm.service.ChartsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/charts")
public class ChartsController {

    @Resource
    private ChartsService chartsService;

    @RequestMapping("/queryTranCount")
    @ResponseBody
    public List<Map<String, Object>> queryTranCount() {
        System.out.println(chartsService.queryTranCount());

        return chartsService.queryTranCount();
    }

}
