package com.gmsj.cigar.service;

import com.gmsj.core.business.vo.cigar.LoanApplyVO; /**
 * @author Ovrille
 * @date 2017/11/13
 */
public interface LoanService {
    /**
     * 申请借款
     * @param loanApplyVO 借款VO
     */
    Long applyLoan(LoanApplyVO loanApplyVO);
}
