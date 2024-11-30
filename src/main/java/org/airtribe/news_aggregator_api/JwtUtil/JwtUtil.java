package org.airtribe.news_aggregator_api.JwtUtil;

import java.security.Key;
import java.util.Date;

import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Configuration
public class JwtUtil {
	private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private static final long expiration = 86400000L;

	public static String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.signWith(key)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.setIssuedAt(new Date())
				.compact();
	}

	public String getEmailFromToken(String bearer) {
		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(bearer).getBody();
		return claims.getSubject();
	}
}
