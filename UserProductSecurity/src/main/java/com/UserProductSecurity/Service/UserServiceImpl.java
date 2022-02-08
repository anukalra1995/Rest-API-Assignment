package com.UserProductSecurity.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.UserProductSecurity.Model.Users;
import com.UserProductSecurity.Payload.Response.JwtResponse;
import com.UserProductSecurity.Repository.UserRepository;
import com.UserProductSecurity.Security.Jwt.JwtUtils;
import com.UserProductSecurity.Security.UserServices.UserDetailsImpl;
import com.UserProductSecurity.Service.ServiceInterface.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserRepository userRepository;
	
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Override
	public void save(Users user){
		
	    String roles = user.getRole();
	    
	    
	    user.setPwd(bCryptPasswordEncoder.encode(user.getPwd()));
	    
	    System.out.println(user);
	    
	    userRepository.save(user);
		
	}
	
	@Override
	public Users findUsername(String username) {
		
		return userRepository.findByUsername(username).get();
	}

	public JwtResponse login(Users users) {
		
		Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
															(users.getUsername(), users.getPwd()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		Users use = findUsername(users.getUsername());
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		System.out.println("Generated JWT Token is: "+jwt);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();  
		
		System.out.println(users);	
		
		return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), use.getRole());
		
	}

}

/*
 * if(roles.equals("Admin")){
	    	
	    	user.setRole("ROLE_ADMIN");
	    	System.out.println("Role set is: "+user.getRole());
	    	
	    }else if(roles.equals("User")){
	    	
	    	user.setRole("ROLE_USER");
	    	System.out.println("Role set is: "+user.getRole());
	    }
 */