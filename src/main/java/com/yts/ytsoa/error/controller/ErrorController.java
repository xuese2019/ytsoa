package com.yts.ytsoa.error.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/error")
public class ErrorController {

    @RequestMapping(value = "/{code}")
    public ModelAndView codes(@PathVariable("code") String code) {
        switch (code) {
            case "500":
                return new ModelAndView("/error/500");
            default:
                return new ModelAndView("/error/500");
        }
    }
}
