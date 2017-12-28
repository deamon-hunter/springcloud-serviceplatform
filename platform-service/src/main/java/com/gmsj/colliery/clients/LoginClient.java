package com.gmsj.colliery.clients;

import com.gmsj.core.business.command.base.LoginCommand;
import com.gmsj.core.business.model.RestResp;
import com.gmsj.core.business.vo.user.LoginTokenDataVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Ovrille
 * @date 2017/12/27
 */
@FeignClient("core-service")
public interface LoginClient {

    @PostMapping("/user/authenticate")
    RestResp<LoginTokenDataVO> loginIn(LoginCommand loginCommand);

}
