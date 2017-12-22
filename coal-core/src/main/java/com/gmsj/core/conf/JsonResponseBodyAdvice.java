/*
 * Copyright (c) 2017 xiaomaihd and/or its affiliates.All Rights Reserved.
 *            http://www.xiaomaihd.com
 */
package com.gmsj.core.conf;

import com.gmsj.core.business.model.RestResp;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

/**
 * 统一处理所有返回值，对其进行封装
 *
 * @author Ovrille
 * @date 2017/03/04
 */
@ControllerAdvice
public class JsonResponseBodyAdvice extends AbstractMappingJacksonResponseBodyAdvice {


    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType, MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
//        if (!(bodyContainer.getValue() instanceof RestResp)  && !(bodyContainer.getValue() instanceof Json ) &&
//                    !(bodyContainer.getValue() instanceof List)&&!(bodyContainer.getValue() instanceof SecurityConfiguration) &&
//                        !(bodyContainer.getValue() instanceof UiConfiguration) ) {
//            bodyContainer.setValue(RestResp.ok(bodyContainer.getValue()));
//        }
        String url= request.getURI().getPath();
        if(!(url.equals("/v2/api-docs")  || url.equals("/swagger-resources") || url.equals("/swagger-resources/configuration/ui") || url.equals("/swagger-resources/configuration/security"))
                && !(bodyContainer.getValue() instanceof RestResp)  ){
            bodyContainer.setValue(RestResp.ok(bodyContainer.getValue()));
        }

    }

}
