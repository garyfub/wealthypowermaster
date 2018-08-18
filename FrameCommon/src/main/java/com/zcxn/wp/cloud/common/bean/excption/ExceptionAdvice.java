package com.zcxn.wp.cloud.common.bean.excption;

import com.zcxn.wp.cloud.common.result.RestResponse;
import com.zcxn.wp.cloud.common.result.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 统一的封装处理
 * Created by Athlon on 2018/4/19.
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

  private Logger logger = LoggerFactory.getLogger( ExceptionAdvice.class );
  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public RestResponse handleHttpMessageNotReadableException(
      HttpMessageNotReadableException e) {
    return new RestResponse<String>().error("参数转换失败！");
  }

  /**
   * 405 - Method Not Allowed
   */
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public RestResponse handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException e) {
    logger.info("不支持当前请求方法" + e);
    return new RestResponse<String>().error("不支持当前："+e.getMethod()+"请求方法！");
  }

  /**
   * 415 - Unsupported Media Type
   */
  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public RestResponse handleHttpMediaTypeNotSupportedException(Exception e) {
    logger.info("不支持当前媒体类型" + e);
    return new RestResponse().error("不支持的媒体类型");
  }

  /**
   * 500 - Internal Server Error
   */
  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler(Exception.class)
  public RestResponse handleException(Exception e) {
    e.printStackTrace();
    RestResponse restResponse = new RestResponse();
    restResponse.setCode( ResultCode.INTERNAL_SERVER_ERROR.code() );
    restResponse.setMessage( e.getMessage() );
    return restResponse;
  }

  /**
   * 500 - 内部错误
   */
  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler(IllegalArgumentException.class)
  public RestResponse parameterException(IllegalArgumentException e) {
    logger.info("非法参数" + e.toString());
    return new RestResponse().error(e.getMessage());
  }

  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler(NullPointerException.class)
  public RestResponse parameterException(NullPointerException e) {
    logger.info("非法参数" + e.toString());
    return new RestResponse().error(e.getMessage());
  }




}
