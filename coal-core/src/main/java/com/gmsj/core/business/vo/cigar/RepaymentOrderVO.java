package com.gmsj.core.business.vo.cigar;

import com.gmsj.core.business.model.TablePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class RepaymentOrderVO extends TablePage {

    /**
     * ID
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 借款申请编号
     */
    private Long borrowId;

    /**
     * 订单金额
     */
    private BigDecimal amount;

    /**
     * 生成日期
     */
    private Date createTime;

    /**
     * 截止日期
     */
    private Date repayExpireDate;

    /**
     * 本金及利息
     */
    private BigDecimal totalRepay;

    /**
     * 服务费
     */
    private BigDecimal serviceCharge;

    /**
     * 加层费
     */
    private BigDecimal addAmount;

    /**
     * 还款状态
     */
    private Integer repayStatus;


    /**
     * 还款时间
     */
    private Date repayStartDate;


}
