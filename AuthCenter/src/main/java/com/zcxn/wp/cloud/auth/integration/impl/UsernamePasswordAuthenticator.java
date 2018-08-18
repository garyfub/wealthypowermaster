package com.zcxn.wp.cloud.auth.integration.impl;

import com.zcxn.wp.cloud.auth.entity.BaseUser;
import com.zcxn.wp.cloud.auth.integration.AuthenticationType;
import com.zcxn.wp.cloud.auth.service.BaseRoleService;
import com.zcxn.wp.cloud.auth.service.BaseUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by Athlon on 2018/6/15.
 */
@Component
@Primary
public class UsernamePasswordAuthenticator extends AbstractPreparableAuthenticator {

  @Autowired
  private BaseUserService baseUserService;
  @Autowired
  private BaseRoleService baseRoleService;
  @Autowired
  private JdbcTemplate jdbcTemplate;


  @Override
  public BaseUser authenticate(AuthenticationType integrationAuthentication) {
    String account=integrationAuthentication.getAuthParameter( "username" );
    BaseUser baseUser = baseUserService.findByUserAccount(account);
    return baseUser;
  }

  @Override
  public void prepare(AuthenticationType integrationAuthentication) {

  }

  @Override
  public boolean support(AuthenticationType integrationAuthentication) {
    return StringUtils.isEmpty(integrationAuthentication.getAuthType());
  }
}
