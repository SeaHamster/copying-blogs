package com.copying.blogs.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.copying.blogs.model.entity.CyBlogsUser;
import org.thymeleaf.util.StringUtils;

import java.util.Date;

@SuppressWarnings("unused")
public class TokenUtil {
    /** 12小时过期 */
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000;
    /** 密钥盐 */
    private static final String TOKEN_SECRET = "h^+=Xz2q";
    /** 密钥盐 */
    public static final String TOKEN_HEADER = "copying-Token";

    /**
     * 签名生成
     *
     * @param user CyBlogsUserDto
     * @return String签名生成
     */
    public static String sign(CyBlogsUser user) {
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("copying")
                    .withClaim("userName", user.getUsername())//存放自己的信息
                    .withClaim("userId", user.getUserId())
                    .withClaim("permissionList", StringUtils.join(user.getPermissionList(), ","))
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;

    }

    /**
     * 签名验证
     */
    public static boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("copying").build();
            DecodedJWT jwt = verifier.verify(token);
//            System.out.println("认证通过：");
//            System.out.println("issuer: " + jwt.getIssuer());
//            System.out.println("name: " + jwt.getClaim("name").asString());
//            System.out.println("过期时间：      " + jwt.getExpiresAt());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取内容
     */
    public static Long getUserId(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("copying").build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("userId").asLong();
    }

    /**
     * 获取name
     */
    public static String getUserName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userName").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取权限
     */
    public static String getPermissionList(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("permissionList").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
