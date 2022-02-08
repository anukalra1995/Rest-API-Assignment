package com.UserProductSecurity.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.UserProductSecurity.Model.*;
import com.UserProductSecurity.Payload.Response.JwtResponse;
import com.UserProductSecurity.Repository.*;
import com.UserProductSecurity.Service.*;

@CrossOrigin(origins = "*", maxAge = 3600)
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
	
	private JwtResponse jwtres;
	
	String message = null;
	
	//Register User
    @PostMapping("/user/signup")
    public ResponseEntity<?> registration(@RequestBody Users userForm) {
    	
    	System.out.println(userForm);
    	if(userRepository.existsByUsername(userForm.getUsername())){
    		
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
            
        }
    	
//    	userService.save(userForm);
    	
    	return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    //Login User
	@PostMapping("/user/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody Users userForm){

		jwtres = userService.login(userForm);
		
        return new ResponseEntity<>(jwtres, HttpStatus.OK);
    }
    
	//Logout User
	@PostMapping("/user/signout")
	public ResponseEntity<String> logoutuser(HttpSession session){
		
		
		session.invalidate();
		
		return new ResponseEntity<>("Signout Sucessfully", HttpStatus.OK);
	}
	
	//Add Products
	@PostMapping("/product/add")
	public ResponseEntity<String> addproddetails(@RequestBody Product products){
		
		System.out.println(jwtres.getRoles());
		if(jwtres == null) {
			
			return new ResponseEntity<>("Login First", HttpStatus.BAD_REQUEST);
			
		}else {
			
			System.out.println("The product details are: \n"+products);
			
			prodService.addprod(products);
			
			System.out.println(jwtres);
			
			message = "Added successfully";
		}
		
		return new ResponseEntity<>(message, HttpStatus.OK);
		
		
	}
	
	//Search Products
	@GetMapping("/product/Search")
	public ResponseEntity<Object> searchproduct(@RequestBody Product products){
		
		if(jwtres == null) {
			return new ResponseEntity<>("Login First", HttpStatus.BAD_REQUEST);
		}
		
		List<Product> prod = new ArrayList<>();
		
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
		
		return new ResponseEntity<>(prod, HttpStatus.OK);
	}
	
	//Search Product Price with Id
	@GetMapping("/product/getprices/{prodid}")
	public ResponseEntity<Object> getproductprice(@PathVariable("prodid") int prodid){
		
		if(jwtres == null) {
			return new ResponseEntity<>("Login First", HttpStatus.BAD_REQUEST);
		}
		
		Product products = null; 
		if(!ProdRepository.existsByProdid(prodid)){
			
			return new ResponseEntity<>("No such products with this ID exists", HttpStatus.BAD_REQUEST);
			
		}else {
			message = "Searched data with price";
			
			products = prodService.proddetails(prodid);
			
			System.out.println("After searching, Price of Product id are: "+products);
		}
		return new ResponseEntity<>("Price of Product is: "+products.getProdprice(), HttpStatus.OK);
		
	}
	
	//Search Product Price with Id
	@GetMapping("/product/getdesc/{prodid}")
	public ResponseEntity<Object> getproductdesc(@PathVariable("prodid") int prodid){
		
		if(jwtres == null) {
			
			return new ResponseEntity<>("Login First", HttpStatus.BAD_REQUEST);
		}
		
		Product products = null; 
		
		if(!ProdRepository.existsByProdid(prodid)){
			
			return new ResponseEntity<>("No such products with this ID exists", HttpStatus.BAD_REQUEST);
			
		}else {
			message = "Searched data with price";
			
			products = prodService.proddetails(prodid);
			
			System.out.println("After searching, Price of Product id are: "+products);
		}
		
		return new ResponseEntity<>("Description of Product: "+products.getProddesc(), HttpStatus.OK);
			
		}
}