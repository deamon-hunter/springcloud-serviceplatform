package com.gmsj.core.business.vo.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gmsj.core.business.group.user.LoginByPass;
import com.gmsj.core.business.group.user.LoginByPhone;
import com.gmsj.core.business.group.user.Register;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long id;

    @NotNull(message = "openId不能为空", groups = {LoginByPhone.class, LoginByPass.class, Register.class})
    private String openId;

    /**
     * 手机号码
     */
    @Pattern(message = "手机号码不符合规范", regexp = "^1[34578]\\d{9}$", groups = {LoginByPhone.class, LoginByPass.class, Register.class})
    @NotNull(message = "手机号码不能为空", groups = {LoginByPhone.class, LoginByPass.class, Register.class})
    private String phone;

    /**
     * 短信验证码
     */
    @Pattern(message = "必须是6位数字", regexp = "^\\d{6}$", groups = {LoginByPhone.class, Register.class})
    @NotNull(message = "验证码不能为空", groups = {LoginByPhone.class, Register.class})
    private String verifyCode;


    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户名
     */
    @JsonIgnore
    private String userName;

    /**
     * 密码
     */
    @NotNull(message = "验证码不能为空", groups = {LoginByPass.class, Register.class})
    private String password;

    /**
     * 可用额度
     */
    private BigDecimal amount;

    /**
     * 授信额度
     */
    private BigDecimal creditLine;

    private Boolean isAuth = false;


    private AuthInfoVO authInfo;



    private Boolean isNewUser = false;

    /**
     * 账号状态
     */
    @JsonIgnore
    private Boolean status = false;

    /**
     * 综合评分
     */
    private Long scoreIntegrated;

    /**
     * 资信等级
     */
    private Short creditGrade;

    private String apiKey;


}
