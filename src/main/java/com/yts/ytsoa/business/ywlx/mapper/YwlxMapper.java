package com.yts.ytsoa.business.ywlx.mapper;

import com.yts.ytsoa.business.ywlx.mapper.sql.YwlxSql;
import com.yts.ytsoa.business.ywlx.model.YwlxModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface YwlxMapper {

    String table = " ywlx_table ";

    @Insert({
            "insert into " + table + " (uuid,ywlxmc) values (replace(uuid(), '-', ''),#{model.ywlxmc})"
    })
    void add(@Param("model") YwlxModel model) throws Exception;

    @Delete({
            "delete from " + table + " where uuid = #{uuid}"
    })
    void deleteById(@Param("uuid") String uuid) throws Exception;

    @Update({
            "update " + table + " set ywlxmc = #{model.ywlxmc} where uuid = #{model.uuid}"
    })
    void updateById(@Param("model") YwlxModel model) throws Exception;

    @SelectProvider(type = YwlxSql.class, method = "findAllSql")
    List<YwlxModel> findAll(@Param("model") YwlxModel model) throws Exception;

    @Select({
            "select * from" + table + " where uuid = #{id}"
    })
    YwlxModel getById(@Param("id") String id) throws Exception;

    @Select({
            "select * from" + table + " where ywlxmc = #{ywlxmc}"
    })
    List<YwlxModel> findByYwlxmc(@Param("ywlxmc") String ywlxmc) throws Exception;

}
