package com.yts.ytsoa.business.xmfl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class XmflModel implements Serializable {

    private String uuid;
    /*项目分类名称*/
    @NotBlank(message = "项目分类名称不能为空")
    @Size(max = 200, message = "项目分类名称最大长度为200位")
    private String xmflmc;

}
