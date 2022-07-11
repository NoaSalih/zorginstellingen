package com.zorginstellingen.security;

import com.zorginstellingen.encryption.AES;
import com.zorginstellingen.model.User;
import com.zorginstellingen.repositories.UserRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		User applicationUser = loadUserByLoginId(loginId);
		return new org.springframework.security.core.userdetails.User(applicationUser.getUsername(), "{noop}" + applicationUser.getPassword(), AuthorityUtils.createAuthorityList(applicationUser.getRole()+""));
	}

	public User loadUserByLoginId(String username) {
		User user = null;
		try {
			user = userRepository.findByUsername(username);
			String pass = AES.decrypt(user.getPassword(),"HEALTH");
			user.setPassword(new String(Base64.encodeBase64(pass.getBytes())));
		} catch (Exception e) {
			if (user == null){
				e.printStackTrace();
			}
		}
		if (user == null)
			return new User();
		else
			return user;
	}
}
