package com.gmsj.core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.ImmutableMap;
import com.gmsj.core.business.exception.BizUserErrorCode;
import com.gmsj.core.business.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author Ovrille
 * @date 2017/07/03
 */
@Slf4j
public class JWTUtil {
    /**
     * 使用传入的值获得JWT token,通常情况是登录的时候调用。
     *
     * @param map 例如 传入的map.put("userId",4481973312226302L)
     * @return 获得的token
     */
    public static String produceJWTToken(Map<String, Object> map) {
        try {

            Algorithm algorithm = Algorithm.HMAC256("secret");
            Map<String, Object> headerClaims = new HashMap<>();
            headerClaims.put("owner", "fmountain");
            JWTCreator.Builder builder = JWT.create().withIssuer("coal").withHeader(headerClaims);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object val = entry.getValue();
                if (val instanceof Long) {
                    builder.withClaim(entry.getKey(), (Long) val);
                } else if (val instanceof Integer) {
                    builder.withClaim(entry.getKey(), (Integer) val);
                } else if (val instanceof Boolean) {
                    builder.withClaim(entry.getKey(), (Boolean) val);
                } else if (val instanceof Double) {
                    builder.withClaim(entry.getKey(), (Double) val);
                } else if (val instanceof String) {
                    builder.withClaim(entry.getKey(), (String) val);
                } else {
                    builder.withClaim(entry.getKey(), val.toString());
                }
            }
            builder.withExpiresAt(buildExpirationDate());

            return builder.sign(algorithm);

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new BusinessException(BizUserErrorCode.USER_JWT_SIGN_FAIL);
        }
    }

    private static Date buildExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        return calendar.getTime();
    }

    /**
     * 根据传入的key集合，从token中解析，获取到对应的key,val对，请集中调用，不要多次调用，会没效率。
     *
     * @param coll 例如 传入的coll.add("userId")
     * @param coll 例如 传入的coll.add("userId")
     * @return key, val对
     */
    public static Map<String, Object> getJWTValue(Collection<String> coll, String token) {
        try {

            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("coal")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            //DecodedJWT jwt2 = JWT.decode(token);

            //String algorithmstr = jwt.getAlgorithm();
            //String type = jwt.getType();
            //Claim owner = jwt.getHeaderClaim("owner");
            String issuer = jwt.getIssuer();
            Date expiresAt = jwt.getExpiresAt();

            Map<String, Object> map = new HashMap<>();
            for (String key : coll) {
                map.put(key, jwt.getClaim(key).as(Object.class));
            }

            return map;
        } catch (Exception ex) {
            throw new BusinessException(BizUserErrorCode.USER_JWT_VERIFY_FAIL);
        }
    }


    /**
     * 获取用户ID
     *
     * @param token JWT token
     * @return
     */
    public static Long getUserId(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("coal")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("userId").asLong();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }


    public static void main(String[] args) {
        System.out.println(produceJWTToken(ImmutableMap.of("userId", 4801701682316288L)));
    }
}
