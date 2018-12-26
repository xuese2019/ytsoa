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
                SELECT("*");
                FROM("account_table");
                if (model.getAccount() != null && !model.getAccount().isEmpty()) {
                    model.setAccount("%" + model.getAccount() + "%");
                    WHERE("account like #{model.account}");
                }
                if (model.getName() != null && !model.getName().isEmpty()) {
                    model.setName("%" + model.getName() + "%");
                    WHERE("name like #{model.name}");
                }
                if (model.getUuid() != null && !model.getUuid().isEmpty())
                    WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }
}
