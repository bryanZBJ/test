package com.example.auth.utils;


import com.example.auth.jwt.JWTHelper;
import com.example.auth.jwt.JWTInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

    @Autowired
    private JWTHelper jwtHelper;

    /**
     * 获取token
     *
     * @param jwtInfo
     * @return
     */
    public String generateToken(JWTInfo jwtInfo) {
        return jwtHelper.generateToken(jwtInfo);
    }

    /**
     * token获取用户信息
     *
     * @param token
     * @return
     */
    public JWTInfo getInfoFromToken(String token) {
        return jwtHelper.getInfoFromToken(token);
    }
}
