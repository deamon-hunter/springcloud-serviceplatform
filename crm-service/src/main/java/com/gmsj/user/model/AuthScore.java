package com.gmsj.user.model;

import com.gmsj.core.business.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "tbl_auth_score")
public class AuthScore extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 实名认证分
     */
    @Column(name = "autoaym_score")
    private Integer autoaymScore;

    /**
     * 商户认证得分
     */
    private Integer storeScore;


    /**
     * 芝麻认证分
     */
    @Column(name = "zmxy_score")
    private Integer zmxyScore;

}