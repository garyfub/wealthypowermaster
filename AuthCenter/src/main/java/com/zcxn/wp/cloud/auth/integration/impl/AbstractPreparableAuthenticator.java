package com.zcxn.wp.cloud.auth.integration.impl;

import com.zcxn.wp.cloud.auth.entity.BaseUser;
import com.zcxn.wp.cloud.auth.integration.AuthenticationType;
import com.zcxn.wp.cloud.auth.integration.IAuthenticator;

/**
 * Created by Athlon on 2018/6/15.
 */
public abstract class AbstractPreparableAuthenticator implements IAuthenticator {

  @Override
  public abstract BaseUser authenticate(AuthenticationType integrationAuthentication) ;

  @Override
  public abstract void prepare(AuthenticationType integrationAuthentication);

  @Override
  public abstract boolean support(AuthenticationType integrationAuthentication);

  @Override
  public void complete(AuthenticationType integrationAuthentication) {

  }
}
