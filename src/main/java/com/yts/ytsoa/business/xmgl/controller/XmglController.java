package com.yts.ytsoa.business.xmgl.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmgl.model.XmglModel;
import com.yts.ytsoa.business.xmgl.model.interfaces.XmglApply;
import com.yts.ytsoa.business.xmgl.service.XmglService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/xmgl")
public class XmglController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private XmglService service;

    @RequiresAuthentication
    @RequiresPermissions(value = {"xmglDelete"})
    @RequestMapping(value = "/xmgl/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<XmglModel> deleteById(@PathVariable("uuid") String uuid) throws Exception {
        return service.deleteById(uuid);
    }

    @RequiresAuthentication
    @RequiresPermissions(value = {"xmglUpdate"})
    @RequestMapping(value = "/xmgl", method = RequestMethod.PUT)
    public ResponseResult<XmglModel> updateById(@Validated(value = XmglApply.class)
                                                @ModelAttribute("form") XmglModel model,
                                                BindingResult result) throws Exception {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        return service.updateById(model);
    }

    @RequiresAuthentication
    @RequiresPermissions(value = {"xmglPage"})
    @RequestMapping(value = "/xmgl/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<PageInfo<XmglModel>> findAll(@PathVariable("pageNow") int pageNow,
                                                       @ModelAttribute("form") XmglModel model) throws Exception {
//        AccountModel user = (AccountModel) SecurityUtils.getSubject().getPrincipal();
//        model.setCjr(user.getUuid());
        return service.findAll(pageNow, pageSize, model);
    }

    @RequiresAuthentication
    @RequiresPermissions(value = {"xmglOneId"})
    @RequestMapping(value = "/oneId/{uuid}", method = RequestMethod.GET)
    public ResponseResult<XmglModel> getById(@PathVariable("uuid") String uuid) throws Exception {
        return service.getById(uuid);
    }
}
