package com.gmsj.core.business.vo.cigar;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Ovrille
 * @date 2017/11/13
 */
@Data
public class LoanApplyVO {

    private Long id;

    /**
     * 申请编号
     */
    private String borrowNumber;

    /**
     * 借款人id
     */
    private Long userId;

    /**
     * 借款金额
     */
    @NotNull(message = "借款金额不能为空")
    private BigDecimal borrowAmount;

    /**
     * 借款期数
     */
    @NotNull(message = "借款期数不能为空")
    private Integer borrowPeriod;

    /**
     * 贷款类型（0：整借整还，1：分期还款）
     */
    @NotNull(message = "贷款类型不能为空，贷款类型（0：整借整还，1：分期还款）")
    private Integer borrowType;
    /**
     * 审批状态
     */
    private Integer approveState;


    /**
     * 借款人姓名
     */
    private String clientName;

    /**
     * 借款人银行帐号
     */
    private String clientBankAccount;

}
