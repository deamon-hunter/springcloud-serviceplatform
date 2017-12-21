package com.gmsj.core.feign;

import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import org.springframework.cloud.netflix.feign.support.ResponseEntityDecoder;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * @author Ovrille
 * @date 2017/08/23
 */

public class FeignDecoder extends ResponseEntityDecoder {

    public FeignDecoder(Decoder decoder) {
        super(decoder);
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {


        String body = StreamUtils.copyToString(response.body().asInputStream(), Charset.forName("utf-8"));

        return super.decode(response, type);
    }
}
