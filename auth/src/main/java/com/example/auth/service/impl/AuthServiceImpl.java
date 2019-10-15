package com.example.auth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.auth.entity.JWTUser;
import com.example.auth.jwt.JWTInfo;
import com.example.auth.service.AuthService;
import com.example.auth.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String getTokenByUser(JWTUser jwtUser) {
        JWTInfo jwtInfo = new JWTInfo();
        jwtInfo.setUserId(jwtUser.getUserId());
        jwtInfo.setUsername(jwtUser.getUserName());
        jwtInfo.setExtend(this.getExtends(jwtUser));
        return jwtTokenUtil.generateToken(jwtInfo);
    }

    @Override
    public boolean validate(String token) {
        String extend = jwtTokenUtil.getInfoFromToken(token).getExtend();
        if (StringUtils.isNotEmpty(extend)) {
            return true;
        }
        return false;
    }

    @Override
    public String refresh(String oldToken) {
        return jwtTokenUtil.generateToken(jwtTokenUtil.getInfoFromToken(oldToken));
    }

    /**
     * auth附加字段转换json
     *
     * @param auth
     * @return
     */
    private String getExtends(Object auth) {
        return JSONObject.toJSONString(auth);
    }
}
