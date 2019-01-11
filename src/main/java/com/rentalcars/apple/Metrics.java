package com.rentalcars.apple;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Metrics {
	private float yoymaintanance;
	private float depreciation;
	private RentalCount rentalcount;

	public Metrics() {

	}

	public Metrics(float yoymaintanance, float depreciation) {
		super();
		this.yoymaintanance = yoymaintanance;
		this.depreciation = depreciation;
	}

	public RentalCount getRentalcount() {
		return rentalcount;
	}

	public void setRentalcount(RentalCount rentalcount) {
		this.rentalcount = rentalcount;
	}

	public float getYoymaintanance() {
		return yoymaintanance;
	}

	public void setYoymaintanance(float yoymaintanance) {
		this.yoymaintanance = yoymaintanance;
	}

	public float getDepreciation() {
		return depreciation;
	}

	public void setDepreciation(float depreciation) {
		this.depreciation = depreciation;
	}

	@Override
	public String toString() {
		return "Metrics [yoy=" + yoymaintanance + ", depreciation=" + depreciation + ", rentalcount=" + rentalcount + "]";
	}

}
