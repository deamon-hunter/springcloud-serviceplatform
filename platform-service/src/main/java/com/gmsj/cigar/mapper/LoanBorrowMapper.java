package com.gmsj.cigar.mapper;

import com.gmsj.cigar.model.LoanBorrow;
import com.gmsj.core.lib.MyBaseMapper;
import com.gmsj.core.business.vo.cigar.LoanBorrowVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoanBorrowMapper extends MyBaseMapper<LoanBorrow> {

    List<LoanBorrowVO> selectLoanBorrowList(LoanBorrowVO loanBorrowVO);

    LoanBorrowVO selectVOById(Long id);
}