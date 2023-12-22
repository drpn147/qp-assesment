package com.questionpro.gbooking.model;

public class Invoice {

	private int quantity;
	private String productName;
	private double totalAmount;

	public Invoice(int quantity, String productName, double totalAmount) {
		super();
		this.quantity = quantity;
		this.productName = productName;
		this.totalAmount = totalAmount;
	}

	public Invoice() {
		// TODO Auto-generated constructor stub
	}

	public int getTransactionId() {
		return quantity;
	}

	public void setTransactionId(int transactionId) {
		this.quantity = transactionId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
