package com.questionpro.gbooking.service;

import java.util.List;
import java.util.Map;

import com.questionpro.gbooking.model.Product;

public interface ProductService {

	String saveProduct(Product product);

	List<Product> displayAll();

	String updateProduct(String id, Product product);

	boolean deleteProduct(String id);

	Map<String, String> createInvoice(Map<String, String> products);

}
