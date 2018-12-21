package com.yts.ytsoa.business.account.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.account.model.AccountModel;
import com.yts.ytsoa.business.account.service.AccountService;
import com.yts.ytsoa.utils.ResponseResult;
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
@RequestMapping("/data/account")
public class AccountController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private AccountService service;

    @RequestMapping(value = "/account/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<AccountModel>> findAll(@PathVariable("pageNow") int pageNow,
                                                          @RequestBody AccountModel model,
                                                          HttpServletRequest request) {
        return service.findAll(pageNow, pageSize, model);
    }

    @RequestMapping(value = "/account/{uuid}", method = RequestMethod.GET)
    public ResponseResult<AccountModel> getById(@PathVariable("uuid") String uuid) {
        AccountModel model = new AccountModel();
        model.setUuid(uuid);
        ResponseResult<List<AccountModel>> result = service.findAll(model);
        return new ResponseResult<>(result.isSuccess(), result.getMessage(), result.getData() != null ? result.getData().get(0) : null);
    }


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


    @RequestMapping(value = "/account/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<AccountModel> deleteById(@PathVariable("uuid") String uuid) {
        return service.deleteById(uuid);
    }


    @RequestMapping(value = "/account", method = RequestMethod.PUT)
    public ResponseResult<AccountModel> updateById(@ModelAttribute("form") AccountModel model) {
        return service.updateById(model, false);
    }


    @RequestMapping(value = "/isLogin", method = RequestMethod.PUT)
    public ResponseResult<AccountModel> isLogin(@RequestParam("uuid") String uuid,
                                                @RequestParam("isLogin") String isLogin) {
        AccountModel model = new AccountModel();
        model.setUuid(uuid);
        model.setIsLogin(isLogin);
        return service.updateById(model, false);
    }


    @RequestMapping(value = "/pwd", method = RequestMethod.PUT)
    public ResponseResult<AccountModel> updateByAccount(HttpServletRequest request,
                                                        @ModelAttribute("form") AccountModel model) {
        if (!model.getPassword().isEmpty()) {
            String md5Password = DigestUtils.md5DigestAsHex(model.getPassword().getBytes(StandardCharsets.UTF_8));
            model.setPassword(md5Password);
            return service.updateById(model, false);
        } else
            return new ResponseResult<>(false, "密码不能为空", null);
    }

    @RequestMapping(value = "/czmm", method = RequestMethod.PUT)
    public ResponseResult<AccountModel> czmm(@RequestParam("uuid") String uuid) {
        AccountModel model = new AccountModel();
        model.setUuid(uuid);
        String md5Password = DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8));
        model.setPassword(md5Password);
        return service.updateById(model, false);
    }

}
