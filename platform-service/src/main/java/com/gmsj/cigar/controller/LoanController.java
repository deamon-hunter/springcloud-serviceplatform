package com.gmsj.cigar.controller;

import com.google.common.collect.ImmutableMap;
import com.gmsj.cigar.service.LoanService;
import com.gmsj.core.business.vo.cigar.LoanApplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 申请贷款
 *
 * @author Ovrille
 * @date 2017/06/21
 */
@RequestMapping("/loan")
@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;


    @PostMapping("/apply")
    public Map<String,Object> applyLoan(@RequestBody @Validated LoanApplyVO loanApplyVO) throws Exception {

        Long id = loanService.applyLoan(loanApplyVO);

        return ImmutableMap.of("id", id);
    }


}
