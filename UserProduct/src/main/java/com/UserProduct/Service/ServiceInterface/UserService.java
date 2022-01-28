package com.UserProduct.Service.ServiceInterface;

import com.UserProduct.Model.Users;

public interface UserService {
	
	void save(Users user);

    Users findUsername(String username);
    
}
