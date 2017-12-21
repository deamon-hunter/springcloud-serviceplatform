package com.gmsj.cigar.service;

import com.gmsj.core.business.model.BootstrapTable;
import com.gmsj.core.business.vo.cigar.LoanBorrowVO;

/**
 * 借款申请service接口类
 * Created by Administrator on 2017/8/4.
 */
public interface LoanBorrowService {

    /**
     * 查询借款申请列表
     *
     * @param loanBorrowVO
     * @return
     */
    BootstrapTable<LoanBorrowVO> selectLoanBorrowList(LoanBorrowVO loanBorrowVO);

    /**
     * 通过申请
     *
     * @param loanBorrowVO
     * @return
     */
    Integer borrowPass(LoanBorrowVO loanBorrowVO);

    /**
     * 拒绝申请
     *
     * @param loanBorrowVO
     * @return
     */
    Integer borrowRefuse(LoanBorrowVO loanBorrowVO);

    /**
     * 添加借款申请单
     * @param loanBorrowVO
     */
    void addLoanBorrow(LoanBorrowVO loanBorrowVO);

    /**
     * 借款单详情
     * @param id 借款ID
     * @return
     */
    LoanBorrowVO getById(Long id);

    /**
     * 查询贷款列表
     */
    BootstrapTable<LoanBorrowVO> selectLoanList(LoanBorrowVO loanBorrowVO);
}
