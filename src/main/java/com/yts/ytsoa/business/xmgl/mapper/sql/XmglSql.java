package com.yts.ytsoa.business.xmgl.mapper.sql;

import com.yts.ytsoa.business.xmgl.model.XmglModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public class XmglSql {

    String table = " xmgl_table ";

    public String findAllSql(@Param("model") XmglModel model) {
        return new SQL() {
            {
                SELECT("x.*,y.ywlxmc as ywlx2,xf.xmflmc as xmfl2,z.zzjgmc as cjbm2,a.name as cjr2,acc.name as qrwcshr2," +
                        "CASE x.fxpg WHEN 1 THEN '低' ELSE (CASE x.fxpg WHEN 2 THEN '中' ELSE '高' END) END AS fxpg2");
                FROM(table + " x");
                LEFT_OUTER_JOIN("ywlx_table y on y.uuid = x.ywlx");
                LEFT_OUTER_JOIN("xmfl_table xf on xf.uuid = x.xmfl");
                LEFT_OUTER_JOIN("zzjg_table z on z.uuid = x.cjbm");
                LEFT_OUTER_JOIN("account_table a on a.uuid = x.cjr");
                LEFT_OUTER_JOIN("account_table acc on acc.uuid = x.qrwcshr");
                if (model != null) {
                    if (model.getXmFlag() != null)
                        WHERE("x.xm_flag = #{model.xmFlag}");
                    if (model.getUuid() != null && !model.getUuid().isEmpty())
                        WHERE("x.uuid = #{model.uuid}");
                    if ((model.getCjr() != null && !model.getCjr().isEmpty()) &&
                            (model.getQrwcshr() != null && !model.getQrwcshr().isEmpty())) {
                        WHERE("x.cjr = #{model.cjr}");
                        OR();
                        WHERE("x.qrwcshr = #{model.qrwcshr}");
                    } else {
                        if (model.getCjr() != null && !model.getCjr().isEmpty())
                            WHERE("x.cjr = #{model.cjr}");
                        if (model.getQrwcshr() != null && !model.getQrwcshr().isEmpty())
                            WHERE("x.qrwcshr = #{model.qrwcshr}");
                    }
                    if (model.getXmmc() != null && !model.getXmmc().isEmpty()) {
                        model.setXmmc("%" + model.getXmmc() + "%");
                        WHERE("x.xmmc like #{model.xmmc}");
                    }
                }
            }
        }.toString();
    }

}
