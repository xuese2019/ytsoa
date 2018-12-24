package com.yts.ytsoa.business.account.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminModel implements Serializable {

    private String uuid;
    //    账户
    @NotBlank(message = "账户不能为空")
    @Size(max = 200, message = "账户最大长度为200位")
    private String account;
    //    密码
    @NotBlank(message = "密码不能为空")
    @Size(max = 200, message = "密码最大长度为200位")
    private String password;
    //    是否首次登录-更改为是否允许登录 N:不允许 Y：允许
    private String isLogin;

}
