package com.example.auth.api;

import com.example.auth.entity.JWTUser;
import com.example.auth.service.AuthService;
import com.example.common.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/jwt")
@Api(value = "鉴权", tags = {"AuthApi"})
public class AuthApi {

    @Autowired
    private AuthService authService;

    /**
     * token验证
     *
     * @return
     */
    @RequestMapping(value = "verify", method = RequestMethod.GET)
    @ApiOperation(value = "token验证", notes = "verify")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token", required = true)})
    public R verify(@RequestParam("token") String token) {
        try {
            boolean validate = authService.validate(token);
            if (!validate) {
                return R.fail(HttpStatus.UNAUTHORIZED.hashCode(), "登录身份过期，请重新登录");
            }
            return R.success();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return R.fail(HttpStatus.UNAUTHORIZED.hashCode(), "登录身份无效，请重新登录");
        }
    }

    /**
     * 获取token
     *
     * @param jwtUser
     * @return
     */
    @RequestMapping(value = "token", method = RequestMethod.POST)
    @ApiOperation(value = "获取token", notes = "createAuthenticationToken")
    public String createAuthenticationToken(@RequestBody JWTUser jwtUser) {
        try {
            return authService.getTokenByUser(jwtUser);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}
