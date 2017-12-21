package com.gmsj.cigar.service.impl;

import com.gmsj.cigar.mapper.LoanBorrowMapper;
import com.gmsj.cigar.model.LoanBorrow;
import com.gmsj.cigar.service.LoanService;
import com.gmsj.user.model.AuthStore;
import com.gmsj.user.service.AuthService;
import com.gmsj.xsm.service.CustomerInfoService;
import com.gmsj.core.business.constant.BorrowStatus;
import com.gmsj.core.business.constant.RedisKeyConstants;
import com.gmsj.core.business.exception.BizLoanErrorCode;
import com.gmsj.core.business.exception.BusinessException;
import com.gmsj.core.util.id.IdUtil;
import com.gmsj.core.business.vo.cigar.LoanApplyVO;
import com.gmsj.xsm.model.XsmCustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Ovrille
 * @date 2017/11/13
 */
@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private LoanBorrowMapper loanBorrowMapper;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private AuthService authService;

    @Override
    public Long applyLoan(LoanApplyVO loanApplyVO) {


        LoanBorrow loanBorrow = new LoanBorrow();
        loanBorrow.setId(IdUtil.generateId());
        loanBorrow.setUserId(loanApplyVO.getUserId());
        loanBorrow.setBorrowAmount(loanApplyVO.getBorrowAmount());
        loanBorrow.setBorrowPeriod(loanApplyVO.getBorrowPeriod());
        // 状态为正在审批 TODO enum
        loanBorrow.setApproveState(BorrowStatus.BORROW_COMMITED.ordinal());
        loanBorrow.setBorrowType(loanApplyVO.getBorrowType());
        loanBorrow.setBorrowNumber(generateBorrowNumber());

        // TODO: 2017/11/20 首次借款需要验证银行帐号和用户姓名
        checkBankAccount(loanApplyVO);


        loanBorrowMapper.insertSelective(loanBorrow);

        return loanBorrow.getId();
    }

    private void checkBankAccount(LoanApplyVO loanApplyVO) {
        AuthStore authStore = authService.getStoreInfo(loanApplyVO.getUserId());

        XsmCustomerInfo customerInfo = customerInfoService.getCustomerInfo(authStore.getXsmAccount());

        if (!loanApplyVO.getClientBankAccount().equals(customerInfo.getBankAccount())) {
            throw new BusinessException(BizLoanErrorCode.APPLY_LOAN_BANK_ERROR);
        }

        if (!loanApplyVO.getClientName().equals(customerInfo.getManager())) {
            throw new BusinessException(BizLoanErrorCode.APPLY_LOAN_CLIENT_ERROR);
        }
    }

    private String generateBorrowNumber() {
        //设置借款申请编号，使用redis的自增去获得是当天第多少笔借款，数据库查询不能保证并发的情况重复
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String borrowSequenceKey = String.format(RedisKeyConstants.LOAN_APPLY_DAY_SEQUENCE, "" + day);
        Long borrowSequence = redisTemplate.opsForValue().increment(borrowSequenceKey, 1L);
        redisTemplate.expire(borrowSequenceKey, 1L, TimeUnit.DAYS);
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        String borrowNumberPrefix = format.format(new Date());

        return borrowNumberPrefix  + getFixedlenStr("" + borrowSequence, 4);
    }

    private String getFixedlenStr(String source, Integer len) {
        if (source == null || len < 0) {
            return "";
        }
        if (source.length() > len) {
            return source;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len - source.length(); i++) {
            builder.append("0");
        }

        return builder.append(source).toString();
    }
}
