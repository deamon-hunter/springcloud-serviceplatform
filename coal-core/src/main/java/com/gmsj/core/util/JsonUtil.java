/*
 * Copyright (c) 2016 xiaomaihd and/or its affiliates.All Rights Reserved.
 *            http://www.xiaomaihd.com
 */
package com.gmsj.core.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@Slf4j
public class JsonUtil {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new Jackson2ObjectMapperBuilder().build();
        objectMapper.findAndRegisterModules();
        //去掉默认的时间戳格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //设置为中国上海时区
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        //空值不序列化
        objectMapper.setSerializationInclusion(Include.USE_DEFAULTS);
        //反序列化时，属性不存在的兼容处理
        objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //序列化时，日期的统一格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //单引号处理
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @SneakyThrows
    public static <T> T toObject(String json, Class<T> clazz) {
        return objectMapper.readValue(json, clazz);
    }

    @SneakyThrows
    public static <T> String toJsonString(T entity) {
        return objectMapper.writeValueAsString(entity);
    }

    @SneakyThrows
    public static <T> T toList(String json, Class clazz) {
        JavaType javaType = getParametricTypeJavaType(ArrayList.class, clazz);
        return objectMapper.readValue(json, javaType);
    }

    private static JavaType getParametricTypeJavaType(Class parametrizedClass, Class... clazz) {
        return objectMapper.getTypeFactory().constructParametricType(parametrizedClass, clazz);
    }


    @SneakyThrows
    public static Map<String, Object> toMap(String str) {
        JavaType javaType = getParametricTypeJavaType(HashMap.class, String.class, Object.class);
        return objectMapper.readValue(str, javaType);
    }

    @SneakyThrows
    public static JsonNode toJsonNode(String source) {
        return objectMapper.readTree(source);
    }
}