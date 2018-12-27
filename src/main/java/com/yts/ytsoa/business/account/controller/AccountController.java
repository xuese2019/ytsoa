package com.yts.ytsoa.business.account.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.account.model.AccountModel;
import com.yts.ytsoa.business.account.model.PWDModel;
import com.yts.ytsoa.business.account.service.AccountService;
import com.yts.ytsoa.utils.ResponseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<PageInfo<AccountModel>> findAll(@PathVariable("pageNow") int pageNow,
                                                          @ModelAttribute("form") AccountModel model,
                                                          HttpServletRequest request) throws Exception {
        return service.findAll(pageNow, pageSize, model);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/bmid/{bmid}", method = RequestMethod.GET)
    public ResponseResult<List<AccountModel>> findAll(@PathVariable("bmid") String bmid) throws Exception {
        AccountModel model = new AccountModel();
        model.setBmid(bmid);
        return service.findAll(model);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/account/{uuid}", method = RequestMethod.GET)
    public ResponseResult<AccountModel> getById(@PathVariable("uuid") String uuid) throws Exception {
        AccountModel model = new AccountModel();
        model.setUuid(uuid);
        ResponseResult<List<AccountModel>> result = service.findAll(model);
        return new ResponseResult<>(result.isSuccess(), result.getMessage(), result.getData() != null ? result.getData().get(0) : null);
    }

    @RequiresAuthentication
    @RequiresPermissions(value = {"accountAdd"}, logical = Logical.OR)
    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResponseResult<AccountModel> add(@Valid @ModelAttribute("form") AccountModel model,
                                            BindingResult result) throws Exception {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage(), null);
        if (model.getUuid() == null || model.getUuid().isEmpty()) {
            model.setSystimes(new Timestamp(System.currentTimeMillis()));
            //对密码进行 md5 加密
            String md5Password = DigestUtils.md5DigestAsHex(model.getPassword().getBytes(StandardCharsets.UTF_8));
            model.setPassword(md5Password);
            AccountModel user = (AccountModel) SecurityUtils.getSubject().getPrincipal();
            model.setCreatorAccId(user.getUuid());
            model.setLx(1);
            return service.add(model);
        }
        model.setAccount(null);
        model.setPassword(null);
        return service.updateById(model);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/account/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<AccountModel> deleteById(@PathVariable("uuid") String uuid) throws Exception {
        return service.deleteById(uuid);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/account", method = RequestMethod.PUT)
    public ResponseResult<AccountModel> updateById(@ModelAttribute("form") AccountModel model) throws Exception {
        model.setAccount(null);
        model.setPassword(null);
        return service.updateById(model);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/isLogin", method = RequestMethod.PUT)
    public ResponseResult<AccountModel> isLogin(@RequestParam("uuid") String uuid,
                                                @RequestParam("isLogin") String isLogin) throws Exception {
        AccountModel model = new AccountModel();
        model.setUuid(uuid);
        model.setIsLogin(isLogin);
        return service.updateById(model);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/updatePwd", method = RequestMethod.PUT)
    public ResponseResult<String> updateByAccount(@Valid @ModelAttribute("form") PWDModel model,
                                                  BindingResult result) throws Exception {
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
                model3.setUuid(user.getUuid());
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
    public ResponseResult<AccountModel> czmm(@RequestParam("uuid") String uuid) throws Exception {
        AccountModel model = new AccountModel();
        model.setUuid(uuid);
        String md5Password = DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8));
        model.setPassword(md5Password);
        return service.updateById(model);
    }

}
