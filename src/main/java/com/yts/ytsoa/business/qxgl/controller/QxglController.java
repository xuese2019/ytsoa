package com.yts.ytsoa.business.qxgl.controller;

import com.yts.ytsoa.business.qxgl.model.QxglModel;
import com.yts.ytsoa.business.qxgl.model.ZzQxModel;
import com.yts.ytsoa.business.qxgl.service.QxglService;
import com.yts.ytsoa.business.qxgl.service.ZzQxService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/qxgl")
public class QxglController {

    @Autowired
    private QxglService service;
    @Autowired
    private ZzQxService zzQxService;

    @RequiresAuthentication
    @RequestMapping(value = "/qxgl", method = RequestMethod.GET)
    public ResponseResult<List<QxglModel>> findAll() throws Exception {
        return service.findAll();
    }

    @RequiresAuthentication
    @RequestMapping(value = "/qxgl", method = RequestMethod.POST)
    public ResponseResult<ZzQxModel> setQx(@RequestParam("qxstr") String qxstr,
                                           @RequestParam("zzid") String zzid) throws Exception {
        List<ZzQxModel> list = new ArrayList<>();
        if (!qxstr.equals("")) {
            String[] split = qxstr.split(",");
            for (int i = 0; i < split.length; i++) {
                ZzQxModel model = new ZzQxModel();
                model.setQxid(split[i]);
                model.setZzid(zzid);
                list.add(model);
            }
        }
        return zzQxService.setQx(zzid, list);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/zzqx/{zzid}", method = RequestMethod.GET)
    public ResponseResult<List<ZzQxModel>> setQx(@PathVariable("zzid") String zzid) throws Exception {
        return zzQxService.findByZzid(zzid);
    }
}
