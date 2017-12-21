package com.gmsj.user.model;

import com.gmsj.core.business.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "tbl_auth_info")
public class AuthInfo extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 是否实名认证
     */
    @Column(name = "is_autoaym")
    private Boolean isAutoaym;



    @Column(name = "is_store")
    private Boolean isStore;


    @Column(name = "is_xsm")
    private Boolean isXsm;

}