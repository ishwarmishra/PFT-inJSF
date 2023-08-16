package personalfinancetrackerinweb.api;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;

public class JwtUtils {   
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long EXPIRATION_TIME = 60000;  
    
    public static String generateJwtToken(String username)
    {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + EXPIRATION_TIME))
                .signWith(SECRET_KEY,SignatureAlgorithm.HS512)
                .compact();
    }    
    public static String verifyToken(String token){
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build();
        try {
            Claims claims = parser.parseClaimsJws(token).getBody();
            String userId = claims.getSubject();
            return userId;
        } catch ( ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | io.jsonwebtoken.security.SignatureException | IllegalArgumentException e) {
            return null;
        }
    }
}