package com.rentalcars.apple;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

	private List<Car> cars;

	public Response() {
		cars = new ArrayList<Car>();

	}

	
	public Response(List<Car> cars) {
		super();
		this.cars = cars;
	}


	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public void addCar(Car car) {
		cars.add(car);
	}


	@Override
	public String toString() {
		return "Response [cars=" + cars + ", getCars()=" + getCars() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	

}
