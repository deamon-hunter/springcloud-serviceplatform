package com.gmsj.user.mapper;

import com.gmsj.colliery.model.RepaymentOrder;
import com.gmsj.core.business.vo.cigar.RepaymentOrderVO;
import com.gmsj.core.lib.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 还款订单DAO接口
 *
 * @author tiandong
 * @version $Id: RepaymentOrderMapper.java, v 0.1 2017年6月30日 上午9:52:55 Administrator Exp $
 */
@Mapper
public interface RepaymentOrderMapper extends MyBaseMapper<RepaymentOrder> {

    /**
     * 查询还款订单信息
     *
     * @param repaymentOrderVO
     * @return
     */
    List<RepaymentOrderVO> getRepaymentOrderList(RepaymentOrderVO repaymentOrderVO);
}