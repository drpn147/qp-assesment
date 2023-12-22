package com.questionpro.gbooking.component;

public enum Message {

	SAVE("Product save successfully"), NOT_FOUND("Product not available"), FAILED("Operation Failed"),
	DUPLICATE("Duplicate Product Present"), INVALID("Product data is invalid"), UPDATE("Product Update Successfully"),
	TRANSACTION("Transaction Id:"), PRODUCTS("Purchased Products"), TOTAL("Total Amount");

	private String msg;

	Message(String string) {
		this.msg = string;
	}

	public String getMessage() {
		return this.msg;
	}
}
