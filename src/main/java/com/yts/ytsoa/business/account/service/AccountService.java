package com.yts.ytsoa.business.account.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.account.model.AccountModel;
import com.yts.ytsoa.business.account.model.AdminModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface AccountService {

    ResponseResult<AccountModel> add(AccountModel model) throws Exception;

    ResponseResult<AccountModel> deleteById(String uuid) throws Exception;

    ResponseResult<AccountModel> updateById(AccountModel model) throws Exception;

    ResponseResult<PageInfo<AccountModel>> findAll(int pageNow, int pageSize, AccountModel model) throws Exception;

    ResponseResult<List<AccountModel>> findAll(AccountModel model) throws Exception;

    ResponseResult<List<AccountModel>> findByAccount(AccountModel model) throws Exception;

    ResponseResult<AccountModel> getAdminByAccount(AdminModel model) throws Exception;
}
