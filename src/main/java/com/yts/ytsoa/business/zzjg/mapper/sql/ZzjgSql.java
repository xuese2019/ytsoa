package com.yts.ytsoa.business.zzjg.mapper.sql;

import com.yts.ytsoa.business.zzjg.model.ZzjgModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class ZzjgSql {

    public String findAllSql(@Param("model") ZzjgModel model) {
        return new SQL() {
            {
                SELECT("*");
                FROM("zzjg_table");
                if (model != null) {
                    if (model.getZzjgmc() != null && !model.getZzjgmc().isEmpty()) {
                        model.setZzjgmc("%" + model.getZzjgmc() + "%");
                        WHERE("zzjgmc like #{model.zzjgmc}");
                    }
                    if (model.getUuid() != null && !model.getUuid().isEmpty())
                        WHERE("uuid = #{model.uuid}");
                    if (model.getZzjgfj() != null && !model.getZzjgfj().isEmpty())
                        WHERE("zzjgfj = #{model.zzjgfj}");
                }
            }
        }.toString();
    }
}
