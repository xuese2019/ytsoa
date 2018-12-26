package com.yts.ytsoa.business.qxgl.mapper.sql;

import com.yts.ytsoa.business.qxgl.model.ZzQxModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.StringJoiner;

public class ZzQxSql {

    public String inserts(@Param("list") List<ZzQxModel> list) {
        StringJoiner sj = new StringJoiner("");
        list.forEach(k -> {
            String s = new SQL() {
                {
                    INSERT_INTO("zz_qx_table");
                    VALUES("uuid", "replace(uuid(), '-', '')");
                    VALUES("qxid", "'" + k.getQxid() + "'");
                    VALUES("zzid", "'" + k.getZzid() + "'");
                }
            }.toString();
            sj.add(s + ";");
        });
        String s = sj.toString().substring(0, sj.toString().length() - 1);
        return s;
    }
}
