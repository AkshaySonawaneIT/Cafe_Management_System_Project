package com.inn.cafe.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.inn.cafe.POJO.Product;
import com.inn.cafe.wrapper.ProductWrapper;

public interface ProductDao extends JpaRepository<Product, Integer>{

	List<ProductWrapper> getAllProduct();
	
	@Modifying
	@Transactional
	Integer updateProductStatus(String status, Integer id);

	List<ProductWrapper> getProductByCategory(Integer id);

	List<ProductWrapper> getProductById(Integer id);
	
	Integer getProductByStatus();
}
