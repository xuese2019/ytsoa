package com.yts.ytsoa.business.xmfl.mapper.sql;

import com.yts.ytsoa.business.xmfl.model.XmflModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class XmflSql {

    public String findAllSql(@Param("model") XmflModel model) {
        return new SQL() {
            {
                SELECT("*");
                FROM("xmfl_table");
                if (model != null) {
                    if (model.getXmflmc() != null && !model.getXmflmc().isEmpty()) {
                        model.setXmflmc("%" + model.getXmflmc() + "%");
                        WHERE("xmflmc like #{model.xmflmc}");
                    }
                    if (model.getUuid() != null && !model.getUuid().isEmpty())
                        WHERE("uuid = #{model.uuid}");
                }
            }
        }.toString();
    }
}
