package com.yts.ytsoa.business.qxgl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZzQxModel implements Serializable {

    private String uuid;
    /*权限id*/
    private String qxid;
    /*组织id*/
    private String zzid;

}
