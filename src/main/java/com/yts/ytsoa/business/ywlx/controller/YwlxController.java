package com.yts.ytsoa.business.ywlx.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.ywlx.model.YwlxModel;
import com.yts.ytsoa.business.ywlx.service.YwlxService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/ywlx")
public class YwlxController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private YwlxService service;

    @RequiresAuthentication
    @RequestMapping(value = "/ywlx", method = RequestMethod.POST)
    public ResponseResult<YwlxModel> add(@Valid @ModelAttribute("form") YwlxModel model,
                                         BindingResult result) throws Exception {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        return service.add(model);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/ywlx/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<YwlxModel> deleteById(@PathVariable("uuid") String uuid) throws Exception {
        return service.deleteById(uuid);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/ywlx", method = RequestMethod.PUT)
    public ResponseResult<YwlxModel> updateById(@Valid @ModelAttribute("form") YwlxModel model,
                                                BindingResult result) throws Exception {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        return service.updateById(model);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/ywlx/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<PageInfo<YwlxModel>> findAll(@PathVariable("pageNow") int pageNow,
                                                       @ModelAttribute("form") YwlxModel model) throws Exception {
        return service.findAll(pageNow, pageSize, model);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/oneId/{uuid}", method = RequestMethod.GET)
    public ResponseResult<YwlxModel> getById(@PathVariable("uuid") String uuid) throws Exception {
        return service.getById(uuid);
    }
}
