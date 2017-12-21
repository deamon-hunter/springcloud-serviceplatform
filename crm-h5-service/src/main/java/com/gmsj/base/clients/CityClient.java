package com.gmsj.base.clients;

import com.gmsj.core.business.model.RestResp;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Ovrille
 * @date 2017/11/17
 */
@FeignClient("core-service")
public interface CityClient {

    @GetMapping("/city/all")
    RestResp getCities();
}
