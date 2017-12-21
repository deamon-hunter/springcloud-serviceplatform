package com.gmsj.user.clients;

import com.gmsj.core.business.model.RestResp;
import com.gmsj.core.business.vo.user.AuthRealNameVO;
import com.gmsj.core.business.vo.user.AuthStoreVO;
import com.gmsj.core.business.vo.user.AuthXsmVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Ovrille
 * @date 2017/10/18
 */
@FeignClient("core-service")
public interface AuthClient {
    @PostMapping("/auth/realName")
    RestResp saveRealNameInfo(AuthRealNameVO realNamesVo);

    @PostMapping("/auth/store")
    RestResp saveStoreInfo(AuthStoreVO authStoreVO);

    @PostMapping("/auth/xsm")
    RestResp saveXsmInfo(AuthXsmVO authXsmVO);

    @GetMapping("/auth/info/{userId}")
    RestResp getAuthInfo(@PathVariable(value = "userId") Long userId);
}
