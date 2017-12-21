package com.gmsj.user.model;

import com.gmsj.core.business.model.BaseModel;
import com.gmsj.core.util.id.IdUtil;
import com.gmsj.core.business.vo.user.AuthRealNameVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "tbl_auth_realname")
public class AuthRealName extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "user_id")
    private Long userId;


    /**
     * 认证时间
     */
    @Column(name = "auth_time")
    private Date authTime;

    /**
     * 身份证号码
     */
    @Column(name = "id_card_number")
    private Long idCardNumber;

    /**
     * 姓名
     */
    private String name;


    /**
     *  正面图
     */
    private String idCardFront;

    /**
     * 背面图
     */
    private String idCardBack;



    public AuthRealName() {

    }

    public AuthRealName(AuthRealNameVO authRealNameVO) {
        BeanUtils.copyProperties(authRealNameVO, this);
        setId(IdUtil.generateId());
    }
}