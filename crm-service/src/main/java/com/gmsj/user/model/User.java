package com.gmsj.user.model;

import com.gmsj.core.business.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "tbl_user")
public class User extends BaseModel {

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    private String password;


    /**
     * 可用额度
     */
    private BigDecimal amount;

    /**
     * 授信额度
     */
    @Column(name = "credit_line")
    private BigDecimal creditLine;

    /**
     * 是否有贷款权限（0：无权限  1：有权限）
     */
    @Column(name = "is_auth")
    private Boolean isAuth;

    /**
     * 是否评估（0：未评估 1：已评估）
     */
    @Column(name = "is_assess")
    private Boolean isAssess;

    /**
     * 账号状态（0：冻结  1：正常）
     */
    private Boolean status;

    /**
     * 综合评分
     */
    @Column(name = "score_integrated")
    private Long scoreIntegrated;

    /**
     * 资信等级
     */
    @Column(name = "credit_grade")
    private Short creditGrade;

    private String openId;
}