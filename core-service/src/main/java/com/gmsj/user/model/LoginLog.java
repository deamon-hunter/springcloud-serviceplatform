package com.gmsj.user.model;

import com.gmsj.core.business.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "tbl_log_login")
public class LoginLog extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 登录IP地址
     */
    private String ip;

    /**
     * 登录用户
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 登录地址
     */
    @Column(name = "login_address")
    private String loginAddress;

    /**
     * 登录终端
     */
    private String terminal;

    /**
     * 设备ID
     */
    private String deviceId;
}