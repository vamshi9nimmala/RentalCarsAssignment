package com.rentalcars.apple;

public class Car {

	private String make;
	private String model;
	private String vin;
	private MetaData metadata;
	private Metrics metrics;
	private PerDayRent perdayrent;

	public Car() {

	}

	public Car(String make, String model, String vin, MetaData metadata, PerDayRent perdayrent, Metrics metrics) {
		this.make = make;
		this.model = model;
		this.vin = vin;
		this.metadata = metadata;
		this.perdayrent = perdayrent;
		this.metrics = metrics;

	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public MetaData getMetadata() {
		return metadata;
	}

	public void setMetadata(MetaData metadata) {
		this.metadata = metadata;
	}

	public PerDayRent getPerdayrent() {
		return perdayrent;
	}

	public void setPerdayrent(PerDayRent perdayrent) {
		this.perdayrent = perdayrent;
	}

	public Metrics getMetrics() {
		return metrics;
	}

	public void setMetrics(Metrics metrics) {
		this.metrics = metrics;
	}

	@Override
	public String toString() {
		return "Car [make=" + make + ", model=" + model + ", vin=" + vin + ", metadata=" + metadata + ", metrics="
				+ metrics + ", perdayrent=" + perdayrent + "]";
	}

}