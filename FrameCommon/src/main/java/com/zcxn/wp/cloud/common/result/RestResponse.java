package com.zcxn.wp.cloud.common.result;

import java.io.Serializable;

/**
 * 用于统一封装返回结果
 * Created by Athlon on 2018/4/19.
 */
public class RestResponse<T> implements Serializable {


    private static final String OK = "success";

     private static final String ERROR = "failure";


    private Integer code;

    private String errorCode;

    private String message;

    private T body;

    public Integer getCode() {
      return code;
    }

    public RestResponse<T> setCode(int resultCode) {
      this.code = resultCode;
      return this;
    }

    public String getErrorCode() {
      return errorCode;
    }

    public RestResponse<T> setErrorCode(String errorCode) {
      this.errorCode = errorCode;
      return this;
    }

    public String getMessage() {
      return message;
    }

    public RestResponse<T> setMessage(String message) {
      this.message = message;
      return this;
    }

    public T getBody() {
      return body;
    }

    public RestResponse<T> setBody(T body) {
      this.body = body;
      return this;
    }

    public RestResponse<T> success()
    {
      this.code= ResultCode.SUCCESS.code();
      this.message=OK;
      return this;
    }

    public RestResponse<T> success(T body)
    {
      this.code= ResultCode.SUCCESS.code();
      this.message=OK;
      this.body=body;
      return this;
    }

    public RestResponse<T> error(String message)
    {
      this.code= ResultCode.FAIL.code();
      this.message=message;
      this.errorCode=ERROR;
      return this;
    }




}
