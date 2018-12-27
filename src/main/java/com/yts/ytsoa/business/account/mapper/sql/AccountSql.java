package com.yts.ytsoa.business.account.mapper.sql;

import com.yts.ytsoa.business.account.model.AccountModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public class AccountSql {

    public String updateByIdSql(@Param("model") AccountModel model) {
        return new SQL() {
            {
                UPDATE("account_table");
                if (model.getPassword() != null && !model.getPassword().isEmpty())
                    SET("password=#{model.password}");
                if (model.getBmid() != null && !model.getBmid().isEmpty())
                    SET("bmid=#{model.bmid}");
                if (model.getIsLogin() != null && !model.getIsLogin().isEmpty())
                    SET("is_login = #{model.isLogin}");
                WHERE("uuid = #{model.uuid}");
            }
        }.toString();
    }

    public String findAllSql(@Param("model") AccountModel model) {
        return new SQL() {
            {
                SELECT("a.*,z.zzjgmc");
                FROM("account_table a");
                LEFT_OUTER_JOIN("zzjg_table z on z.uuid = a.bmid");
                if (model.getAccount() != null && !model.getAccount().isEmpty()) {
                    model.setAccount("%" + model.getAccount() + "%");
                    WHERE("a.account like #{model.account}");
                }
                if (model.getName() != null && !model.getName().isEmpty()) {
                    model.setName("%" + model.getName() + "%");
                    WHERE("a.name like #{model.name}");
                }
                if (model.getUuid() != null && !model.getUuid().isEmpty())
                    WHERE("a.uuid=#{model.uuid}");
            }
        }.toString();
    }
}
