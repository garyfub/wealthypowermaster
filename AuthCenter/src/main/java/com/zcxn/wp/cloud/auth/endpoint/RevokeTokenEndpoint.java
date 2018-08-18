package com.zcxn.wp.cloud.auth.endpoint;

import com.zcxn.wp.cloud.common.result.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FrameworkEndpoint
public class RevokeTokenEndpoint {
  @Autowired
  private ConsumerTokenServices consumerTokenServices;

  @RequestMapping(value = "/oauth/token", method= RequestMethod.DELETE)
  public @ResponseBody
  RestResponse<String> revokeToken(String access_token){

    if (consumerTokenServices.revokeToken(access_token)){
     return new RestResponse<String>().success( "注销成功" );
    }else {
      return new RestResponse<String>().success( "注销失败" );
    }
  }
}