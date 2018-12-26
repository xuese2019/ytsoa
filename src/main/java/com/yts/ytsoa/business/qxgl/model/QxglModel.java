package com.yts.ytsoa.business.qxgl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QxglModel implements Serializable {

    private String uuid;
    /*权限名称*/
    private String qxmc;
    /*权限标识*/
    private String qxbs;
    /*权限父级*/
    private String qxfj;
    /*权限类型 1:菜单 2:内部按钮*/
    private String qxlx;

    private List<QxglModel> list = new ArrayList<>();

}
