package com.yts.ytsoa.business.zzjg.controller;

import com.yts.ytsoa.business.zzjg.model.ZzjgModel;
import com.yts.ytsoa.business.zzjg.service.ZzjgService;
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
@RequestMapping("/zzjg")
public class ZzjgController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private ZzjgService service;

    @RequiresAuthentication
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/zzjg", method = RequestMethod.POST)
    public ResponseResult<ZzjgModel> add(@Valid @ModelAttribute("form") ZzjgModel model,
                                         BindingResult result) throws Exception {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        if (model.getUuid() != null && !model.getUuid().isEmpty())
            return service.updateById(model);
        else
            return service.add(model);
    }

    @RequiresAuthentication
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/zzjg/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<ZzjgModel> deleteById(@PathVariable("uuid") String uuid) throws Exception {
        return service.deleteById(uuid);
    }

    @RequiresAuthentication
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/zzjg", method = RequestMethod.PUT)
    public ResponseResult<ZzjgModel> updateById(@Valid @ModelAttribute("form") ZzjgModel model,
                                                BindingResult result) throws Exception {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        return service.updateById(model);
    }

    @RequiresAuthentication
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/zzjg/{uuid}", method = RequestMethod.GET)
    public ResponseResult<ZzjgModel> updateById(@PathVariable("uuid") String uuid) throws Exception {
        return service.getById(uuid);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/recursive", method = RequestMethod.GET)
    public ResponseResult<List<ZzjgModel>> recursive() throws Exception {
        return service.findAll();
    }
}
