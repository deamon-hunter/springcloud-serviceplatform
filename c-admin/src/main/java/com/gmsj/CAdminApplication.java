package com.gmsj;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@SpringCloudApplication
@EnableFeignClients
public class CAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(CAdminApplication.class, args);
    }

}
