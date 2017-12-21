package com.gmsj.core.business.vo.user;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Ovrille
 * @date 2017/11/09
 */
@Data
public class AuthInfoVO {

    /**
     * 是否实名认证
     */
    private Boolean isAutoaym;

    /**
     * 是否新商盟认证
     */
    private Boolean isXsm;

    private Boolean isStore;

    private Boolean isAssess;

    private BigDecimal creditLine;


}
