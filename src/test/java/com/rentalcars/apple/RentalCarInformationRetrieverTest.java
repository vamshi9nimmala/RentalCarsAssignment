package com.rentalcars.apple;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RentalCarInformationRetrieverTest {
	private RentalCarInformationRetriever rentalCarInformationRetriever;
	private final String FILE_BASE_PATH = "src/test/java/com/rentalcars/apple/JsonResponseTestFiles/";

	@Before
	public void setUp() {
		rentalCarInformationRetriever = new RentalCarInformationRetriever();
	}

	@Test
	public void getBlueTeslaCar_test() throws IOException {
		String responseString = new String(Files.readAllBytes(Paths.get(FILE_BASE_PATH + "carsWithSingleTesla.json")));
		List<Car> cars = rentalCarInformationRetriever.getBlueTeslaCars(responseString);
		Assert.assertEquals(1, cars.size());
		Assert.assertEquals("Incorrect car make", "Tesla", cars.get(0).getMake());

	}

	@Test
	public void getBlueTeslaCar_WithzeroCounttest() throws IOException {
		String responseString = new String(Files.readAllBytes(Paths.get(FILE_BASE_PATH + "carsWithZeroTesla.json")));
		List<Car> cars = rentalCarInformationRetriever.getBlueTeslaCars(responseString);
		Assert.assertEquals(0, cars.size());
	}

	@Test
	public void getBlueTeslaCar_WithMultipleCounttest() throws IOException {
		String responseString = new String(Files.readAllBytes(Paths.get(FILE_BASE_PATH + "carsWithMultipleTeslas.json")));
		List<Car> cars = rentalCarInformationRetriever.getBlueTeslaCars(responseString);
		Assert.assertEquals(2, cars.size());
	}
	@Test
	public void getCarsWithLowestRental_EmptyResponseWithDiscounttest() throws IOException {
		List<Car> cars = rentalCarInformationRetriever.getCarsWithLowestRental("", true);
		System.out.println(cars);
		Assert.assertEquals(0,cars.size() );
	}
	@Test
	public void getCarsWithLowestRental_WithDiscounttest() throws IOException {
		String responseString = new String(Files.readAllBytes(Paths.get(FILE_BASE_PATH + "carsWithZeroTesla.json")));
		List<Car> cars = rentalCarInformationRetriever.getCarsWithLowestRental(responseString, true);
		System.out.println(cars);
		Assert.assertEquals(1,cars.size() );
	}
	@Test
	public void getCarsWithLowestRental_WithDiscountMultipleCarstest() throws IOException {
		String responseString = new String(Files.readAllBytes(Paths.get(FILE_BASE_PATH + "carsWithMultipleTeslas.json")));
		List<Car> cars = rentalCarInformationRetriever.getCarsWithLowestRental(responseString, true);
		System.out.println(cars);
		Assert.assertEquals(2,cars.size() );
	}
	@Test
	public void getCarsWithLowestRental_WithoutDiscounttest() throws IOException {
		String responseString = new String(Files.readAllBytes(Paths.get(FILE_BASE_PATH + "carsWithZeroTesla.json")));
		List<Car> cars = rentalCarInformationRetriever.getCarsWithLowestRental(responseString, false);
		System.out.println(cars);
		Assert.assertEquals(1,cars.size() );
	}
	@Test
	public void getCarsWithLowestRental_WithoutDiscountMultipleCarstest() throws IOException {
		String responseString = new String(Files.readAllBytes(Paths.get(FILE_BASE_PATH + "carsWithMultipleTeslas.json")));
		List<Car> cars = rentalCarInformationRetriever.getCarsWithLowestRental(responseString, false);
		System.out.println(cars);
		Assert.assertEquals(2, cars.size() );
	}
	@Test
	public void getCarsWithLowestRental_EmptyResponseWithoutDiscounttest() throws IOException {
		List<Car> cars = rentalCarInformationRetriever.getCarsWithLowestRental("", false);
		System.out.println(cars);
		Assert.assertEquals(0,cars.size() );
	}

	@Test
	public void getCarsWithMoreRevenue_test() throws IOException {
		String responseString = new String(Files.readAllBytes(Paths.get(FILE_BASE_PATH + "carsWithZeroTesla.json")));
		List<Car> cars = rentalCarInformationRetriever.getCarsWithHighestProfit(responseString);
		Assert.assertEquals(1, cars.size() );
		System.out.println(cars);
	}
	@Test
	public void getCarsWithMoreRevenue_MultipleCarstest() throws IOException {
		String responseString = new String(Files.readAllBytes(Paths.get(FILE_BASE_PATH + "MultipleCarsWithHighestRevenue.json")));
		List<Car> cars = rentalCarInformationRetriever.getCarsWithHighestProfit(responseString);
		Assert.assertEquals(2, cars.size() );
		System.out.println(cars);
	}
	@Test
	public void getCarsWithMoreRevenue_EmptyResponsetest() throws IOException {
		List<Car> cars = rentalCarInformationRetriever.getCarsWithHighestProfit("");
		Assert.assertEquals(0, cars.size() );
		System.out.println(cars);
	}
	
}
