package com.yts.ytsoa.business.qxgl.mapper;

import com.yts.ytsoa.business.qxgl.mapper.sql.ZzQxSql;
import com.yts.ytsoa.business.qxgl.model.QxglModel;
import com.yts.ytsoa.business.qxgl.model.ZzQxModel;
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
public interface ZzQxMapper {

    String table = " zz_qx_table ";

    @Select({
            "select * from " + table + " where zzid = #{zzid}"
    })
    List<ZzQxModel> findByZzid(@Param("zzid") String zzid) throws SQLException;

    @Select({
            "select q.* from account_table a"
                    + " left join zz_qx_table z on z.zzid = a.bmid"
                    + " left join qxgl_table q on q.uuid = z.qxid"
                    + " where a.uuid = #{accid}"
    })
    List<QxglModel> findByAccid(@Param("accid") String accid) throws SQLException;

    @Delete({
            "delete from " + table + " where zzid = #{zzid}"
    })
    void deleteByZzid(@Param("zzid") String zzid) throws SQLException;

    @Insert({
            "insert into " + table + " (uuid,qxid,zzid) values (replace(uuid(), '-', ''),#{model.qxid},#{model.zzid})"
    })
    void add(@Param("model") ZzQxModel model) throws SQLException;

    @InsertProvider(type = ZzQxSql.class, method = "inserts")
    void adds(@Param("list") List<ZzQxModel> list) throws SQLException;

}
