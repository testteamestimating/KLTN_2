package com.estimating.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.estimating.entity.Users;
import com.estimating.enums.RoleEnum;
import com.estimating.service.core.AbstractBaseService;

@SuppressWarnings("deprecation")
@Service
public class UserSecurityServiceImpl extends AbstractBaseService implements UserDetailsService {

	private static final Logger logger = Logger.getLogger("UserSecurityServiceImpl");

	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		try {
			Users usr = userDao.findOneByName(userName);
				boolean enabled = true;
				boolean accountNonExpired = true;
				boolean credentialsNonExpired = true;
				boolean accountNonLocked = true;
				logger.info("User " + userName + " logon!");
				return new org.springframework.security.core.userdetails.User(usr.getUsername(),
						usr.getPassword(), enabled, accountNonExpired,
						credentialsNonExpired, accountNonLocked,
						getAuthorities(usr.getUserType().getId()));
						
			} 
		 catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public Collection<? extends GrantedAuthority> getAuthorities(int role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}

	public List<String> getRoles(int role) {
		List<String> roles = new ArrayList<String>();
		if (role == RoleEnum.ROLE_REGULAR.getKey()) {
			roles.add("ROLE_REGULAR");
		} else {
			roles.add("ROLE_REGULAR");
		}
		return roles;
	}

	public static List<GrantedAuthority> getGrantedAuthorities(
			List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new GrantedAuthorityImpl(role));
		}
		return authorities;
	}
	

}
