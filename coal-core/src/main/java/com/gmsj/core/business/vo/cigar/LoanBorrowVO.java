package com.gmsj.core.business.vo.cigar;

import com.gmsj.core.business.vo.user.AuthRealNameVO;
import com.gmsj.core.business.vo.user.AuthStoreVO;
import com.gmsj.core.business.model.TablePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoanBorrowVO extends TablePage {

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
     * 借款商户
     */
    private String storeName;

    /**
     * 借款人
     */
    private String userName;

    /**
     * 借款电话
     */
    private String userPhone;

    /**
     * 审批金额
     */
    private BigDecimal approveAmount;

    /**
     * 借款金额
     */
    private BigDecimal borrowAmount;

    /**
     * 借款期数
     */
    private Integer borrowPeriod;

    /**
     * 审批人id
     */
    private Long employeeId;

    /**
     * 审批时间
     */
    private Date approveTime;

    /**
     * 审批意见
     */
    private String approveConclusion;

    /**
     * 贷款类型（0：整借整还，1：分期还款）
     */
    private Integer borrowType;


    /**
     * 审批状态
     */
    private Integer approveState;

    /**
     * 审批期数
     */
    private Integer approvePeriod;

    /**
     * 服务费
     */
    private BigDecimal serviceCharge;

    /**
     * 审批利率
     */
    private BigDecimal approveRate;

    private Date createTime;

    private Date updateTime;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 还款分期数
     */
    private Integer returnCount;

    /**
     * 每期还款本息
     */
    private BigDecimal approveInterest;

    /**
     * 还款截止日
     */
    private Date repayExpireDate;

    /**
     * 还款开始日
     */
    private Date repayStartDate;

    /**
     * 还款状态（0：未还款 1：还款中 2：已还款）
     */
    private Integer repayStatus;

    /**
     * 利率
     */
    private BigDecimal rate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 本金及利息
     */
    private BigDecimal totalRepay;


    // ------------------------

    private AuthStoreVO authStore;

    private AuthRealNameVO authRealName;


    // query

    private String createTimeEnd;
    private String createTimeStart;


    private String employeeName;
    private String company;

}
