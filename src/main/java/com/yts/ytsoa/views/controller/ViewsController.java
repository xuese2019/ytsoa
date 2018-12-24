package com.yts.ytsoa.views.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.StringJoiner;

@RestController
@RequestMapping("/views")
public class ViewsController {

    @RequiresAuthentication
    @RequiresPermissions(value = {"abc"})
//    @RequiresPermissions(value = {"abc"},logical = Logical.OR)
    @RequestMapping(value = "/{a}/{b}")
    public ModelAndView views(@PathVariable("a") String a,
                              @PathVariable("b") String b) {
        StringJoiner sj = new StringJoiner("");
        sj.add("/").add(a).add("/").add(b);
        return new ModelAndView(sj.toString());
    }
}
