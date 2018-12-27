package com.yts.ytsoa.business.xmgl.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmgl.model.XmglModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface XmglService {

    ResponseResult<XmglModel> add(XmglModel model) throws Exception;

    ResponseResult<XmglModel> deleteById(String uuid) throws Exception;

    ResponseResult<XmglModel> updateById(XmglModel model) throws Exception;

    ResponseResult<PageInfo<XmglModel>> findAll(int pageNow, int pageSize, XmglModel model) throws Exception;

    ResponseResult<XmglModel> getById(String id) throws Exception;
}
