package com.UserProductSecurity.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UserProductSecurity.Model.Product;
import com.UserProductSecurity.Repository.ProductRepository;
import com.UserProductSecurity.Service.ServiceInterface.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository prodRepository;

	@Override
	public void addprod(Product product) {
		
		System.out.println("Inside addprod, the details are: \n"+product);
		prodRepository.save(product);
		
	}

	@Override
	public Product proddetails(int prodid) {
		
		Product product = prodRepository.findByProdid(prodid).get();
		
		System.out.println(product);
		
		return product;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> searchprod(Product product) {
		
		List<Product> proddetails = null ;
		
		String name = product.getProdname();
		String code = product.getProdcode() ;
		String brand = product.getProdbrand();
		
		if(name != null && code == null && brand == null) {						//Only By Product Name
			
			System.out.println("Product Details By Name");
			proddetails = (List<Product>) prodRepository.findByProdname(product.getProdname()).get();
			
		}else if(code != null && brand == null && name == null) {				//Only By Product Code
			
			System.out.println("Product Details By Code");
			proddetails =(List<Product>) prodRepository.findByProdcode(product.getProdcode()).get();
			
		}else if(brand != null && code == null && name == null) {				//Only By Product Brand
			
			System.out.println("Product Details By Brand");
			
			proddetails = prodRepository.findByProdbrandLike(product.getProdbrand());
			
		}else if(name != null &&  brand != null && code == null ){				//By Product Name  And By Product Brand
			
			System.out.println("Product Details By Brand And By Name");
			
			proddetails = prodRepository.findByProdnameAndProdbrandLike(product.getProdname(),product.getProdbrand());
			
		}else if(name != null && code != null && brand == null) {				//By Product Name  And By Product Code
			
			System.out.println("Product Details By Code And By Name");
			proddetails =  prodRepository.findByProdnameAndProdcode(name,code);
			
		}else if(code != null && brand != null && name == null ){				//By Product Code  And By Product Brand
			
			System.out.println("Product Details By Code And By Brand");
			proddetails = prodRepository.findByProdbrandAndProdcode(brand, code);
			
		}else {																	//By Product Name, By Product Code And By Product Brand
			
			System.out.println("Product Details By Code, By Brand And By Name");
			
			proddetails = prodRepository.findByProdnameAndProdcodeAndProdbrand(name,code,brand);
			
		}
				
		return proddetails;
	}
}
