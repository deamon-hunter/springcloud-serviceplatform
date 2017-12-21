package com.gmsj.user.model;

import com.gmsj.core.business.model.BaseModel;
import com.gmsj.core.business.vo.user.BankCardVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "tbl_bank_card")
public class BankCard extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 银行卡关联的用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 银行ID
     */
    @Column(name = "bank_id")
    private Long bankId;

    /**
     * 银行预留电话
     */
    private String phone;

    /**
     * 银行卡号
     */
    @Column(name = "card_number")
    private String cardNumber;

    /**
     * 银行卡状态
     */
    @Column(name = "card_state")
    private Boolean cardState;

    public BankCard() {

    }

    public BankCard(BankCardVO bankCardVO) {
        BeanUtils.copyProperties(bankCardVO, this);
    }
}