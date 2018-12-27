package com.yts.ytsoa.business.xmwp.controller;

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
@RequestMapping("/xmwp")
public class XmwpController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private XmglService service;

    @RequiresAuthentication
    @RequiresPermissions(value = {"xmwpApply"})
    @RequestMapping(value = "/xmwp", method = RequestMethod.POST)
    public ResponseResult<XmglModel> add(@Validated(value = XmglApply.class)
                                         @ModelAttribute("form") XmglModel model,
                                         BindingResult result) throws Exception {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        model.setXmFlag(0);
        model.setBgFlag(0);
        model.setHtFlag(0);
        model.setGdFlag(0);
        return service.add(model);
    }

    @RequiresAuthentication
    @RequiresPermissions(value = {"xmwpDelete"})
    @RequestMapping(value = "/xmwp/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<XmglModel> deleteById(@PathVariable("uuid") String uuid) throws Exception {
        return service.deleteById(uuid);
    }

    @RequiresAuthentication
    @RequiresPermissions(value = {"xmwpUpdate"})
    @RequestMapping(value = "/xmwp", method = RequestMethod.PUT)
    public ResponseResult<XmglModel> updateById(@Validated(value = XmglApply.class)
                                                @ModelAttribute("form") XmglModel model,
                                                BindingResult result) throws Exception {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        return service.updateById(model);
    }

    @RequiresAuthentication
    @RequiresPermissions(value = {"xmwpPage"})
    @RequestMapping(value = "/xmwp/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<PageInfo<XmglModel>> findAll(@PathVariable("pageNow") int pageNow,
                                                       @ModelAttribute("form") XmglModel model) throws Exception {
        model.setXmFlag(0);
        return service.findAll(pageNow, pageSize, model);
    }

    @RequiresAuthentication
    @RequiresPermissions(value = {"xmwpOneId"})
    @RequestMapping(value = "/oneId/{uuid}", method = RequestMethod.GET)
    public ResponseResult<XmglModel> getById(@PathVariable("uuid") String uuid) throws Exception {
        return service.getById(uuid);
    }
}
