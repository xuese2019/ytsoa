package com.yts.ytsoa.business.account.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.account.model.AccountModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface AccountService {

    ResponseResult<AccountModel> add(AccountModel model);

    ResponseResult<AccountModel> deleteById(String uuid);

    ResponseResult<AccountModel> updateById(AccountModel model);

    ResponseResult<PageInfo<AccountModel>> findAll(int pageNow, int pageSize, AccountModel model);

    ResponseResult<List<AccountModel>> findAll(AccountModel model);

    ResponseResult<List<AccountModel>> findByAccount(AccountModel model);


}
