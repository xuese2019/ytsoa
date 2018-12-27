package com.yts.ytsoa.business.xmkt.service;

import com.yts.ytsoa.business.xmgl.model.XmglModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface XmktService {

    ResponseResult<XmglModel> cjxm(String uuid) throws Exception;
}
