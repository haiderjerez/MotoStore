package pru.betr.valhalla.user.Security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "U29sb19RdWllcm9fVGVuZXJfUGxhdGFfUG9kZXJfWV9TYWx1ZF9QYXJhX1BvZGVyX0RhcmxlX0FfTWlzX1BhZHJlc19Ub2RvX0xvX1F1ZV9Ob19UdWJpZXJvbg==";
    private final long EXPIRATION_TIME = 86400000;

    private Key getSigninKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); // Decodifica la clave en Base64
    return Keys.hmacShaKeyFor(keyBytes);
}

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigninKey())
                .compact();
    }

    public boolean validateToken(String token, String username){
        return extractUserName(token ).equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    public String extractUserName(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); 
    }
    
}
