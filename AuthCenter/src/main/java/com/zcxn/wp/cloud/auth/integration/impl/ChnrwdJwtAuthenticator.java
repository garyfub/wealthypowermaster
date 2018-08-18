package com.zcxn.wp.cloud.auth.integration.impl;

import com.zcxn.wp.cloud.auth.entity.BaseUser;
import com.zcxn.wp.cloud.auth.integration.AuthenticationType;
import com.zcxn.wp.cloud.auth.integration.IAuthenticator;
import com.zcxn.wp.cloud.auth.util.JwtUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * Created by Athlon on 2018/6/15.
 */
@Component
public class ChnrwdJwtAuthenticator extends AbstractPreparableAuthenticator implements IAuthenticator,ApplicationEventPublisherAware {

  private final static String JWT_AUTH_TYPE = "jwt";
  @Value( "${jwt.signingKey:'sitech!@#123'}" )
  private String signingKey;

  private ApplicationEventPublisher applicationEventPublisher;


  @Override
  public BaseUser authenticate(AuthenticationType integrationAuthentication) {

    String jwtToken = integrationAuthentication.getAuthParameter("jwt_token");

    String json =  JwtUtil.getSubject(jwtToken,signingKey );
    System.out.println("decode====json=="+json);
    JSONObject jsonObject = JSONObject.fromObject( json );
    BaseUser currentUser = new BaseUser();
    // currentUser.setAccount( "aaaaa" );
    currentUser.setAccount( jsonObject.getString( "loginNo" ));
    currentUser.setGroupId( jsonObject.getString( "groupId" ) );
    currentUser.setGroupName( jsonObject.getString( "groupName" ) );
    currentUser.setRegionGroupId( jsonObject.getString( "regionGroupId" ) );
    currentUser.setRegionName( jsonObject.getString( "regionName" ) );
    currentUser.setRoleId( jsonObject.getString( "roleId" ) );
    currentUser.setRoleName( jsonObject.getString( "roleName" ) );
    //密码123456
    currentUser.setPassword( "$2a$10$cKRbR9IJktfmKmf/wShyo.5.J8IxO/7YVn8twuWFtvPgruAF8gtKq" );

    return currentUser;
  }

  @Override
  public void prepare(AuthenticationType integrationAuthentication) {
    String jwtToken = integrationAuthentication.getAuthParameter("jwt_token");
    if(StringUtils.isEmpty( jwtToken ))
    {
      throw new RuntimeException( "登陆信息错误" );
    }

  }

  @Override
  public boolean support(AuthenticationType integrationAuthentication) {
    return JWT_AUTH_TYPE.equals(integrationAuthentication.getAuthType());
  }

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    applicationEventPublisher = applicationEventPublisher;
  }
}
