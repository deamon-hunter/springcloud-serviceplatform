package com.gmsj.user.mapper;

import com.gmsj.core.lib.MyBaseMapper;
import com.gmsj.user.model.BankCard;
import com.gmsj.core.business.vo.user.BankCardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 我的银行卡DAO接口
 *
 * @author hongQiang tang
 * @version $Id: BankCardMapper.java, v 0.1 2017年6月30日 上午9:53:14 Administrator Exp $
 */
@Mapper
public interface BankCardMapper extends MyBaseMapper<BankCard> {

    /**
     * 查询我绑定的所有银行卡列表
     *
     * @param userId 用户ID
     * @return
     */
    List<BankCardVO> selectBankCardList(Long userId);
}