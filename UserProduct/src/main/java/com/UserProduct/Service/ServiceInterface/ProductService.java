package com.UserProduct.Service.ServiceInterface;

import java.util.List;

import com.UserProduct.Model.Product;

public interface ProductService {
	
	List<Product> searchprod(Product product);
	
	void addprod(Product product);
	
//	Product getprodid(int prodid);
	
	Product proddetails(int prodid);
	
}
