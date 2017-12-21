package com.gmsj.user.model;

import com.gmsj.core.business.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * 芝麻信用授权认证实体
 *
 * @author hongQiang tang
 * @version $Id: AuthZmxy.java, v 0.1 2017年6月30日 上午9:22:49 Administrator Exp $
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "tbl_auth_zmxy")
public class AuthZmxy extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 芝麻信用授权ID
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 芝麻信用积分
     */
    @Column(name = "zm_score")
    private Integer zmScore;

    /**
     * 授权认证时间
     */
    @Column(name = "auth_time")
    private Date authTime;

    public AuthZmxy() {

    }

}