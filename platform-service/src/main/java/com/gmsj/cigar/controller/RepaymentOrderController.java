package com.gmsj.cigar.controller;


import com.gmsj.cigar.service.RepaymentOrderService;
import com.gmsj.core.business.vo.cigar.RepaymentOrderVO;
import com.gmsj.core.business.model.DataTable;
import com.gmsj.core.business.model.RestResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 待支付Controller层
 *
 * @author tiandong
 */
@RestController
@RequestMapping("/repaymentorder")
public class RepaymentOrderController {

    @Autowired
    RepaymentOrderService repaymentOrderService;

    /**
     * 获取学费支付记录
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/getrepaymentorder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResp<DataTable<RepaymentOrderVO>> tables(@RequestBody @Validated RepaymentOrderVO repaymentOrderVO) {
        return RestResp.ok(repaymentOrderService.getRepaymentOrder(repaymentOrderVO));
    }

}
