package org.airtribe.news_aggregator_api.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	public static final String SECRET_KEY = "aVeryLongSecureSecretKeyForJwtTokenThatIsAtLeast256BitsLong!";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String token = authorizationHeader.substring(7); // Extract the JWT token

			// Parse the token to extract claims
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
					.build()
					.parseClaimsJws(token)
					.getBody();

			String username = claims.getSubject();

			if (username != null) {
				// You can optionally extract roles or authorities from the claims if they are included in your JWT
				// For now, it's empty, but you could set authorities or roles based on your token's claims
				User authenticatedUser = new User(username, "", Collections.emptyList());
				UsernamePasswordAuthenticationToken authentication =
						new UsernamePasswordAuthenticationToken(authenticatedUser, null, Collections.emptyList());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				// Set the authentication in the SecurityContext
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		// Continue the filter chain
		filterChain.doFilter(request, response);
	}
}
