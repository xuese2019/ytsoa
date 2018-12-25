package com.yts.ytsoa.business.zzjg.service.impl;

import com.yts.ytsoa.business.zzjg.mapper.ZzjgMapper;
import com.yts.ytsoa.business.zzjg.model.ZzjgModel;
import com.yts.ytsoa.business.zzjg.service.ZzjgService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ZzjgServiceImpl implements ZzjgService {

    @Autowired
    private ZzjgMapper mapper;

    @Transactional
    @Override
    public ResponseResult<ZzjgModel> add(ZzjgModel model) throws Exception {
        List<ZzjgModel> list = mapper.findByzzjgmc(model.getZzjgmc());
        if (list.size() > 0)
            return new ResponseResult<>(false, "名称重复");
        mapper.add(model);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional
    @Override
    public ResponseResult<ZzjgModel> deleteById(String uuid) throws Exception {
        List<ZzjgModel> list = mapper.findByzzjgfj(uuid);
        if (list.size() > 0)
            return new ResponseResult<>(false, "请先删除下级");
        mapper.deleteById(uuid);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional
    @Override
    public ResponseResult<ZzjgModel> updateById(ZzjgModel model) throws Exception {
        if (model.getUuid() == null || model.getUuid().isEmpty())
            return new ResponseResult<>(false, "缺失主键，无法进行更新");
        List<ZzjgModel> list = mapper.findByzzjgmc(model.getZzjgmc());
        if (list.size() > 0)
            return new ResponseResult<>(false, "名称重复");
        mapper.updateById(model);
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<List<ZzjgModel>> findAll() throws Exception {
        List<ZzjgModel> list = mapper.findAll();
        if (list.size() > 0) {
            List<ZzjgModel> newList = buildByRecursive(list);
            return new ResponseResult<>(true, "成功", newList);
        }
        return new ResponseResult<>(false, "未查询到记录");
    }

    /**
     * 使用递归方法建树
     *
     * @param treeNodes
     * @return
     */
    private static List<ZzjgModel> buildByRecursive(List<ZzjgModel> treeNodes) {
        List<ZzjgModel> trees = new ArrayList<>();
        for (ZzjgModel treeNode : treeNodes) {
            if ("0".equals(treeNode.getZzjgfj())) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    private static ZzjgModel findChildren(ZzjgModel treeNode, List<ZzjgModel> treeNodes) {
        for (ZzjgModel it : treeNodes) {
            if (treeNode.getUuid().equals(it.getZzjgfj())) {
                treeNode.getList().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }

    @Override
    public ResponseResult<ZzjgModel> getById(String id) throws Exception {
        ZzjgModel one = mapper.getById(id);
        if (one != null)
            return new ResponseResult<>(true, "成功", one);
        return new ResponseResult<>(false, "未查询到记录");
    }
}
