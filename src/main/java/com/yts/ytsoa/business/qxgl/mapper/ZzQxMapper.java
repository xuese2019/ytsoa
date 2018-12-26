package com.yts.ytsoa.business.qxgl.mapper;

import com.yts.ytsoa.business.qxgl.mapper.sql.ZzQxSql;
import com.yts.ytsoa.business.qxgl.model.ZzQxModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface ZzQxMapper {

    String table = " zz_qx_table ";

    @Select({
            "select * from " + table + " where zzid = #{zzid}"
    })
    List<ZzQxModel> findByZzid(@Param("zzid") String zzid);

    @Select({
            "select * from " + table + " z " +
                    "left join zzjg_table jg on jg.uuid = z.zzid " +
                    "left join account_table a on a.bmid = jg.uuid " +
                    "where a.uuid = #{accid}"
    })
    List<ZzQxModel> findByAccid(@Param("accid") String accid);

    @Delete({
            "delete from " + table + " where zzid = #{zzid}"
    })
    void deleteByZzid(@Param("zzid") String zzid);

    @Insert({
            "insert into " + table + " (uuid,qxid,zzid) values (replace(uuid(), '-', ''),#{model.qxid},#{model.zzid})"
    })
    void add(@Param("model") ZzQxModel model);

    @InsertProvider(type = ZzQxSql.class,method = "inserts")
    void adds(@Param("list") List<ZzQxModel> list);

}
