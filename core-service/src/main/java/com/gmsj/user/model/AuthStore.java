package com.gmsj.user.model;

import com.gmsj.core.util.BeanUtil;
import com.gmsj.core.util.id.IdUtil;
import com.gmsj.core.business.vo.user.AuthStoreVO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tbl_auth_store")
@Data
public class AuthStore {
    @Id
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 商店地址
     */
    @Column(name = "store_address")
    private String storeAddress;

    /**
     * 商店城市
     */
    @Column(name = "store_city")
    private String storeCity;

    private String storeArea;

    /**
     * 商店注册号
     */
    @Column(name = "cigar_number")
    private String cigarNumber;

    /**
     * 商户名称
     */
    @Column(name = "store_name")
    private String storeName;

    /**
     * 烟草VIP帐号
     */
    @Column(name = "cigar_vip_number")
    private String cigarVipNumber;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 烟草证图片
     */
    @Column(name = "cigar_number_img")
    private String cigarNumberImg;


    /**
     * 新商盟账号
     */
    private String xsmAccount;

    /**
     * 新商盟密码
     */
    private String xsmPassword;

    /**
     * 订烟频率
     */
    private Integer frequency;

    /**
     * 周几
     */
    private String dayOfWeek;

    public AuthStore() {

    }

    public AuthStore(AuthStoreVO authStoreVO) {
        BeanUtil.copyProperties(authStoreVO, this);
        setId(IdUtil.generateId());
    }

}