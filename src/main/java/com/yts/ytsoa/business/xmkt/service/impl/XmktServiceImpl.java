package com.yts.ytsoa.business.xmkt.service.impl;

import com.yts.ytsoa.business.account.model.AccountModel;
import com.yts.ytsoa.business.xmgl.mapper.XmglMapper;
import com.yts.ytsoa.business.xmgl.model.XmglModel;
import com.yts.ytsoa.business.xmkt.service.XmktService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class XmktServiceImpl implements XmktService {

    @Autowired
    private XmglMapper mapper;

    @Override
    public ResponseResult<XmglModel> cjxm(String uuid) throws Exception {
        XmglModel one = mapper.getById(uuid);
        if (one == null)
            return new ResponseResult<>(false, "失败，该项目已不存在");
        AccountModel model = (AccountModel) SecurityUtils.getSubject().getPrincipal();
        if (one.getCjr().equals(model.getUuid()))
            return new ResponseResult<>(false, "失败，该项目已承接人已更换");
        one.setXmFlag(1);
        mapper.updateById(one);
        return new ResponseResult<>(true, "成功");
    }
}
