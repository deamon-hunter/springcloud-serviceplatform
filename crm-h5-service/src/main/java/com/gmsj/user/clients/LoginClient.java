package com.gmsj.user.clients;

import com.gmsj.core.business.model.RestResp;
import com.gmsj.core.business.vo.user.UserVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Ovrille
 * @date 2017/10/17
 */
@FeignClient("core-service")
public interface LoginClient {

    @PostMapping("/login/phone")
    RestResp<UserVO> loginByPhone(UserVO userVO);


    @PostMapping("/login/account")
    RestResp<UserVO> loginByAccount(UserVO userVO);
}
