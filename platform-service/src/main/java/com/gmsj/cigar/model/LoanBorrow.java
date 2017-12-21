package com.gmsj.cigar.model;

import com.gmsj.core.business.vo.cigar.LoanBorrowVO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "tbl_loan_borrow")
@Data
public class LoanBorrow {
    @Id
    private Long id;

    /**
     * 申请编号
     */
    @Column(name = "borrow_number")
    private String borrowNumber;

    /**
     * 借款人id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 审批金额
     */
    @Column(name = "approve_amount")
    private BigDecimal approveAmount;

    /**
     * 借款金额
     */
    @Column(name = "borrow_amount")
    private BigDecimal borrowAmount;

    /**
     * 借款期数
     */
    @Column(name = "borrow_period")
    private Integer borrowPeriod;

    /**
     * 审批人id
     */
    @Column(name = "employee_id")
    private Long employeeId;

    /**
     * 审批时间
     */
    @Column(name = "approve_time")
    private Date approveTime;

    /**
     * 审批意见
     */
    @Column(name = "approve_conclusion")
    private String approveConclusion;

    /**
     * 贷款类型（0：整借整还，1：分期还款）
     */
    @Column(name = "borrow_type")
    private Integer borrowType;

    /**
     * 审批状态
     */
    @Column(name = "approve_state")
    private Integer approveState;

    /**
     * 审批期数
     */
    @Column(name = "approve_period")
    private Integer approvePeriod;

    /**
     * 服务费
     */
    @Column(name = "service_charge")
    private BigDecimal serviceCharge;

    /**
     * 审批利率
     */
    @Column(name = "approve_rate")
    private BigDecimal approveRate;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;


    /**
     * 每期还款本息
     */
    @Column(name = "approve_interest")
    private BigDecimal approveInterest;

    /**
     * 还款截止日
     */
    @Column(name = "repay_expire_date")
    private Date repayExpireDate;

    /**
     * 还款开始日
     */
    @Column(name = "repay_start_date")
    private Date repayStartDate;

    /**
     * 还款状态（0：未还款 1：还款中 2：已还款）
     */
    @Column(name = "repay_status")
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
    private Boolean status;

    /**
     * 本金及利息
     */
    @Column(name = "total_repay")
    private BigDecimal totalRepay;


    public LoanBorrow() {

    }

    public LoanBorrow(LoanBorrowVO loanBorrowVO) {
        BeanUtils.copyProperties(loanBorrowVO, this);
    }
}