package com.zcxn.wp.cloud.auth.integration;

/**
 * Created by Athlon on 2018/6/15.
 */
public class AuthenticationContext {

  private static ThreadLocal<AuthenticationType> holder = new ThreadLocal<>();

  public static void set(AuthenticationType integrationAuthentication){
    holder.set(integrationAuthentication);
  }

  public static AuthenticationType get(){
    return holder.get();
  }

  public static void clear(){
    holder.remove();
  }


}
