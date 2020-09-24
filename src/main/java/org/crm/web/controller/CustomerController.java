package org.crm.web.controller;

import org.crm.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Resource
    private CustomerService customerService;

    @RequestMapping("/queryName")
    @ResponseBody
    public List<String> queryName(String name) {
        return customerService.queryName(name);
    }
}
