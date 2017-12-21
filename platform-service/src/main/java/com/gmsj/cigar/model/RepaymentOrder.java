package com.gmsj.cigar.model;

import com.gmsj.core.business.vo.cigar.RepaymentOrderVO;
import com.gmsj.core.business.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "tbl_order")
public class RepaymentOrder extends BaseModel {

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

    public RepaymentOrder() {
    }

    public RepaymentOrder(RepaymentOrderVO repaymentOrderVO) {
        BeanUtils.copyProperties(repaymentOrderVO, this);
    }
}
