package com.yts.ytsoa.index.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
public class IndexController {

    @RequestMapping(value = "/")
    public ModelAndView index() throws Exception {
        return new ModelAndView("redirect:/index");
    }

    @RequestMapping(value = "/index")
    public ModelAndView index2() throws Exception {
        return new ModelAndView("/index");
    }

}
