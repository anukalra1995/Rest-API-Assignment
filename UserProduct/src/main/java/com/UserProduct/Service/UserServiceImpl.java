package com.UserProduct.Service;

import java.util.*;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.UserProduct.Model.*;
import com.UserProduct.Repository.*;
import com.UserProduct.SecurityConfig.Exception.BadRequestException;
import com.UserProduct.Service.ServiceInterface.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserRepository userRepository;
	
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Override
	public void save(Users user){
		
	    String roles = user.getRole();
	    
	    if(roles.equals("Admin")){
	    	
	    	user.setRole("ROLE_ADMIN");
	    	System.out.println("Role set is: "+user.getRole());
	    	
	    }else if(roles.equals("User")){
	    	
	    	user.setRole("ROLE_USER");
	    	System.out.println("Role set is: "+user.getRole());
	    }
	    
	    user.setPwd(bCryptPasswordEncoder.encode(user.getPwd()));
	    
	    System.out.println(user);
	    
	    userRepository.save(user);
		
	}
	
	@Override
	public Users findUsername(String username) {
		
		return userRepository.findByUsername(username).get();
	}

	public void login(Users users, List<Object> notes,HttpSession session) {
		
		Users usersexist = findUsername(users.getUsername());

		if(users.getPwd() == null) {
			
			System.out.println("password Not provided.");
			throw new BadRequestException("password Not provided. Please Provide password");
			
		}else if (!bCryptPasswordEncoder.matches(users.getPwd(), usersexist.getPwd())) {
			
			throw new BadRequestException("Invalid user name and password combination.");
		}

		Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
															(users.getUsername(), users.getPwd()));

		System.out.println(users);

		if (notes == null) {
		    notes = new ArrayList<>();
		    session.setAttribute("USER_SESSION", notes);
		}

		notes.add(usersexist.getFname() + " " + usersexist.getLname());
		notes.add(usersexist.getRole());
		notes.add(usersexist.getUsername());

		session.setAttribute("USER_SESSION", notes);
		System.out.println("In session, values are: \n "+notes);

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	public void logout(List<Object> notes,HttpSession session) {
		
		session.setAttribute("User_SESSION", notes);
		System.out.println(notes);
		
		if(notes == null) {
			session.invalidate();
		}
		
		notes.clear();		//Clear the Session list data
		
		//Then it will invalidate the session which will clear the data from configured database
		session.invalidate();
		
		
	}

}
