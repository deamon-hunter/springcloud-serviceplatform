package com.gmsj.core.business.vo.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gmsj.core.business.group.bank.Bind;
import com.gmsj.core.business.group.bank.Unbind;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class BankCardVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 银行卡数据ID
     */
    @NotNull(message = "ID不能为空", groups = {Unbind.class})
    private Long id;

    /**
     * 银行卡关联的用户ID
     */
    private Long userId;

    /**
     * 银行ID
     */
    @NotNull(message = "银行ID不能为空", groups = {Unbind.class, Bind.class})
    private Long bankId;

    /**
     * 银行预留电话
     */
    @Pattern(message = "手机号码不符合规范", regexp = "^1[34578]\\d{9}$", groups = {Bind.class})
    @NotNull(message = "预留电话不能为空", groups = {Bind.class})
    private String phone;

    /**
     * 银行卡号
     */
    @NotNull(message = "预留电话不能为空", groups = {Bind.class})
    private String cardNumber;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行分支
     */
    private String cardName;

    /**
     * 卡号类型
     */
    private String cardType;

    /**
     * 银行背景水印
     */
    private String imgUrl;

    /**
     * 银行卡状态
     */
    @JsonIgnore
    private Boolean cardState;

    @JsonIgnore
    public Long getUserId() {
        return userId;
    }

    @JsonProperty
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}