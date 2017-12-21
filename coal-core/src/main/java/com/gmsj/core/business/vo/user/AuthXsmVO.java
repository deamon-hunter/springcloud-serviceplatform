package com.gmsj.core.business.vo.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Ovrille
 * @date 2017/10/19
 */
@Data
public class AuthXsmVO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 新商盟账号
     */
    @NotNull(message = "账号不能为空")
    private String xsmAccount;

    /**
     * 新商盟密码
     */
    @NotNull(message = "密码不能为空")
    private String xsmPassword;


}
