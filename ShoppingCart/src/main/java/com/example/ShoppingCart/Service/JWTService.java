package com.example.ShoppingCart.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private static String secretKey = "U29tZVZlcnlTZWN1cmUyNTZCaXRLZXlGb3JKV1RTaGFNYWM=";
    private final UserService userService;

    public JWTService(UserService userService) {
        this.userService = userService;
    }

//    public JWTService(){
//        try {
//            KeyGenerator keyGen = KeyGenerator.getInstance(secretKey);
//            SecretKey sk = keyGen.generateKey();
//            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
//
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public String generateToken(String username,String roles) {


        Map<String,Object> claims = new HashMap<>();

        claims.put("role", roles); // 👈 Add roles to the claims

        return Jwts
                .builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
                .and()
                .signWith(getKey())
                .compact();

    }

    private SecretKey getKey() {
        byte [] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);

    }

    public String exactUserName(String token) {
        return extractClaim(token, Claims::getSubject);

    }

    private <T> T extractClaim(String token, Function<Claims,T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }

    public boolean validateToken(String token) {
        final  String userName = exactUserName(token);
        return (isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }
}
