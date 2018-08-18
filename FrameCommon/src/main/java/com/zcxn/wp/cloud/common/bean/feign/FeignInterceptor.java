package com.zcxn.wp.cloud.common.bean.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于feign传递token
 * Created by Athlon on 2018/4/12.
 */
@Component
public class FeignInterceptor {

    @Bean
    public RequestInterceptor requestTokenBearerInterceptor() {

        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                        .getRequestAttributes();
                if (attributes != null) {
                    HttpServletRequest request = attributes.getRequest();
                    String version = request.getParameter("server_version");
                    if (StringUtils.isNotEmpty(version)) {
                        requestTemplate.header("version", version);
                    }
                }
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication != null) {
                    OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
                    requestTemplate.header("Authorization", "Bearer " + details.getTokenValue());
                }


            }
        };
    }

}
