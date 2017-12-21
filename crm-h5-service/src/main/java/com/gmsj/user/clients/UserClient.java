package com.gmsj.user.clients;

import com.gmsj.core.business.model.RestResp;
import com.gmsj.core.business.vo.user.UserVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Ovrille
 * @date 2017/10/26
 */
@FeignClient("core-service")
public interface UserClient {


    @GetMapping("/user/info/{openId}")
    RestResp getUserInfoByOpenId(@PathVariable(value = "openId") String openId);


    /**
     * 设置密码
     * @param userVO
     * @return
     */
    @PostMapping("/user/pass")
    RestResp setNewPassword(UserVO userVO);
}
