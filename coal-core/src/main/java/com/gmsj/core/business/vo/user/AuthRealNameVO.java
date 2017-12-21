package com.gmsj.core.business.vo.user;

import com.gmsj.core.business.group.auth.RealName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Data
public class AuthRealNameVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 实名ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;


    /**
     * 认证时间
     */
    private Date authTime;

    /**
     * 身份证号码
     */
    @NotNull(message = "身份证号码不能为空", groups = {RealName.class})
    @Pattern(message = "身份证不合法", regexp = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)")
    private Long idCardNumber;

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空", groups = {RealName.class})
    private String name;


    private String idCardFront;

    private String idCardBack;


//    // 照片合法性检查结果
//    private AuthRealNameLegalityVO legality;
//
//    public void setLegality(AuthRealNameLegalityVO legality) {
//        BeanUtils.copyProperties(legality, this);
//    }
}