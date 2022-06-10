package com.syh.handle;

import com.alibaba.fastjson.JSON;
import com.syh.domain.ResponseResult;
import com.syh.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
       //异常授权
        ResponseResult result = new ResponseResult(403, "授权失败");
        String s = JSON.toJSONString(result);
        WebUtils.renderString(response,s);

    }
}
