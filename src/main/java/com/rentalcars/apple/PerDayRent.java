package com.rentalcars.apple;

public class PerDayRent {
	private float price;
	private float discount;

	public PerDayRent() {
		
	}
	public PerDayRent(float price, float discount) {
		this.price = price;
		this.discount = discount;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	@Override
	public String toString() {
		return "PerDayRent [price=" + price + ", discount=" + discount + "]";
	}
	


}
