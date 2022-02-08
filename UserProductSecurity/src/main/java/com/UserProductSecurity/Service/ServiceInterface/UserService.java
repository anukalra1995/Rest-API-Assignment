package com.UserProductSecurity.Service.ServiceInterface;

import com.UserProductSecurity.Model.Users;

public interface UserService {
	
	void save(Users user);

    Users findUsername(String username);
    
}
