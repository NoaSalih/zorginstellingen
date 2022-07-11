package com.zorginstellingen.security;

import com.zorginstellingen.model.User;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private final CustomUserDetailsService customUserDetailService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailService) {
		super(authenticationManager);
		this.customUserDetailService = customUserDetailService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		String header = request.getHeader(SecurityConstants.HEADER_STRING);
		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken usernamePasswordAuth = geUsernamePasswordAuthenticationToken(request);
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuth);
		chain.doFilter(request, response);

	}

	public UsernamePasswordAuthenticationToken geUsernamePasswordAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstants.HEADER_STRING);
		if (token == null)
			return null;

		String username = Jwts.parser().setSigningKey(SecurityConstants.SECRET)
				.parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
				.getBody()
				.getSubject();

		UserDetails userDetail = customUserDetailService.loadUserByUsername(username);
		User user = customUserDetailService.loadUserByLoginId(username);
		return username != null ? new UsernamePasswordAuthenticationToken(user, null, userDetail.getAuthorities()) : null;
	}
}
