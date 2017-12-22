package com.gmsj.user.model;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "sys_user")
public class User {
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String userName;

    private String name;

    /**
     * 密码
     */
    private String password;

    @Column(name = "dept_id")
    private Integer deptId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态 0:禁用，1:正常
     */
    private Byte status;

    /**
     * 创建用户id
     */
    @Column(name = "user_id_create")
    private Long userIdCreate;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;

}