package com.zcxn.wp.cloud.common.bean.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zcxn.wp.cloud.common.bean.config.FilterUrlsPropertiesConifg;
import com.zcxn.wp.cloud.common.bean.config.RedisTemplateTokenStore;
import com.zcxn.wp.cloud.common.result.RestResponse;
import com.zcxn.wp.cloud.common.result.ResultCode;
import java.io.PrintWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Created by athlon on 2018/3/16.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

    @Autowired
    private FilterUrlsPropertiesConifg filterUrlsPropertiesConifg;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Bean
    RedisTemplateTokenStore redisTokenStore(){
        RedisTemplateTokenStore tokenStore = new RedisTemplateTokenStore();
        tokenStore.setRedisTemplate( redisTemplate );
        return tokenStore;
    }
    // TokenStore tokenStore()
    // {
    //    return  new JwtTokenStore( jwtTokenEnhancer() );
    // }
    //
    // @Bean
    // protected JwtAccessTokenConverter jwtTokenEnhancer() {
    //     JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    //     converter.setSigningKey("123");
    //     return converter;
    // }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
        http.headers().frameOptions().disable();
        String[] ingores= new String[filterUrlsPropertiesConifg.getIgnore().size()];
        filterUrlsPropertiesConifg.getIgnore().toArray(ingores);
        http.authorizeRequests().antMatchers(ingores)
            .permitAll().anyRequest().authenticated().and();
        http.httpBasic();
    }




    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(redisTokenStore());
        resources.accessDeniedHandler((httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json; charset=utf-8");
            // Map<String> result = new R<>(new PigDeniedException("授权失败，禁止访问"));
            RestResponse<String> response = new RestResponse<String>();
            response.setCode( ResultCode.FORBIDDEN.code() );
            response.setMessage( "授权失败,禁止访问" );
            ObjectMapper objectMapper = new ObjectMapper();
            httpServletResponse.setStatus(403);
            PrintWriter printWriter = httpServletResponse.getWriter();
            printWriter.append(objectMapper.writeValueAsString(response));
        });
        // resources.expressionHandler()
    }
}
