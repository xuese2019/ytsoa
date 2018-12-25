package com.yts.ytsoa.business.xmfl.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmfl.model.XmflModel;
import com.yts.ytsoa.business.xmfl.service.XmflService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/xmfl")
public class XmflController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private XmflService service;

    @RequiresAuthentication
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/xmfl", method = RequestMethod.POST)
    public ResponseResult<XmflModel> add(@Valid @ModelAttribute("form") XmflModel model,
                                         BindingResult result) throws Exception {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        return service.add(model);
    }

    @RequiresAuthentication
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/xmfl/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<XmflModel> deleteById(@PathVariable("uuid") String uuid) throws Exception {
        return service.deleteById(uuid);
    }

    @RequiresAuthentication
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/xmfl", method = RequestMethod.PUT)
    public ResponseResult<XmflModel> updateById(@Valid @ModelAttribute("form") XmflModel model,
                                                BindingResult result) throws Exception {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        return service.updateById(model);
    }

    @RequiresAuthentication
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/xmfl/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<PageInfo<XmflModel>> findAll(@PathVariable("pageNow") int pageNow,
                                                       @ModelAttribute("form") XmflModel model) throws Exception {
        return service.findAll(pageNow, pageSize, model);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/xmfl", method = RequestMethod.GET)
    public ResponseResult<List<XmflModel>> findAll() throws Exception {
        return service.findAll();
    }

    @RequiresAuthentication
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/oneId/{uuid}", method = RequestMethod.GET)
    public ResponseResult<XmflModel> getById(@PathVariable("uuid") String uuid) throws Exception {
        return service.getById(uuid);
    }
}
