package com.gmsj;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@EnableFeignClients
@SpringCloudApplication
public class H5Application {

	public static void main( String [] args ) {
		SpringApplication.run( H5Application.class, args );
	}

}
