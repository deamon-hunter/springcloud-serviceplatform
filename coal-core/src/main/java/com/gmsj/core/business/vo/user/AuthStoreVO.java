package com.gmsj.core.business.vo.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Ovrille
 * @date 2017/10/19
 */
@Data
public class AuthStoreVO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 城市ID
     */
    @NotNull(message = "城市不能为空")
    private String storeCity;

    @NotNull(message = "区域不能为空")
    private String storeArea;
    /**
     * 商户名称
     */
    @NotNull(message = "商户名称不能为空")
    private String storeName;

    /**
     * 商户地址
     */
    @NotNull(message = "商户地址不能为空")
    private String storeAddress;


    /**
     * 烟草执照号
     */
    @NotNull(message = "烟草许可证号不能为空")
    private String cigarNumber;


    /**
     * 烟草证件
     */
    @NotNull(message = "请上传烟草许可证")
    private String cigarNumberImg;


    /**
     * 订烟频率
     */
    @NotNull(message = "请选择订烟次次数")
    private Integer frequency;

    /**
     * 周几
     */
    @NotNull(message = "请选择订烟周期")
    private String dayOfWeek;

}
