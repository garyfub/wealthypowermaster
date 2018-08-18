package com.zcxn.wp.cloud.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Athlon on 2018/3/15.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableWebSecurity
@ComponentScan(basePackages = {"com.sitech.prom.iease.cloud"},
    excludeFilters=@ComponentScan.Filter(type = FilterType.REGEX,
        pattern = "com.sitech.prom.iease.cloud.common.bean.resource.*"))
public class AuthCenterApplication extends WebMvcConfigurerAdapter {

  public static void main(String[] args) {
    SpringApplication.run(AuthCenterApplication.class, args);
  }
  // @Override
  // public void addViewControllers(ViewControllerRegistry registry) {
  //   registry.addViewController("/login").setViewName("login");
  //   registry.addViewController("/oauth/confirm_access").setViewName("authorize");
  // }
}
