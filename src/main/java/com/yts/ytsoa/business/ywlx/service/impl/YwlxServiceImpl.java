package com.yts.ytsoa.business.ywlx.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.ywlx.mapper.YwlxMapper;
import com.yts.ytsoa.business.ywlx.model.YwlxModel;
import com.yts.ytsoa.business.ywlx.service.YwlxService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class YwlxServiceImpl implements YwlxService{

    @Autowired
    private YwlxMapper mapper;

    @Override
    public ResponseResult<YwlxModel> add(YwlxModel model) throws Exception {
        YwlxModel model1 = new YwlxModel();
        model1.setYwlxmc(model.getYwlxmc());
        List<YwlxModel> list = mapper.findAll(model1);
        if (list.size() > 0)
            return new ResponseResult<>(false, "名称重复");
        mapper.add(model);
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<YwlxModel> deleteById(String uuid) throws Exception {
        mapper.deleteById(uuid);
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<YwlxModel> updateById(YwlxModel model) throws Exception {
        if (model.getUuid() == null || model.getUuid().isEmpty())
            return new ResponseResult<>(false, "缺失主键，无法进行更新");
        YwlxModel model1 = new YwlxModel();
        model1.setYwlxmc(model.getYwlxmc());
        List<YwlxModel> list = mapper.findAll(model1);
        if (list.size() > 0)
            return new ResponseResult<>(false, "名称重复");
        mapper.updateById(model);
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<PageInfo<YwlxModel>> findAll(int pageNow, int pageSize, YwlxModel model) throws Exception {
        PageHelper.startPage(pageNow, pageSize, "ywlxmc");
        Page<YwlxModel> page = mapper.findAll(model);
        if (page.size() > 0)
            return new ResponseResult<>(true, "成功", new PageInfo<>(page));
        return new ResponseResult<>(false, "未查询到记录");
    }

    @Override
    public ResponseResult<YwlxModel> getById(String id) throws Exception {
        YwlxModel one = mapper.getById(id);
        if (one != null)
            return new ResponseResult<>(true, "成功", one);
        return new ResponseResult<>(false, "未查询到记录");
    }
}
