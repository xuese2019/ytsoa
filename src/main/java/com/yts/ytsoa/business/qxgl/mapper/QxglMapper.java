package com.yts.ytsoa.business.qxgl.mapper;

import com.yts.ytsoa.business.qxgl.model.QxglModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface QxglMapper {

    String table = " qxgl_table ";

    @Select({
            "select * from " + table
    })
    List<QxglModel> findAll();

}
