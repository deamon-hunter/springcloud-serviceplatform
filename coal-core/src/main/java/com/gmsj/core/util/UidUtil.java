package com.gmsj.core.util;

import java.math.BigInteger;
import java.util.UUID;

/**
 * UUID工具类
 *
 * @author hongQiang tang
 * @version $Id: UidUtil.java, v 0.1 2017年6月20日 上午11:48:00 Administrator Exp $
 */
public class UidUtil {

    public static String uuid() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.replaceAll("-", "");
    }

    public static String[] uuids(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = uuid();
        }
        return ss;
    }

    public static String salt() {
        String uuid = uuid();
        String longSn = new BigInteger(uuid, 16).toString();
        return longSn.substring(0, 12);
    }

    public static String salt(int num) {
        String uuid = uuid();
        String longSn = new BigInteger(uuid, 16).toString();
        return longSn.substring(0, num);
    }
}
