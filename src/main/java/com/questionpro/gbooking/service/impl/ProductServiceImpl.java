package com.questionpro.gbooking.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.questionpro.gbooking.component.Message;
import com.questionpro.gbooking.exception.NotFound;
import com.questionpro.gbooking.model.Product;
import com.questionpro.gbooking.repository.ProductRepository;
import com.questionpro.gbooking.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	public ProductRepository productRepo;

	@Override
	public String saveProduct(Product product) {
		if (product == null) {
			throw new NotFound(Message.NOT_FOUND.getMessage());
		}

		boolean validator = checkProduct(product);
		if (validator) {
			productRepo.save(product);
			return Message.SAVE.getMessage();
		}
		return Message.FAILED.getMessage();
	}

	private boolean checkProduct(Product product) {
		List<Product> productList = displayAll();
		Optional<Product> products = productList.stream()
				.filter(p -> p.getProductName().equalsIgnoreCase(product.getProductName())).findFirst();
		if (products.isEmpty()) {
			if (product.getProductPrice() > 0.0 && product.getProductQuantity() > 0) {
				return true;
			} else {
				throw new NotFound(Message.INVALID.getMessage());
			}
		} else {
			throw new NotFound(Message.DUPLICATE.getMessage());
		}
	}

	@Override
	public List<Product> displayAll() {
		List<Product> productList = productRepo.findAll();
		if (productList == null) {
			throw new NotFound(Message.NOT_FOUND.getMessage());
		}
		return productList;
	}

	@Override
	public String updateProduct(String id, Product product) {
		if (id == null || product == null) {
			throw new NotFound(Message.NOT_FOUND.getMessage());
		}

		Optional<Product> products = productRepo.findById(Integer.parseInt(id));
		if (products.isPresent()) {
			products.get().setProductPrice(product.getProductPrice());
			products.get().setProductQuantity(product.getProductQuantity());
			productRepo.save(products.get());
			return Message.UPDATE.getMessage();
		} else {
			throw new NotFound(Message.NOT_FOUND.getMessage());
		}
	}

	@Override
	public boolean deleteProduct(String id) {
		if (id == null) {
			return false;
		}

		Optional<Product> products = productRepo.findById(Integer.parseInt(id));
		if (products.isPresent()) {
			productRepo.delete(products.get());
			return true;
		} else {
			throw new NotFound(Message.NOT_FOUND.getMessage());
		}
	}

	@Override
	public Map<String, String> createInvoice(Map<String, String> products) {
		if (!products.isEmpty()) {
			Map<String, String> invoice = new HashMap<>();
			List<Product> productList = displayAll();
			Random random = new Random();
			String productName = null;
			double totalAmount = 0.0;
			for (Map.Entry<String, String> map : products.entrySet()) {
				Optional<Product> product = productList.stream()
						.filter(p -> p.getProductId() == Integer.parseInt(map.getKey())).findFirst();
				if (product.isPresent() && product.get().getProductQuantity() > Integer.parseInt(map.getValue())) {
					if (productName == null) {
						productName = product.get().getProductName();
						totalAmount = totalAmount + Integer.parseInt(map.getValue()) * product.get().getProductPrice();

					} else {
						productName = productName + "," + product.get().getProductName();
						totalAmount = totalAmount + Integer.parseInt(map.getValue()) * product.get().getProductPrice();
					}
					product.get()
							.setProductQuantity(product.get().getProductQuantity() - Integer.parseInt(map.getValue()));
				} else {
					throw new NotFound(product.get().getProductName() + " Out of Stock");
				}
				productRepo.save(product.get());

			}
			invoice.put(Message.TRANSACTION.getMessage(), Integer.toString(random.nextInt(1000000, 100000000)));
			invoice.put(Message.PRODUCTS.getMessage(), productName);
			invoice.put(Message.TOTAL.getMessage(), Double.toString(totalAmount));
			return invoice;
		} else {
			throw new NotFound(Message.NOT_FOUND.getMessage());
		}
	}

}
