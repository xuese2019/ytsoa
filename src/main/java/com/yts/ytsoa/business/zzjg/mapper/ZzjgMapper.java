package com.yts.ytsoa.business.zzjg.mapper;

import com.yts.ytsoa.business.zzjg.model.ZzjgModel;
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
public interface ZzjgMapper {

    String table = " zzjg_table ";

    @Insert({
            "insert into " + table + " (uuid,zzjgfj,zzjgmc) values (replace(uuid(), '-', ''),#{model.zzjgfj},#{model.zzjgmc})"
    })
    void add(@Param("model") ZzjgModel model) throws SQLException;

    @Delete({
            "delete from " + table + " where uuid = #{uuid}"
    })
    void deleteById(@Param("uuid") String uuid) throws SQLException;

    @Update({
            "update " + table + " set zzjgmc = #{model.zzjgmc} where uuid = #{model.uuid}"
    })
    void updateById(@Param("model") ZzjgModel model) throws SQLException;

    @Select({
            "select * from " + table
    })
    List<ZzjgModel> findAll() throws SQLException;

    @Select({
            "select * from" + table + " where uuid = #{id}"
    })
    ZzjgModel getById(@Param("id") String id) throws SQLException;

    @Select({
            "select * from" + table + " where zzjgfj = #{zzjgfj}"
    })
    List<ZzjgModel> findByzzjgfj(@Param("zzjgfj") String zzjgfj) throws SQLException;

    @Select({
            "select * from" + table + " where zzjgmc = #{zzjgmc}"
    })
    List<ZzjgModel> findByzzjgmc(@Param("zzjgmc") String zzjgmc) throws SQLException;

}
