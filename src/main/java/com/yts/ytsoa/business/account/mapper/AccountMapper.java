package com.yts.ytsoa.business.account.mapper;

import com.yts.ytsoa.business.account.mapper.sql.AccountSql;
import com.yts.ytsoa.business.account.model.AccountModel;
import com.yts.ytsoa.business.account.model.AdminModel;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
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
            "insert into " + table + " (uuid,name,account,password,lx,bmid,is_login,systimes,rzrq,creator_acc_id,sex,phone) " +
                    " values (replace(uuid(), '-', ''),#{model.name},#{model.account},#{model.password},#{model.lx},#{model.bmid}," +
                    "#{model.isLogin},#{model.systimes},#{model.rzrq},#{model.creatorAccId},#{model.sex},#{model.phone})"
    })
    void add(@Param("model") AccountModel model) throws SQLException;

    @Delete({
            "delete from " + table + " where uuid = #{uuid}"
    })
    void deleteById(@Param("uuid") String uuid) throws SQLException;

    @UpdateProvider(type = AccountSql.class, method = "updateByIdSql")
    void updateById(@Param("model") AccountModel model) throws SQLException;

    @SelectProvider(type = AccountSql.class, method = "findAllSql")
    @Results(id = "accountMap", value = {
            @Result(property = "isLogin", column = "is_login"),
            @Result(property = "creatorAccId", column = "creator_acc_id"),
            @Result(property = "bmid", column = "zzjgmc")
    })
    List<AccountModel> findAll(@Param("model") AccountModel model) throws SQLException;

    @Select({
            "select * from " + table + " where account = #{account}"
    })
    List<AccountModel> findByAccount(@Param("account") String account) throws SQLException;

    @Select({
            "select * from account_table a where a.account = #{model.account}"
    })
    @ResultMap(value = "accountMap")
    List<AccountModel> findByAccountAndPassword(@Param("model") AccountModel model) throws SQLException;

    @Select({
            "select * from admin_table a where a.account = #{model.account}"
    })
    AccountModel getAdminByAccount(@Param("model") AdminModel model) throws SQLException;

}
