package com.gmsj.core.conf;

import com.gmsj.core.feign.FeignDecoder;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @author Ovrille
 * @date 2017/08/23
 */
//@Configuration
public class FeignClientConf {
    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    @Primary
    public Decoder decoder() {
        return new FeignDecoder(new SpringDecoder(messageConverters));
    }
}
