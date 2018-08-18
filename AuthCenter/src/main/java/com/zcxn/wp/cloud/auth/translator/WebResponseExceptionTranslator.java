package com.zcxn.wp.cloud.auth.translator;

import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * Created by Athlon on 2018/3/17.
 */
@Component
public class WebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {

  @Override
  public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
    ResponseEntity responseEntity = super.translate(e);
    OAuth2Exception body = (OAuth2Exception) responseEntity.getBody();
    HttpHeaders headers = new HttpHeaders();
    headers.setAll(responseEntity.getHeaders().toSingleValueMap());

    ObjectMapper mm = new ObjectMapper();
    Map map = new HashMap();
    map.put("code",((OAuth2Exception) responseEntity.getBody()).getHttpErrorCode());
    map.put("msg",((OAuth2Exception) responseEntity.getBody()).getSummary());
    return new ResponseEntity(mm.writeValueAsString(map), headers, responseEntity.getStatusCode());

  }
}
