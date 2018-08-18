package com.zcxn.wp.cloud.auth.controller;

import com.google.code.kaptcha.Producer;
import com.zcxn.wp.cloud.common.constant.SecurityConstants;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 * Created by Athlon on 2018/4/28.
 */
@Controller
public class ValidateCodeController {


  @Autowired
  private Producer producer;

  @Autowired
  private RedisTemplate redisTemplate;


  /**
   * 创建验证码
   *
   * @param request request
   * @throws Exception
   */
  @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{randomStr}")
  public void createCode(@PathVariable String randomStr, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setHeader("Cache-Control", "no-store, no-cache");
    response.setContentType("image/jpeg");
    //生成文字验证码
    String text = producer.createText();
    //生成图片验证码
    BufferedImage image = producer.createImage(text);
    redisTemplate.opsForValue().set(SecurityConstants.DEFAULT_CODE_KEY + randomStr, text, SecurityConstants.DEFAULT_IMAGE_EXPIRE, TimeUnit.SECONDS);
    ServletOutputStream out = response.getOutputStream();
    System.out.println("code===="+redisTemplate.opsForValue().get( SecurityConstants.DEFAULT_CODE_KEY + randomStr ));

    ImageIO.write(image, "JPEG", out);
    IOUtils.closeQuietly(out);
  }


}
