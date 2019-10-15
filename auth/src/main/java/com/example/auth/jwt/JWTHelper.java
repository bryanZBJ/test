package com.example.auth.jwt;

import com.example.auth.constants.AuthConstants;
import com.example.common.util.DateFormatUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTHelper {

    @Value("${jwt.expiration:36}")
    private Long expiration;

    @Value("${jwt.secret:2333}")
    private String secret;

    /**
     * 生成token的过期时间
     *
     * @return
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 密钥加密token
     *
     * @param jwtInfo
     * @return
     * @throws Exception
     */
    public String generateToken(JWTInfo jwtInfo) {
        String compactJws = Jwts.builder()
                .setSubject(jwtInfo.getUsername())
                .claim(AuthConstants.JWT_KEY_USER_ID, jwtInfo.getUserId())
                .claim(AuthConstants.JWT_KEY_EXTENDS, jwtInfo.getExtend())
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return compactJws;
    }

    /**
     * 密钥加密token
     *
     * @param jwtInfo
     * @return
     * @throws Exception
     */
    public String generateTokenNotExpire(JWTInfo jwtInfo) {
        String compactJws = Jwts.builder()
                .setSubject(jwtInfo.getUsername())
                .claim(AuthConstants.JWT_KEY_USER_ID, jwtInfo.getUserId())
                .claim(AuthConstants.JWT_KEY_EXTENDS, jwtInfo.getExtend())
                // token不再保存过期时间，登录过期以redis为主
//                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return compactJws;
    }


    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public Jws<Claims> parserToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return claimsJws;
    }

    /**
     * 获取token中的用户信息
     *
     * @param token
     * @return
     * @throws Exception
     */
    public JWTInfo getInfoFromToken(String token) {
        Jws<Claims> claimsJws = parserToken(token);
        Claims body = claimsJws.getBody();
        return new JWTInfo(body.getSubject(), getObjectValue(body.get(AuthConstants.JWT_KEY_USER_ID)), getObjectValue(body.get(AuthConstants.JWT_KEY_EXTENDS)));
    }

    /**
     * 获取token中的用户信息
     *
     * @param token
     * @return
     * @throws Exception
     */
    public JWTInfo getExpireInfoFromToken(String token) {
        Jws<Claims> claimsJws = parserToken(token);
        Claims body = claimsJws.getBody();
        JWTInfo jwtInfo = new JWTInfo(body.getSubject(), getObjectValue(body.get(AuthConstants.JWT_KEY_USER_ID)), getObjectValue(body.get(AuthConstants.JWT_KEY_EXTENDS)));
        if (body.getExpiration() != null) {
            jwtInfo.setExpire(DateFormatUtil.formatDate(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND, body.getExpiration()));
        }
        return jwtInfo;
    }


    public Date getExpirationFromToken(String token) {
        Jws<Claims> claimsJws = parserToken(token);
        Claims body = claimsJws.getBody();
        return body.getExpiration();
    }

    public static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }
}
