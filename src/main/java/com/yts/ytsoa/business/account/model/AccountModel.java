package com.yts.ytsoa.business.account.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * lombok常用注解整理
 *
 * @Data 注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
 * @Setter ：注解在属性上；为属性提供 setting 方法
 * @Setter ：注解在属性上；为属性提供 getting 方法
 * @Log4j ：注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
 * @NoArgsConstructor ：注解在类上；为类提供一个无参的构造方法
 * @AllArgsConstructor ：注解在类上；为类提供一个全参的构造方法
 * @Cleanup : 可以关闭流
 * @Builder ： 被注解的类加个构造者模式
 * @Synchronized ： 加个同步锁
 * @SneakyThrows : 等同于try/catch 捕获异常
 * @NonNull : 如果给参数加个这个注解 参数为null会抛出空指针异常
 * @Value : 注解和@Data类似，区别在于它会把所有成员变量默认定义为private final修饰，并且不会生成set方法。
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountModel implements Serializable {

    private String uuid;
    /*姓名*/
    private String name;
    /*账户*/
    @NotBlank(message = "账户不能为空")
    @Size(max = 200, message = "账户最大长度为200位")
    private String account;
    /*密码*/
    @NotBlank(message = "密码不能为空")
    @Size(max = 200, message = "密码最大长度为200位")
    private String password;
    //    账户类型( -1:管理员 1：普通用户)
    private int lx = 1;
    //    所属部门
    private String bmid;
    //    是否首次登录-更改为是否允许登录 N:不允许 Y：允许
    private String isLogin;
    //    账户生成系统时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp systimes;
    //    入职日期
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date rzrq;
    //    账户生成人
    private String creatorAccId;
    //    性别
    private String sex;
    //    电话
    @Length(min = 11, max = 11, message = "电话长度为11位")
    private String phone;

}
