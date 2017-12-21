package com.gmsj.user.clients;

import com.gmsj.core.business.model.RestResp;
import com.gmsj.core.business.vo.user.UserVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * @author Ovrille
 * @date 2017/10/17
 */
@FeignClient("core-service")
public interface IndexClient {

    @PostMapping("/verify-code")
    RestResp<Map> generationVerifyCode(UserVO userVO);


    @PostMapping("/register")
    RestResp registionUser(UserVO userVO);

    @PostMapping("/forget-pwd")
    RestResp forgetPassword(UserVO userVO);

    @PostMapping("/pwd-new")
    RestResp setNewPassword(UserVO userVO);
}
