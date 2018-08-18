package com.zcxn.wp.cloud.auth.integration;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Athlon on 2018/6/15.
 */
public class AuthenticationType implements Serializable{

  private String authType;

  private String username;

  private Map<String,String[]> authParameters;

  public String getAuthParameter(String paramter){
    String[] values = this.authParameters.get(paramter);
    if(values != null && values.length > 0){
      return values[0];
    }
    return null;
  }

  public String getAuthType() {
    return authType;
  }

  public void setAuthType(String authType) {
    this.authType = authType;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Map<String, String[]> getAuthParameters() {
    return authParameters;
  }

  public void setAuthParameters(Map<String, String[]> authParameters) {
    this.authParameters = authParameters;
  }
}
