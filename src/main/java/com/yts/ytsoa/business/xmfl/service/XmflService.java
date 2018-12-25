package com.yts.ytsoa.business.xmfl.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmfl.model.XmflModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

public interface XmflService {

    ResponseResult<XmflModel> add(XmflModel model) throws Exception;

    ResponseResult<XmflModel> deleteById(String uuid) throws Exception;

    ResponseResult<XmflModel> updateById(XmflModel model) throws Exception;

    ResponseResult<PageInfo<XmflModel>> findAll(int pageNow, int pageSize, XmflModel model) throws Exception;

    ResponseResult<List<XmflModel>> findAll() throws Exception;

    ResponseResult<XmflModel> getById(String id) throws Exception;
}
