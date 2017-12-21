package com.gmsj.cigar.service;

import com.gmsj.core.business.vo.cigar.RepaymentOrderVO;
import com.gmsj.core.business.model.DataTable;

/**
 * 还款订单接口
 *
 * @author tiandong
 * @version $Id: PayService.java, v 0.1 2017年7月13日 下午2:59:02 Administrator Exp $
 */
public interface RepaymentOrderService {

    /**
     * 还款订单信息获取
     *
     * @param unPaidRecordVO 还款订单信息
     */
    DataTable<RepaymentOrderVO> getRepaymentOrder(RepaymentOrderVO repaymentOrderVO);
}
