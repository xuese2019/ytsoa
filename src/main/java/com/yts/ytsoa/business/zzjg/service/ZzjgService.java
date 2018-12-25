package com.yts.ytsoa.business.zzjg.service;

import com.yts.ytsoa.business.zzjg.model.ZzjgModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

public interface ZzjgService {

    ResponseResult<ZzjgModel> add(ZzjgModel model) throws Exception;

    ResponseResult<ZzjgModel> deleteById(String uuid) throws Exception;

    ResponseResult<ZzjgModel> updateById(ZzjgModel model) throws Exception;

    ResponseResult<List<ZzjgModel>> findAll() throws Exception;

    ResponseResult<ZzjgModel> getById(String id) throws Exception;
}
