package com.UserProductSecurity.Security.UserServices;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UserProductSecurity.Model.Users;
import com.UserProductSecurity.Repository.UserRepository;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
    @Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user = userRepository.findByUsername(username).get();
		Set < GrantedAuthority > grantedAuthorities = new HashSet < > ();
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
		
		return UserDetailsImpl.build(user);
	}
	
}
