package com.yts.ytsoa.business.xmgl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yts.ytsoa.business.xmgl.model.interfaces.XmglApply;
import com.yts.ytsoa.business.xmgl.model.interfaces.XmglCj;
import com.yts.ytsoa.business.xmgl.model.interfaces.XmglUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class XmglModel implements Serializable {

    private String uuid;

    /*项目名称*/
    @NotBlank(message = "项目名称不能为空", groups = {XmglApply.class, XmglUpdate.class})
    @Length(max = 201, message = "项目名称最大长度为200位", groups = {XmglApply.class, XmglUpdate.class})
    @Null(message = "不允许修改项目名称", groups = {XmglCj.class})
    private String xmmc;

    /*委托方*/
    @NotBlank(message = "委托方不能为空", groups = {XmglApply.class, XmglUpdate.class})
    @Length(max = 201, message = "委托方最大长度为200位", groups = {XmglApply.class, XmglUpdate.class})
    @Null(message = "不允许修改委托方", groups = {XmglCj.class})
    private String wtf;

    /*委托时间*/
    @NotNull(message = "委托时间不能为空", groups = {XmglApply.class, XmglUpdate.class})
    @Null(message = "不允许修改委托时间", groups = {XmglCj.class})
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date wtsj;
    /*被审计单位*/
    @NotBlank(message = "被审计单位不能为空", groups = {XmglApply.class, XmglUpdate.class})
    @Length(max = 201, message = "被审计单位最大长度为200位", groups = {XmglApply.class, XmglUpdate.class})
    @Null(message = "不允许修改被审计单位", groups = {XmglCj.class})
    private String bsjdw;
    /*风险评估*/
    @NotBlank(message = "风险评估不能为空", groups = {XmglApply.class, XmglUpdate.class})
    @Length(min = 1, max = 1, message = "风险评估最大长度为1位", groups = {XmglApply.class, XmglUpdate.class})
    @Null(message = "不允许修改风险评估", groups = {XmglCj.class})
    private String fxpg;
    /*业务类型*/
    @NotBlank(message = "业务类型不能为空", groups = {XmglApply.class, XmglUpdate.class})
    @Length(max = 201, message = "业务类型最大长度为200位", groups = {XmglApply.class, XmglUpdate.class})
    @Null(message = "不允许修改业务类型", groups = {XmglCj.class})
    private String ywlx;
    /*项目分类*/
    @NotBlank(message = "项目分类不能为空", groups = {XmglApply.class, XmglUpdate.class})
    @Length(max = 201, message = "项目分类最大长度为200位", groups = {XmglApply.class, XmglUpdate.class})
    @Null(message = "不允许修改项目分类", groups = {XmglCj.class})
    private String xmfl;
    /*承接部门*/
    @NotBlank(message = "承接部门不能为空", groups = {XmglApply.class, XmglUpdate.class})
    @Length(max = 201, message = "承接部门最大长度为200位", groups = {XmglApply.class, XmglUpdate.class})
    @Null(message = "不允许修改承接部门", groups = {XmglCj.class})
    private String cjbm;
    /*项目开始时间*/
    @NotNull(message = "项目开始时间不能为空", groups = {XmglApply.class, XmglUpdate.class})
    @Null(message = "不允许修改项目开始时间", groups = {XmglCj.class})
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date xmkssj;
    /*项目结束时间*/
    @NotNull(message = "项目结束时间不能为空", groups = {XmglApply.class, XmglUpdate.class})
    @Null(message = "不允许修改项目结束时间", groups = {XmglCj.class})
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date xmjssj;
    /*预计收费*/
    @NotNull(message = "预计收费不能为空", groups = {XmglApply.class, XmglUpdate.class})
    @Min(value = 0, message = "预计收费最小值为0", groups = {XmglApply.class, XmglUpdate.class})
    @Max(value = 999999999, message = "预计收费最大值为999999999", groups = {XmglApply.class, XmglUpdate.class})
    @Null(message = "不允许修改预计收费", groups = {XmglCj.class})
    private Integer yjsf;
    /*
    项目标记
    0：创建委派但未承接
    1：已承接
    2：已完成但未确认
    3：已确认完成
    */
//    @NotNull(message = "项目标记不能为空", groups = {XmglApply.class, XmglUpdate.class})
//    @Min(value = 0, message = "项目标记最小值为0", groups = {XmglApply.class})
//    @Max(value = 0, message = "项目标记最大值为0", groups = {XmglApply.class})
//    @Null(message = "不允许修改项目标记", groups = {XmglCj.class})
    private Integer xmFlag;
    /*合同标记*/
//    @NotNull(message = "合同标记不能为空", groups = {XmglApply.class})
//    @AssertFalse(message = "合同标记默认为false", groups = {XmglApply.class})
//    @Null(message = "不允许修改合同标记", groups = {XmglCj.class})
    private Integer htFlag;
    /*报告标记*/
//    @NotNull(message = "报告标记不能为空", groups = {XmglApply.class})
//    @AssertFalse(message = "报告标记默认为false", groups = {XmglApply.class})
//    @Null(message = "不允许修改报告标记", groups = {XmglCj.class})
    private Integer bgFlag;
    /*尾款标记*/
//    @NotNull(message = "尾款标记不能为空", groups = {XmglApply.class})
//    @AssertFalse(message = "尾款标记默认为false", groups = {XmglApply.class})
//    @Null(message = "不允许修改尾款标记", groups = {XmglCj.class})
    private Integer wkFlag;
    /*归档标记*/
//    @NotNull(message = "归档标记不能为空", groups = {XmglApply.class})
//    @AssertFalse(message = "归档标记默认为false", groups = {XmglApply.class})
//    @Null(message = "不允许修改归档标记", groups = {XmglCj.class})
    private Integer gdFlag;
    /*承接人*/
    @NotBlank(message = "承接人不能为空", groups = {XmglApply.class, XmglUpdate.class})
    @Length(max = 201, message = "承接人最大长度为200位", groups = {XmglApply.class, XmglUpdate.class})
    @Null(message = "不允许修改承接人", groups = {XmglCj.class})
    private String cjr;
    /*项目承接时间*/
    @NotNull(message = "项目承接时间不能为空", groups = {XmglCj.class})
    @Null(message = "不允许修改项目结束时间", groups = {XmglApply.class})
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date xmcjsj;
    /*确认完成审核人*/
    @Null(message = "不允许修改预计收费", groups = {XmglApply.class, XmglCj.class})
    private String qrwcshr;
}
