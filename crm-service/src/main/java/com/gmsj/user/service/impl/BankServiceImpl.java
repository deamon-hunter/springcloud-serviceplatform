package com.gmsj.user.service.impl;


import com.gmsj.core.util.id.IdUtil;
import com.gmsj.user.model.BankCard;
import com.gmsj.user.mapper.BankCardMapper;
import com.gmsj.user.service.BankService;
import com.gmsj.core.business.vo.user.BankCardVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BankServiceImpl implements BankService {

    @Autowired
    BankCardMapper bankCardMapper;

    @Override
    public void bankBind(BankCardVO bankCardVo) {

        // 创建查询条件
        Example example = new Example(BankCard.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cardNumber", bankCardVo.getCardNumber());

        // 判断是否有被解绑的银行卡
        // 如果有被解绑的银行卡并且当次绑定的银行卡是之前已经被解绑的数据
        // 那么直接把上次已经解绑的银行卡还原回来
        List<BankCard> exists = bankCardMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(exists)) {

            Assert.isTrue(!exists.get(0).getCardState(), "该卡号已经被绑定");

            BankCard bankCard = new BankCard();
            bankCard.setCardState(true);
            bankCard.setPhone(bankCardVo.getPhone());
            bankCard.setUpdateTime(new Date());
            bankCardMapper.updateByExampleSelective(bankCard, example);
            return;
        }

        bankCardVo.setCardState(true);
        bankCardVo.setId(IdUtil.generateId());
        bankCardMapper.insertSelective(new BankCard(bankCardVo));
    }

    @Override
    public void bankUnbind(BankCardVO bankCardVo) {

        Example example = new Example(BankCard.class);
        example.createCriteria()
                .andEqualTo("id", bankCardVo.getId())
                .andEqualTo("bankId", bankCardVo.getBankId())
                .andEqualTo("userId", bankCardVo.getUserId());

        bankCardVo.setCardState(false);

        bankCardMapper.updateByExampleSelective(new BankCard(bankCardVo), example);
    }

    @Override
    public List<BankCardVO> selectAllBankCard(Long userId) {
        return bankCardMapper.selectBankCardList(userId);
    }
}
