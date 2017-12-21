package com.gmsj.core.lib;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Ovrille
 * @date 2017/11/15
 */
@Data
@ConfigurationProperties(prefix = "aliyun")
@Component
public class AliyunProperties {
    private String accessKeyId;
    private String accessKeySecret;


    private Oss oss;

    private Message message;

    @Data
    public static class Oss {
        private String endpoint;
        private String imgBucketName;

        private String imgDomain;


    }

    @Data
    public static class Message {
        private String verifyTemplate;
    }

}
