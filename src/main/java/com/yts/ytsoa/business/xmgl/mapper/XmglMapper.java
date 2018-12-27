package com.yts.ytsoa.business.xmgl.mapper;

import com.yts.ytsoa.business.xmgl.mapper.sql.XmglSql;
import com.yts.ytsoa.business.xmgl.model.XmglModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface XmglMapper {

    String table = " xmgl_table ";

    @Insert({
            "insert into " + table + " (uuid,xmmc,wtf,wtsj,bsjdw,fxpg,ywlx,xmfl,cjbm,xmkssj,xmjssj,yjsf,xm_flag,ht_flag," +
                    "bg_flag,wk_flag,gd_flag,cjr,qrwcshr,xmcjsj) " +
                    " values " +
                    " (replace(uuid(), '-', ''),#{model.xmmc},#{model.wtf},#{model.wtsj},#{model.bsjdw},#{model.fxpg}," +
                    "#{model.ywlx},#{model.xmfl},#{model.cjbm},#{model.xmkssj},#{model.xmjssj},#{model.yjsf},#{model.xmFlag}," +
                    "#{model.htFlag},#{model.bgFlag},#{model.wkFlag},#{model.gdFlag},#{model.cjr},#{model.qrwcshr},#{model.xmcjsj})"
    })
    void add(@Param("model") XmglModel model) throws Exception;

    @Delete({
            "delete from " + table + " where uuid = #{uuid}"
    })
    void deleteById(@Param("uuid") String uuid) throws Exception;

    @Update({
            "update " + table + " set xm_flag = #{model.xmFlag} where uuid = #{model.uuid}"
    })
    void updateById(@Param("model") XmglModel model) throws Exception;

    @SelectProvider(type = XmglSql.class, method = "findAllSql")
    @Results(id = "xmglMap", value = {
            @Result(property = "ywlx", column = "ywlx2"),
            @Result(property = "xmfl", column = "xmfl2"),
            @Result(property = "cjbm", column = "cjbm2"),
            @Result(property = "cjr", column = "cjr2"),
            @Result(property = "qrwcshr", column = "qrwcshr2"),
            @Result(property = "xmFlag", column = "xm_flag"),
            @Result(property = "fxpg", column = "fxpg2")
    })
    List<XmglModel> findAll(@Param("model") XmglModel model) throws Exception;

    @Select({
            "select * from" + table + " where uuid = #{id}"
    })
    @ResultMap(value = "xmglMap")
    XmglModel getById(@Param("id") String id) throws Exception;

}
