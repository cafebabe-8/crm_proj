package org.crm.web.controller;

import org.crm.entity.DicType;
import org.crm.service.DicTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/type")
public class DicTypeController {

     @Resource
     private DicTypeService typeService;


     @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
     @ResponseBody
     public List<DicType> queryAll(){
          System.out.println(typeService.queryAll(new DicType()));
         return typeService.queryAll(new DicType());
     }


     @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
     @ResponseBody
     public Map<String, Object> deleteById(String[] code){
          typeService.deleteById(code);
          Map<String, Object> map = new HashMap<>();
          map.put("success", true);
          return map;
     }

     @RequestMapping(value = "/insert", method = RequestMethod.POST)
     public String insert(DicType type){
          typeService.insert(type);
          return "redirect:/settings/dictionary/type/index.html";
     }

     @RequestMapping(value = "/queryById", method = RequestMethod.GET)
     @ResponseBody
     public DicType queryById(String code){
          return typeService.queryById(code);
     }

     @RequestMapping(value = "/update", method = RequestMethod.POST)
     public String update(DicType type){
          typeService.update(type);
          return "redirect:/settings/dictionary/type/index.html";
     }
}
