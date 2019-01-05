package com.yts.ytsoa.business.login.controller;

import com.yts.ytsoa.business.account.model.AccountModel;
import com.yts.ytsoa.business.account.service.AccountService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() throws Exception {
        return new ModelAndView("/login/login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult<String> login(@Valid @ModelAttribute("form") AccountModel model,
                                        BindingResult result) throws Exception {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage(), null);
        String md5Password = DigestUtils.md5DigestAsHex(model.getPassword().getBytes(StandardCharsets.UTF_8));
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(model.getAccount(), md5Password);
        try {
            subject.login(token);
            subject.getSession().setTimeout(1000 * 60 * 60 * 12);
            AccountModel model1 = (AccountModel) SecurityUtils.getSubject().getPrincipal();
            model1.setPassword(null);
        } catch (IncorrectCredentialsException e) {
            token.clear();
            return new ResponseResult<>(false, "账号密码错误");
        } catch (AuthenticationException e1) {
            token.clear();
            return new ResponseResult<>(false, e1.getMessage());
        }catch (RuntimeException e2){
            token.clear();
            return new ResponseResult<>(false, e2.getMessage());
        }
        return new ResponseResult<>(true, "成功", "/views/home/index");
    }

    @RequiresAuthentication
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout() throws Exception {
        SecurityUtils.getSubject().logout();
        return new ModelAndView("redirect:/index");
    }
}
