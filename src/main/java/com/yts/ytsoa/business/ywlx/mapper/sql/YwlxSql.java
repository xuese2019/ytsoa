package com.yts.ytsoa.business.ywlx.mapper.sql;

import com.yts.ytsoa.business.ywlx.model.YwlxModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class YwlxSql {

    public String findAllSql(@Param("model") YwlxModel model) {
        return new SQL() {
            {
                SELECT("*");
                FROM("ywlx_table");
                if (model != null) {
                    if (model.getYwlxmc() != null && !model.getYwlxmc().isEmpty()) {
                        model.setYwlxmc("%" + model.getYwlxmc() + "%");
                        WHERE("ywlxmc like #{model.ywlxmc}");
                    }
                    if (model.getUuid() != null && !model.getUuid().isEmpty())
                        WHERE("uuid = #{model.uuid}");
                }
            }
        }.toString();
    }
}
