package com.yts.ytsoa.business.xmgl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmgl.mapper.XmglMapper;
import com.yts.ytsoa.business.xmgl.model.XmglModel;
import com.yts.ytsoa.business.xmgl.service.XmglService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class XmglServiceImpl implements XmglService {

    @Autowired
    private XmglMapper mapper;

    @Override
    public ResponseResult<XmglModel> add(XmglModel model) throws Exception {
        mapper.add(model);
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<XmglModel> deleteById(String uuid) throws Exception {
        XmglModel one = mapper.getById(uuid);
        if (one != null) {
            if (one.getXmFlag() > 0)
                return new ResponseResult<>(false, "项目已开始，无法删除");
        }
        mapper.deleteById(uuid);
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<XmglModel> updateById(XmglModel model) throws Exception {
        if (model.getUuid() == null || model.getUuid().isEmpty())
            return new ResponseResult<>(false, "缺失主键，无法进行更新");
        mapper.updateById(model);
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<PageInfo<XmglModel>> findAll(int pageNow, int pageSize, XmglModel model) throws Exception {
//        PageHelper.startPage(pageNow, pageSize, "CONVERT(xmmc USING gbk)");
        PageHelper.startPage(pageNow, pageSize, "wtsj");
        List<XmglModel> list = mapper.findAll(model);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", new PageInfo<>(list));
        return new ResponseResult<>(false, "未查询到记录");
    }

    @Override
    public ResponseResult<XmglModel> getById(String id) throws Exception {
        XmglModel one = mapper.getById(id);
        if (one != null)
            return new ResponseResult<>(true, "成功", one);
        return new ResponseResult<>(false, "未查询到记录");
    }
}
