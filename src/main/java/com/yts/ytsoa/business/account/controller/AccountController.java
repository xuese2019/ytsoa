package com.yts.ytsoa.business.account.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.account.model.AccountModel;
import com.yts.ytsoa.business.account.model.PWDModel;
import com.yts.ytsoa.business.account.service.AccountService;
import com.yts.ytsoa.utils.ResponseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private AccountService service;

    @RequiresAuthentication
    @RequestMapping(value = "/account/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<AccountModel>> findAll(@PathVariable("pageNow") int pageNow,
                                                          @RequestBody AccountModel model,
                                                          HttpServletRequest request) {
        return service.findAll(pageNow, pageSize, model);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/account/{uuid}", method = RequestMethod.GET)
    public ResponseResult<AccountModel> getById(@PathVariable("uuid") String uuid) {
        AccountModel model = new AccountModel();
        model.setUuid(uuid);
        ResponseResult<List<AccountModel>> result = service.findAll(model);
        return new ResponseResult<>(result.isSuccess(), result.getMessage(), result.getData() != null ? result.getData().get(0) : null);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResponseResult<AccountModel> add(@Valid @ModelAttribute("form") AccountModel model,
                                            BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage(), null);
        model.setIsLogin("Y");
        model.setSystimes(new Timestamp(System.currentTimeMillis()));
        //对密码进行 md5 加密
        String md5Password = DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8));
        model.setPassword(md5Password);
        return service.add(model);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/account/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<AccountModel> deleteById(@PathVariable("uuid") String uuid) {
        return service.deleteById(uuid);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/account", method = RequestMethod.PUT)
    public ResponseResult<AccountModel> updateById(@ModelAttribute("form") AccountModel model) {
        return service.updateById(model);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/isLogin", method = RequestMethod.PUT)
    public ResponseResult<AccountModel> isLogin(@RequestParam("uuid") String uuid,
                                                @RequestParam("isLogin") String isLogin) {
        AccountModel model = new AccountModel();
        model.setUuid(uuid);
        model.setIsLogin(isLogin);
        return service.updateById(model);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/updatePwd", method = RequestMethod.PUT)
    public ResponseResult<String> updateByAccount(@Valid @ModelAttribute("form") PWDModel model,
                                                  BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage(), null);

        AccountModel user = (AccountModel) SecurityUtils.getSubject().getPrincipal();
        AccountModel model1 = new AccountModel();
        model1.setUuid(user.getUuid());
        ResponseResult<List<AccountModel>> result2 = service.findAll(model1);
        if (result2.isSuccess()) {
            if (model.isPasswordOk()) {
                String md5Password2 = DigestUtils.md5DigestAsHex(model.getPasswordBack().getBytes(StandardCharsets.UTF_8));
                if (!md5Password2.equals(result2.getData().get(0).getPassword()))
                    return new ResponseResult<>(false, "原密码错误", null);
                AccountModel model3 = new AccountModel();
                model3.setUuid(model.getUuid());
                String md5Password = DigestUtils.md5DigestAsHex(model.getPassword().getBytes(StandardCharsets.UTF_8));
                model3.setPassword(md5Password);
                ResponseResult<AccountModel> result1 = service.updateById(model3);
                if (result1.isSuccess())
                    return new ResponseResult<>(true, "成功,请从新登陆", "/login/logout");
                else
                    return new ResponseResult<>(false, "当前账户已不存在", null);
            } else
                return new ResponseResult<>(false, "两次输入的密码不一致", null);
        } else
            return new ResponseResult<>(false, "当前账户已不存在", null);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/czmm", method = RequestMethod.PUT)
    public ResponseResult<AccountModel> czmm(@RequestParam("uuid") String uuid) {
        AccountModel model = new AccountModel();
        model.setUuid(uuid);
        String md5Password = DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8));
        model.setPassword(md5Password);
        return service.updateById(model);
    }

}
