package com.yts.ytsoa.business.xmfl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmfl.mapper.XmflMapper;
import com.yts.ytsoa.business.xmfl.model.XmflModel;
import com.yts.ytsoa.business.xmfl.service.XmflService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class XmflServiceImpl implements XmflService {

    @Autowired
    private XmflMapper mapper;

    @Transactional
    @Override
    public ResponseResult<XmflModel> add(XmflModel model) throws Exception {
        List<XmflModel> list = mapper.findByxmflmc(model.getXmflmc());
        if (list.size() > 0)
            return new ResponseResult<>(false, "名称重复");
        mapper.add(model);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional
    @Override
    public ResponseResult<XmflModel> deleteById(String uuid) throws Exception {
        mapper.deleteById(uuid);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional
    @Override
    public ResponseResult<XmflModel> updateById(XmflModel model) throws Exception {
        if (model.getUuid() == null || model.getUuid().isEmpty())
            return new ResponseResult<>(false, "缺失主键，无法进行更新");
        List<XmflModel> list = mapper.findByxmflmc(model.getXmflmc());
        if (list.size() > 0)
            return new ResponseResult<>(false, "名称重复");
        mapper.updateById(model);
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<PageInfo<XmflModel>> findAll(int pageNow, int pageSize, XmflModel model) throws Exception {
        PageHelper.startPage(pageNow, pageSize, "CONVERT(xmflmc USING gbk)");
        List<XmflModel> list = mapper.findAll(model);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", new PageInfo<>(list));
        return new ResponseResult<>(false, "未查询到记录");
    }

    @Override
    public ResponseResult<List<XmflModel>> findAll() throws Exception {
        List<XmflModel> list = mapper.findAll(null);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        return new ResponseResult<>(false, "未查询到记录");
    }

    @Override
    public ResponseResult<XmflModel> getById(String id) throws Exception {
        XmflModel one = mapper.getById(id);
        if (one != null)
            return new ResponseResult<>(true, "成功", one);
        return new ResponseResult<>(false, "未查询到记录");
    }
}
