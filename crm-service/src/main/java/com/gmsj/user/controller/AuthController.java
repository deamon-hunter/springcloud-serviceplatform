package com.gmsj.user.controller;

import com.gmsj.core.business.group.auth.RealName;
import com.gmsj.core.business.vo.user.AuthInfoVO;
import com.gmsj.core.business.vo.user.AuthRealNameVO;
import com.gmsj.core.business.vo.user.AuthStoreVO;
import com.gmsj.core.business.vo.user.AuthXsmVO;
import com.gmsj.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 认证接口
 */
@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 实名认证
     *
     * @param realNamesVo
     */
    @RequestMapping(value = "/realName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void saveRealNameInfo(@RequestBody @Validated(RealName.class) AuthRealNameVO realNamesVo) {
        authService.saveRealNameInfo(realNamesVo);
    }



    @PostMapping("/store")
    public void saveStoreInfo(@RequestBody @Validated AuthStoreVO authStoreVO) {
        authService.saveStoreInfo(authStoreVO);
    }

    @PostMapping("/xsm")
    public void saveXsmInfo(@RequestBody @Validated AuthXsmVO authXsmVO) {
        authService.saveXsmInfo(authXsmVO);
    }

    @GetMapping("/info/{userId}")
    public AuthInfoVO getAuthInfo(@PathVariable("userId") Long userId) {
        return authService.getAuthInfo(userId);
    }
}
