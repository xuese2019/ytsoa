package com.yts.ytsoa.business.qxgl.service.impl;

import com.yts.ytsoa.business.qxgl.mapper.ZzQxMapper;
import com.yts.ytsoa.business.qxgl.model.QxglModel;
import com.yts.ytsoa.business.qxgl.model.ZzQxModel;
import com.yts.ytsoa.business.qxgl.service.ZzQxService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ZzQxServiceImpl implements ZzQxService {

    @Autowired
    private ZzQxMapper mapper;

    @Override
    public ResponseResult<List<ZzQxModel>> findByZzid(String zzid) {
        List<ZzQxModel> list = mapper.findByZzid(zzid);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        return new ResponseResult<>(false, "未查询到记录");
    }

    @Override
    public ResponseResult<List<QxglModel>> findByAccid(String accid) {
        List<QxglModel> list = mapper.findByAccid(accid);
        if (list.size() > 0 && list.get(0) != null)
            return new ResponseResult<>(true, "成功", list);
        return new ResponseResult<>(false, "未查询到记录");
    }

    @Transactional
    @Override
    public ResponseResult<ZzQxModel> setQx(String zzid, List<ZzQxModel> list) {
        mapper.deleteByZzid(zzid);
        if (list != null && list.size() > 0)
            mapper.adds(list);
        return new ResponseResult<>(true, "成功");
    }
}
