package com.yts.ytsoa.business.login.controller;

import com.yts.ytsoa.business.account.model.AccountModel;
import com.yts.ytsoa.business.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("/login/login")
                .addObject("model", new AccountModel());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@Valid @ModelAttribute("model") AccountModel model,
                              BindingResult result) {
        if (result.hasErrors())
            return new ModelAndView("/login/login")
                    .addObject("model", model);
        String md5Password = DigestUtils.md5DigestAsHex(model.getPassword().getBytes(StandardCharsets.UTF_8));
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(model.getAccount(), md5Password);
        try {
            subject.login(token);
            subject.getSession().setTimeout(1000 * 60 * 60 * 12);
        } catch (AuthenticationException e) {
            token.clear();
            return new ModelAndView("/login/login")
                    .addObject("model", model)
                    .addObject("errors","账号密码错误");
        }
        return new ModelAndView("/home/index")
                .addObject("user", model);
    }
}
