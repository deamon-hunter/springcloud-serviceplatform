package com.gmsj.cigar.controller;

import com.gmsj.cigar.service.LoanBorrowService;
import com.gmsj.core.business.model.BootstrapTable;
import com.gmsj.core.business.vo.cigar.LoanBorrowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 借款申请
 *
 * @author jensen
 * Created by Administrator on 2017/8/2.
 */
@RestController
@RequestMapping("/loanBorrow")
public class LoanBorrowController {

    @Autowired
    private LoanBorrowService loanBorrowService;



    // -----------------------------------------------



    /**
     * 查询借款申请列表
     *
     * @return
     */
    @PostMapping("/selectBorrowList")
    public BootstrapTable<LoanBorrowVO> selectLoanBorrowList(@RequestBody LoanBorrowVO loanBorrowVO) {
        BootstrapTable<LoanBorrowVO> loanBorrowVOBootstrapTable = loanBorrowService.selectLoanBorrowList(loanBorrowVO);
        return loanBorrowVOBootstrapTable;
    }

    @GetMapping("/{id}")
    public LoanBorrowVO getById(@PathVariable("id") Long id) {
        return loanBorrowService.getById(id);
    }


    /**
     * 通过申请
     *
     * @param loanBorrowVO
     * @return
     */
    @RequestMapping(value = "/borrowPass", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Integer borrowPass(@RequestBody LoanBorrowVO loanBorrowVO) {
        return loanBorrowService.borrowPass(loanBorrowVO);
    }

    /**
     * 拒绝申请
     *
     * @param loanBorrowVO
     * @return
     */
    @RequestMapping(value = "/borrowRefuse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Integer borrowRefuse(@RequestBody LoanBorrowVO loanBorrowVO) {
        return loanBorrowService.borrowRefuse(loanBorrowVO);
    }

    /**
     * 查询贷款列表
     */
    @PostMapping("/selectLoanList")
    public BootstrapTable<LoanBorrowVO> selectLoanList(@RequestBody LoanBorrowVO loanBorrowVO) {
        return loanBorrowService.selectLoanList(loanBorrowVO);
    }

}
