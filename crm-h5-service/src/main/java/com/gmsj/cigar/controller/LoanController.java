package com.gmsj.cigar.controller;

import com.gmsj.util.SessionUtil;
import com.gmsj.cigar.clients.LoanClient;
import com.gmsj.core.business.model.RestResp;
import com.gmsj.core.business.vo.cigar.LoanApplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ovrille
 * @date 2017/11/13
 */
@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanClient loanClient;

    @PostMapping("/apply")
    public RestResp apply(@RequestBody @Validated LoanApplyVO loanApplyVO) {
        loanApplyVO.setUserId(SessionUtil.getCurrUid());
        return loanClient.apply(loanApplyVO);
    }
}
