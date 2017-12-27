package com.gmsj.core.util;

import com.gmsj.core.conf.ConstantsDefinition;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;


public class DigestUtils {
    public final static String ENCODING = "UTF-8";

    public final static String DIGEST_KEY = "AVuu7SWwv99YCbb11IHlk5ONlq77YXba3HLlp57RRvu99Bbe13HHkk55RQuu99i3";

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String decodeBase64(String data) {
        byte[] _date = data.getBytes();
        try {
            return new String(Base64.decodeBase64(_date), ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encoding unknow", e);
        }
    }

    public static String decodeBase64(String data, String encoding) {
        byte[] _date = data.getBytes();
        try {
            return new String(Base64.decodeBase64(_date), encoding);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encoding unknow", e);
        }
    }

    public static String encodeBase64(String data) {
        byte[] _date = data.getBytes();
        try {
            return new String(Base64.encodeBase64(_date), ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encoding unknow", e);
        }
    }

    public static String encodeBase64(byte[] date) {
        try {
            return new String(Base64.encodeBase64(date), ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encoding unknow", e);
        }
    }

    public static byte[] decodeBase64(byte[] date) {
        return Base64.decodeBase64(date);
    }


    public static String encodeBase64(String data, String encoding) {
        byte[] _date = data.getBytes();
        try {
            return new String(Base64.encodeBase64(_date), encoding);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encoding unknow", e);
        }
    }

    public static String encodeBase64(String data, String byteEncoding, String encoding) {
        try {
            byte[] _date = data.getBytes(byteEncoding);
            return new String(Base64.encodeBase64(_date), encoding);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encoding unknow", e);
        }
    }

    public static String md5(String data) {
        return org.apache.commons.codec.digest.DigestUtils.md5Hex(data);
    }


    public static String sha1(String data) {
        return org.apache.commons.codec.digest.DigestUtils.sha1Hex(data);
    }


    public static String urlEncode(String data, String encoding) {
        try {
            return URLEncoder.encode(data, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encoding unknow", e);
        }
    }

    public static String urlDecode(String data, String encoding) {
        try {
            return URLDecoder.decode(data, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encoding unknow", e);
        }
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    public static String generateSign(String characterEncoding, SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + ConstantsDefinition.E_KEY);
        //String str1 = MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        String str1 = MD5Encode(sb.toString(), characterEncoding);
        String str2 = md5(sb.toString());

        return str1;
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5(DigestUtils.DIGEST_KEY + "715812"));
    }
}
