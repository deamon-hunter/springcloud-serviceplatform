package com.gmsj.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@ConditionalOnProperty(prefix = "gmsj-platform", name = "spring-session-open", havingValue = "true")
public class SpringSessionConfig {

}
