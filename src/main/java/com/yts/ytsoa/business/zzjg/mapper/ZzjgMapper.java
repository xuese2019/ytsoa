package com.yts.ytsoa.business.zzjg.mapper;

import com.yts.ytsoa.business.zzjg.model.ZzjgModel;
import org.apache.ibatis.annotations.*;

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
    void add(@Param("model") ZzjgModel model) throws Exception;

    @Delete({
            "delete from " + table + " where uuid = #{uuid}"
    })
    void deleteById(@Param("uuid") String uuid) throws Exception;

    @Update({
            "update " + table + " set zzjgmc = #{model.zzjgmc} where uuid = #{model.uuid}"
    })
    void updateById(@Param("model") ZzjgModel model) throws Exception;

    @Select({
            "select * from " + table
    })
    List<ZzjgModel> findAll() throws Exception;

    @Select({
            "select * from" + table + " where uuid = #{id}"
    })
    ZzjgModel getById(@Param("id") String id) throws Exception;

    @Select({
            "select * from" + table + " where zzjgfj = #{zzjgfj}"
    })
    List<ZzjgModel> findByzzjgfj(@Param("zzjgfj") String zzjgfj) throws Exception;

    @Select({
            "select * from" + table + " where zzjgmc = #{zzjgmc}"
    })
    List<ZzjgModel> findByzzjgmc(@Param("zzjgmc") String zzjgmc) throws Exception;

}
