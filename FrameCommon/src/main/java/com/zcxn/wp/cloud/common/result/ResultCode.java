package com.zcxn.wp.cloud.common.result;

/**
 * Created by Athlon on 2018/4/19.
 */
public enum ResultCode {

  SUCCESS(200),//成功
  FAIL(400),//失败
  UNAUTHORIZED(401),//未认证（签名错误）
  NOT_FOUND(404),//接口不存在
  FORBIDDEN(403),
  INTERNAL_SERVER_ERROR(500);//服务器内部错误

  private final int code;

  ResultCode(int code) {
    this.code = code;
  }

  public int code() {
    return code;
  }

}
