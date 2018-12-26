package com.yts.ytsoa.business.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.account.mapper.AccountMapper;
import com.yts.ytsoa.business.account.model.AccountModel;
import com.yts.ytsoa.business.account.model.AdminModel;
import com.yts.ytsoa.business.account.service.AccountService;
import com.yts.ytsoa.utils.GetUuid;
import com.yts.ytsoa.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper mapper;

    @Transactional
    @Override
    public ResponseResult<AccountModel> add(AccountModel model) throws Exception {
        AccountModel requestModel = new AccountModel();
        requestModel.setAccount(model.getAccount());
        List<AccountModel> list = mapper.findAll(requestModel);
        if (list.size() > 0)
            return new ResponseResult<>(false, "账户重复", null);
        else {
            mapper.add(model);
            return new ResponseResult<>(true, "成功", null);
        }
    }

    @Transactional
    @Override
    public ResponseResult<AccountModel> deleteById(String uuid) throws Exception {
        mapper.deleteById(uuid);
        return new ResponseResult<>(true, "成功", null);
    }

    @Transactional
    @Override
    public ResponseResult<AccountModel> updateById(AccountModel model) throws Exception {
        mapper.updateById(model);
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<PageInfo<AccountModel>> findAll(int pageNow, int pageSize, AccountModel model) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<AccountModel> list = mapper.findAll(model);
        PageInfo<AccountModel> page = new PageInfo<>(list);
        if (page.getSize() > 0)
            return new ResponseResult<>(true, "成功", page);
        else
            return new ResponseResult<>(false, "未查询到记录", null);
    }

    @Override
    public ResponseResult<List<AccountModel>> findAll(AccountModel model) throws Exception {
        List<AccountModel> list = mapper.findAll(model);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到记录", null);
    }

    @Override
    public ResponseResult<List<AccountModel>> findByAccount(AccountModel model) throws Exception {
        List<AccountModel> list = mapper.findByAccountAndPassword(model);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到记录", null);
    }

    @Override
    public ResponseResult<AccountModel> getAdminByAccount(AdminModel model) throws Exception {
        AccountModel model1 = mapper.getAdminByAccount(model);
        if (model1 != null)
            return new ResponseResult<>(true, "成功", model1);
        else
            return new ResponseResult<>(false, "未查询到记录", null);
    }
}
