package org.airtribe.news_aggregator_api.JwtUtil;

import java.nio.charset.StandardCharsets;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtUtil {

	// Ensure the key is long enough for HMAC-SHA algorithms (256 bits or more)
	public static final String SECRET_KEY = "aVeryLongSecureSecretKeyForJwtTokenThatIsAtLeast256BitsLong!";

	// Method to generate a JWT token
	public static String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
				.compact();
	}

	// Method to extract the email from the JWT token
	public String getEmailFromToken(String bearer) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))  // Ensure key is properly used for validation
				.build()
				.parseClaimsJws(bearer)
				.getBody();
		return claims.getSubject();
	}
}
