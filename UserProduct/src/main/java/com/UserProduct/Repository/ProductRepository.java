package com.UserProduct.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UserProduct.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<Product> findByProdname(String prodname);
	
	Optional<Product> findByProdcode(String prodcode);
	
	List<Product> findByProdbrandLike(String prodbrand);
	
	Optional<Product> findByProdid(int prodid);
	
	List<Product> findByProddesc(int prodid);		
	
	List<Product> findByProdnameAndProdcode(String prodname,String prodcode);
	
	List<Product> findByProdnameAndProdbrandLike(String prodname,String prodbrand); //ByName and By Brand
	
	List<Product> findByProdnameAndProdcodeAndProdbrand(String prodname,String prodcode, String prodbrand);
	
	List<Product> findByProdbrandAndProdcode(String prodbrand,String prodcode);
		
	//Exist By methods
	
	Boolean existsByProdid(int prodid);
	
	Boolean existsByProdname(String prodname);
	
	Boolean existsByProdcode(String prodcode);
	
	Boolean existsByProdbrand(String prodbrand);

	Boolean existsByProdnameAndProdbrand(String prodname, String prodbrand);
	
	Boolean existsByProdcodeAndProdbrand(String prodcode, String prodbrand);
	
	Boolean existsByProdnameAndProdcode(String prodname, String prodcode);
	
	Boolean existsByProdcodeAndProdnameAndProdbrand(String prodcode,String prodname, String prodbrand);
}
