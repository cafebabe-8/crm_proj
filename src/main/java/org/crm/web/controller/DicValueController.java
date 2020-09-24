package org.crm.web.controller;


import org.crm.entity.DicValue;
import org.crm.service.DicValueService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/value")
public class DicValueController {

    @Resource
    DicValueService valueService;


    @RequestMapping("/queryAll")
    @ResponseBody
    public List<DicValue> queryAll(){
        return valueService.queryAll(new DicValue());
    }

    @RequestMapping("/queryById")
    @ResponseBody
    public DicValue queryById(String id){
        return valueService.queryById(id);
    }



    // 增加记录和修改记录都由这个方法处理 判断交给service层
    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> update(DicValue value){
        valueService.update(value);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @RequestMapping("/deleteByIds")
    @ResponseBody
    public Map<String, Object> deleteById(String[] ids){
        valueService.deleteByIds(ids);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }



}
