package com.gmsj.user.controller;

import com.gmsj.core.business.group.bank.Bind;
import com.gmsj.core.business.group.bank.Unbind;
import com.gmsj.user.service.BankService;
import com.gmsj.core.business.vo.user.BankCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 银行卡绑定/解绑操作
 *
 * @author hongQiang tang
 * @version $Id: BankController.java, v 0.1 2017年6月27日 下午2:58:33 Administrator Exp $
 */
@RequestMapping("/user/bank")
@RestController
public class BankController {

    @Autowired
    BankService bankService;

    @GetMapping("/{userId}/list")
    public List<BankCardVO> selectAllBankList(@PathVariable(value = "userId") Long userId) {
        return bankService.selectAllBankCard(userId);
    }

    @RequestMapping(value = "/bind", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void bankCardBind(@RequestBody @Validated(Bind.class) BankCardVO bankCardVo) {
        bankService.bankBind(bankCardVo);
    }

    @RequestMapping(value = "/unbind", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void bankCardUnbind(@RequestBody @Validated(Unbind.class) BankCardVO bankCardVo) {
        bankService.bankUnbind(bankCardVo);
    }
}
