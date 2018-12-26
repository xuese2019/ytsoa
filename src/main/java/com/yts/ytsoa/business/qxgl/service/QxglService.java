package com.yts.ytsoa.business.qxgl.service;

import com.yts.ytsoa.business.qxgl.model.QxglModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

public interface QxglService {

    ResponseResult<List<QxglModel>> findAll() throws Exception;
}
