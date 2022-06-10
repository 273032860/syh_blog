package com.syh.config;

import com.syh.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启授权注解功能
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Bean//加密类
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问   配置permitAll() 无论什么情况都能访问
                .antMatchers("/user/login").anonymous()

//                .antMatchers("/testCors").hasAuthority("system:dept:list222")

                //配置无需认证的路径
                .antMatchers("/userlogin",
                        "/userlogout",
                        "/userjwt",
                        "/v2/api-docs",
                        "/swagger-resources/configuration/ui",
                        "/swagger-resources",
                        "/swagger-resources/configuration/security",
                        "/swagger-ui.html",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/webjars/**",
                        "/import/test",
                        "/article/hotArticleList",
                        "/user/userregister",
                        "/category/categoryList",
                        "/index").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
        //记住我功能  tokenValiditySeconds token时长
        http.rememberMe().rememberMeParameter("remember").tokenValiditySeconds(604800);
//        http.
//                formmLgin()
//                .tokenRepository(persistentTokenRepository()) // 配置 token 持久化仓库
//                .tokenValiditySeconds(3600) ;// remember 过期时间，单为秒
        //添加过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //添加自定义异常处理器handle
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)


        //添加自定义异常处理器handle
        .accessDeniedHandler(accessDeniedHandler);
        //允许跨域
        http.cors();

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
