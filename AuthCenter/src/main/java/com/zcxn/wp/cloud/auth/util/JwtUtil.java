package com.zcxn.wp.cloud.auth.util;

/*import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;*/
public class JwtUtil {

  /*public static String generateToken(String signingKey, String subject) {
    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);
    JwtBuilder builder = Jwts.builder()
        .setSubject(subject)
        .setIssuedAt(now)
        .signWith(SignatureAlgorithm.HS256, signingKey);

    return builder.compact();
  }

  public static String getSubject(String token, String signingKey){
    return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
  }*/

  public static String getSubject(String token, String signingKey){
    return "admin";
  }
}
