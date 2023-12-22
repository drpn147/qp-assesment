package com.questionpro.gbooking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.questionpro.gbooking.model.Product;
import com.questionpro.gbooking.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	public ProductService productService;

	@PostMapping("/admin/save")
	public String saveProduct(@RequestBody Product product) {

		return productService.saveProduct(product);
	}

	@GetMapping("/user/displayAll")
	public List<Product> displayAll() {
		return productService.displayAll();
	}

	@PutMapping("/admin/update")
	public String updateProduct(@RequestParam(value = "id") String id, @RequestBody Product product) {
		return productService.updateProduct(id, product);
	}

	@DeleteMapping("/admin/delete")
	public boolean deleteProduct(@RequestParam(value = "id") String id) {
		return productService.deleteProduct(id);
	}

	@PostMapping("/user/invoice")
	public Map<String, String> createInvoice(@RequestParam Map<String, String> products) {
		return productService.createInvoice(products);
	}
}
