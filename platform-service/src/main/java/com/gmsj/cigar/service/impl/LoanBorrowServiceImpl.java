package com.gmsj.cigar.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gmsj.cigar.mapper.LoanBorrowMapper;
import com.gmsj.cigar.service.LoanBorrowService;
import com.gmsj.user.model.AuthRealName;
import com.gmsj.user.service.AuthService;
import com.gmsj.cigar.model.LoanBorrow;

import com.gmsj.core.business.constant.BorrowStatus;
import com.gmsj.core.business.model.BootstrapTable;
import com.gmsj.core.business.vo.cigar.LoanBorrowVO;
import com.gmsj.core.business.vo.user.AuthRealNameVO;
import com.gmsj.core.business.vo.user.AuthStoreVO;
import com.gmsj.core.util.BeanUtil;
import com.gmsj.user.model.AuthStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 借款申请service实现类
 * Created by Administrator on 2017/8/4.
 */
@Service
@Transactional
@Slf4j
public class LoanBorrowServiceImpl implements LoanBorrowService {

    @Autowired
    private LoanBorrowMapper loanBorrowMapper;


    @Autowired
    private AuthService authService;

    @Override
    public BootstrapTable<LoanBorrowVO> selectLoanBorrowList(LoanBorrowVO loanBorrowVO) {

        PageHelper.offsetPage(loanBorrowVO.getOffset(), loanBorrowVO.getLimit());

        loanBorrowVO.setApproveState(0);

        List<LoanBorrowVO> loanBorrowVOList = loanBorrowMapper.selectLoanBorrowList(loanBorrowVO);



        return new BootstrapTable<>(loanBorrowVOList, ((Page) loanBorrowVOList).getTotal());
    }

    @Override
    public Integer borrowPass(LoanBorrowVO loanBorrowVO) {
        return updateBorrowByPassOrRefuse(loanBorrowVO, BorrowStatus.BORROW_APPROVE_SUCCESS.ordinal());
    }

    @Override
    public Integer borrowRefuse(LoanBorrowVO loanBorrowVO) {
        return updateBorrowByPassOrRefuse(loanBorrowVO, BorrowStatus.BORROW_ARTIFICIAL_NO_PASS.ordinal());
    }

    /**
     * 添加借款申请单
     *
     * @param loanBorrowVO
     */
    @Override
    public void addLoanBorrow(LoanBorrowVO loanBorrowVO) {
        LoanBorrow loanBorrow = new LoanBorrow(loanBorrowVO);
        loanBorrowMapper.insertSelective(loanBorrow);
    }


    @Override
    public LoanBorrowVO getById(Long id) {
        LoanBorrowVO loanBorrowVO =  loanBorrowMapper.selectVOById(id);

        AuthStore storeInfo = authService.getStoreInfo(loanBorrowVO.getUserId());

        AuthStoreVO authStoreVO = new AuthStoreVO();
        BeanUtil.copyProperties(storeInfo, authStoreVO);

        AuthRealName realNameInfo = authService.getRealNameInfo(loanBorrowVO.getUserId());
        AuthRealNameVO authRealNameVO = new AuthRealNameVO();
        BeanUtil.copyProperties(realNameInfo, authRealNameVO);

        loanBorrowVO.setAuthRealName(authRealNameVO);
        loanBorrowVO.setAuthStore(authStoreVO);

        return loanBorrowVO;
    }

    @Override
    public BootstrapTable<LoanBorrowVO> selectLoanList(LoanBorrowVO loanBorrowVO) {

        PageHelper.offsetPage(loanBorrowVO.getOffset(), loanBorrowVO.getLimit());

        loanBorrowVO.setStatus(BorrowStatus.BORROW_LENDING.ordinal());

        List<LoanBorrowVO> loanBorrowVOList = loanBorrowMapper.selectLoanBorrowList(loanBorrowVO);


        return new BootstrapTable<>(loanBorrowVOList, ((Page) loanBorrowVOList).getTotal());
    }


    /**
     * 通过或者拒绝更新申请订单
     *
     * @param loanBorrowVO
     * @param state
     * @return
     */
    private Integer updateBorrowByPassOrRefuse(LoanBorrowVO loanBorrowVO, Integer state) {

        LoanBorrow loanBorrow = new LoanBorrow();

        return loanBorrowMapper.updateByPrimaryKeySelective(loanBorrow);
    }

}
