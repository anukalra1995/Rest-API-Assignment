package com.UserProduct.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.UserProduct.Model.*;
import com.UserProduct.Repository.*;
import com.UserProduct.Service.*;

@RestController
public class UserController {
	
	@Autowired
    private UserServiceImpl userService;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private ProductServiceImpl prodService;
	
	@Autowired
	private ProductRepository ProdRepository;
	
	String message = null;
	
	//Register User
    @PostMapping("/user/signup")
    public ResponseEntity<?> registration(@RequestBody Users userForm) {
    	
    	System.out.println(userForm);
    	if(userRepository.existsByUsername(userForm.getUsername())){
    		
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
            
        }
    	
    	userService.save(userForm);
    	
    	return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    //Login User
	@SuppressWarnings("unchecked")
	@PostMapping("/user/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody Users userForm, HttpSession session){
		
    	
    	List<Object> usernotes = (List<Object>) session.getAttribute("USER_SESSION");
    	
		userService.login(userForm,usernotes,session);
		
		System.out.println(usernotes);
		
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }
    
	//Logout User
	@SuppressWarnings("unchecked")
	@PostMapping("/user/signout")
	public ResponseEntity<String> logoutuser(HttpSession session){
		
		List<Object> usernotes = (List<Object>) session.getAttribute("USER_SESSION");
		
		System.out.println("In Logout, Session variables are: "+usernotes);
		
		if(usernotes == null) {
			
			System.out.println("No data available, Login First");
			return new ResponseEntity<>("No data available, Login First", HttpStatus.BAD_REQUEST);
		}else {
			
			userService.logout(usernotes,session);
			message = "User signed-out successfully!.";
		}
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	//Add Products
	@SuppressWarnings("unchecked")
	@PostMapping("/product/add")
	public ResponseEntity<String> addproddetails(@RequestBody Product products, HttpServletRequest request,HttpSession session){
		
		List<Object> usernotes = (List<Object>) session.getAttribute("USER_SESSION");
		
		session.setAttribute("UserSession", usernotes!=null? usernotes:new ArrayList<>());
		
		System.out.println(usernotes);
		
		if(usernotes == null)  {
			
			System.out.println("Login First");
			return new ResponseEntity<>("Login First", HttpStatus.BAD_REQUEST);
			
		}else if(!usernotes.contains("ROLE_ADMIN")) {
			
			message = "User cannot add data";
			
		}else {
			System.out.println("The product details are: \n"+products);
			prodService.addprod(products);
			message = "Added Product successfully";
			
		}
		 return new ResponseEntity<>(message, HttpStatus.OK);
		
	}
	
	//Search Products
	@SuppressWarnings("unchecked")
	@GetMapping("/product/Search")
	public ResponseEntity<Object> searchproduct(@RequestBody Product products, HttpServletRequest request,HttpSession session){
		
		List<Object> usernotes = (List<Object>) session.getAttribute("USER_SESSION");
		
		List<Product> prod = new ArrayList<>();
		
		session.setAttribute("UserSession", usernotes!=null? usernotes:new ArrayList<>());
		
		if(usernotes == null)  {
			
			System.out.println("Login First");
			return new ResponseEntity<>("Login First", HttpStatus.BAD_REQUEST);
			
		}else {
			if(products == null) {
				
				return new ResponseEntity<>("Enter data to search products", HttpStatus.BAD_REQUEST);
				
			}else {
				if(!(ProdRepository.existsByProdname(products.getProdname()) || 
						ProdRepository.existsByProdcode(products.getProdcode()) || 
							ProdRepository.existsByProdbrand(products.getProdbrand()))){
					
					return new ResponseEntity<>("No such products exists", HttpStatus.BAD_REQUEST);
					
				}
				message = "Searched data";
				
				prod = prodService.searchprod(products);
			}
		}
		return new ResponseEntity<>(prod, HttpStatus.OK);
	}
	
	//Search Product Price with Id
	@SuppressWarnings("unchecked")
	@GetMapping("/product/getprices/{prodid}")
	public ResponseEntity<Object> getproductprice(@PathVariable("prodid") int prodid, HttpSession session){
		
		Product products = null; 
		List<Object> usernotes = (List<Object>) session.getAttribute("USER_SESSION");
		
		session.setAttribute("UserSession", usernotes!=null? usernotes:new ArrayList<>());
		System.out.println(usernotes);
		
		if(usernotes == null)  {
			
			System.out.println("Login First");
			return new ResponseEntity<>("Login First", HttpStatus.BAD_REQUEST);
			
		}else if(!ProdRepository.existsByProdid(prodid)){
			
			return new ResponseEntity<>("No such products with this ID exists", HttpStatus.BAD_REQUEST);
			
		}else {
			message = "Searched data with price";
			
			products = prodService.proddetails(prodid);
			
			System.out.println("After searching, Price of Product id are: "+products);
		}
		return new ResponseEntity<>("Price of Product is: "+products.getProdprice(), HttpStatus.OK);
		
	}
	
	//Search Product Price with Id
	@SuppressWarnings("unchecked")
	@GetMapping("/product/getdesc/{prodid}")
	public ResponseEntity<Object> getproductdesc(@PathVariable("prodid") int prodid, HttpSession session){
			
			Product products = null; 
			List<Object> usernotes = (List<Object>) session.getAttribute("USER_SESSION");
			
			session.setAttribute("UserSession", usernotes!=null? usernotes:new ArrayList<>());
			System.out.println(usernotes);
			
			if(usernotes == null)  {
				
				System.out.println("Login First");
				return new ResponseEntity<>("Login First", HttpStatus.BAD_REQUEST);
				
			}else if(!ProdRepository.existsByProdid(prodid)){
				
				return new ResponseEntity<>("No such products with this ID exists", HttpStatus.BAD_REQUEST);
				
			}else {
				message = "Searched data with price";
				
				products = prodService.proddetails(prodid);
				
				System.out.println("After searching, Price of Product id are: "+products);
			}
			return new ResponseEntity<>("Description of Product: "+products.getProddesc(), HttpStatus.OK);
			
		}
}
