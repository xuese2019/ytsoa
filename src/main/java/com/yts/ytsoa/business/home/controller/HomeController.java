package com.yts.ytsoa.business.home.controller;

import com.yts.ytsoa.business.account.service.AccountService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private AccountService accountService;

    @RequiresAuthentication
    @RequestMapping(value = "/index")
    public ModelAndView index() throws Exception {
//        String account = (String) SecurityUtils.getSubject().getSession().getAttribute("account");
        return new ModelAndView("/home/index");
    }
}
