package com.syh.handle;


import com.alibaba.fastjson.JSON;
import com.syh.domain.ResponseResult;
import com.syh.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //处理异常
        ResponseResult result = new ResponseResult(401, "认证失败");
        String s = JSON.toJSONString(result);
        WebUtils.renderString(response,s);

    }
}
