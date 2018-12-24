package com.yts.ytsoa.business.account.mapper;

import com.yts.ytsoa.business.account.mapper.sql.AccountSql;
import com.yts.ytsoa.business.account.model.AccountModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface AccountMapper {

    String table = " account_table ";

    @Insert({
            "insert into " + table + " (uuid,account,password,salt,lx,parents,bmid,is_login,systimes,creator_acc_id,name,phone) " +
                    " values (#{model.uuid},#{model.account},#{model.password},#{model.salt},#{model.lx},#{model.parents}," +
                    "#{model.isLogin},#{model.systimes},#{model.creatorAccId},#{model.name},#{model.phone})"
    })
    void add(@Param("model") AccountModel model);

    @Delete({
            "delete from " + table + " where uuid = #{uuid}"
    })
    void deleteById(@Param("uuid") String uuid);

    @UpdateProvider(type = AccountSql.class, method = "updateByIdSql")
    void updateById(@Param("model") AccountModel model);

    @SelectProvider(type = AccountSql.class, method = "findAllSql")
    @Results(id = "accountMap", value = {
            @Result(property = "isLogin", column = "is_login"),
            @Result(property = "creatorAccId", column = "creator_acc_id"),
    })
    List<AccountModel> findAll(@Param("model") AccountModel model);

    @Select({
            "select * from account_table a where a.account = #{model.account}"
    })
    List<AccountModel> findByAccountAndPassword(@Param("model") AccountModel model);

}
