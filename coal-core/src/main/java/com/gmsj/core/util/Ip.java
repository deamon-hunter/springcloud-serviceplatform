package com.gmsj.core.util;

import org.apache.commons.collections.MapUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public final class Ip {

    public static final RestTemplate Rest = new RestTemplate();

    public static final String IP_LOCATION = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=%s";

    public static final String getIpLocation(String ip) {

        if (ip.startsWith("192.168")) return "本地主机";

        Map<?, ?> result = Rest.getForObject(String.format(IP_LOCATION, ip), Map.class);
        String country = MapUtils.getString(result, "country", "中国");
        String province = MapUtils.getString(result, "province", "");
        String city = MapUtils.getString(result, "city", "");
        return (country + "." + province + city);
    }
}
