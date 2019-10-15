package com.example.auth.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTInfo implements Serializable {

    private String username;
    private String userId;
    private String userNo;
    private String extend;
    private String expire;
    private String oprNo;

    public JWTInfo(String username, String userId, String extend) {
        this.username = username;
        this.userId = userId;
        this.extend = extend;
    }

}
