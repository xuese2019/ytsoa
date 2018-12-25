package com.yts.ytsoa.business.ywlx.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.ywlx.model.YwlxModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

public interface YwlxService {

    ResponseResult<YwlxModel> add(YwlxModel model) throws Exception;

    ResponseResult<YwlxModel> deleteById(String uuid) throws Exception;

    ResponseResult<YwlxModel> updateById(YwlxModel model) throws Exception;

    ResponseResult<PageInfo<YwlxModel>> findAll(int pageNow, int pageSize, YwlxModel model) throws Exception;

    ResponseResult<List<YwlxModel>> findAll() throws Exception;

    ResponseResult<YwlxModel> getById(String id) throws Exception;
}
