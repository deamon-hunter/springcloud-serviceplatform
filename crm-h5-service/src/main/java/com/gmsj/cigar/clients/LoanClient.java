package com.gmsj.cigar.clients;

import com.gmsj.core.business.model.RestResp;
import com.gmsj.core.business.vo.cigar.LoanApplyVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Ovrille
 * @date 2017/11/13
 */
@FeignClient("core-service")
public interface LoanClient {


    @PostMapping("/loan/apply")
    RestResp apply(LoanApplyVO loanApplyVO);

}
