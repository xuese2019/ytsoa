package com.yts.ytsoa.business.qxgl.service;

import com.yts.ytsoa.business.qxgl.model.QxglModel;
import com.yts.ytsoa.business.qxgl.model.ZzQxModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

public interface ZzQxService {

    ResponseResult<ZzQxModel> setQx(String zzid, List<ZzQxModel> list);

    ResponseResult<List<ZzQxModel>> findByZzid(String zzid);

    ResponseResult<List<QxglModel>> findByAccid(String accid);
}
