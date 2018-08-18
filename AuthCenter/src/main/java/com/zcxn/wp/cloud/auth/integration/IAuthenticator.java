package com.zcxn.wp.cloud.auth.integration;

import com.zcxn.wp.cloud.auth.entity.BaseUser;

/**
 * Created by Athlon on 2018/6/15.
 */
public interface IAuthenticator {

  /**
   * 处理集成认证
   * @param integrationAuthentication
   * @return
   */
  BaseUser authenticate(AuthenticationType integrationAuthentication);


  /**
   * 进行预处理
   * @param integrationAuthentication
   */
  void prepare(AuthenticationType integrationAuthentication);

  /**
   * 判断是否支持集成认证类型
   * @param integrationAuthentication
   * @return
   */
  boolean support(AuthenticationType integrationAuthentication);

  /** 认证结束后执行
   * @param integrationAuthentication
   */
  void complete(AuthenticationType integrationAuthentication);

}
