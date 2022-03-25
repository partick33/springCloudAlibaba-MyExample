package com.partick.authservice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

/**
 * JWT工具类
 * @author partick_peng
 */
@Component
public class JWTUtil<E extends String> {

    private static String TOKEN_KEY = "321678DS!34321312342GD";

    /**
     * 生成jwt
     * @param e
     * @return
     */
    public String createToken(E e) {
        String base64 = new BASE64Encoder().encode(TOKEN_KEY.getBytes());

        SecretKey secretKey = Keys.hmacShaKeyFor(base64.getBytes());

        String jwt = Jwts.builder().setSubject(e).signWith(secretKey).compact();

        return jwt;
    }

    /**
     * 校验解密token
     * @param jwt
     * @return
     */
    public String checkJwt(String jwt) {
        String base64 = new BASE64Encoder().encode(TOKEN_KEY.getBytes());

        SecretKey secretKey = Keys.hmacShaKeyFor(base64.getBytes());

        JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey).build();

        Jws<Claims> claimsJws = parser.parseClaimsJws(jwt);

        return claimsJws.getBody().getSubject();
    }
}