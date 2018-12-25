package com.yts.ytsoa.business.zzjg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
public class ZzjgModel implements Serializable {

    private String uuid;

    @NotBlank(message = "组织机构父级不能为空")
    @Size(max = 200, message = "组织机构父级最大长度为200位")
    private String zzjgfj;

    @NotBlank(message = "组织机构名称不能为空")
    @Size(max = 200, message = "组织机构名称最大长度为200位")
    private String zzjgmc;

    private List<ZzjgModel> list = new ArrayList<>();

}
