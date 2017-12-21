package com.gmsj.user.service;

import com.gmsj.core.business.vo.user.BankCardVO;

import java.util.List;

/**
 * 银行卡操作
 *
 * @author hongQiang tang
 * @version $Id: BankService.java, v 0.1 2017年6月27日 下午3:28:21 Administrator Exp $
 */
public interface BankService {

    /**
     * 绑定我的银行卡
     *
     * @param bankCardVo 银行卡表单数据
     */
    void bankBind(BankCardVO bankCardVo);

    /**
     * 解绑我的银行卡
     *
     * @param bankCardVo 银行卡表单数据
     */
    void bankUnbind(BankCardVO bankCardVo);

    /**
     * 查询我绑定的所有银行卡列表
     *
     * @param userId 用户ID
     * @return
     */
    List<BankCardVO> selectAllBankCard(Long userId);
}
