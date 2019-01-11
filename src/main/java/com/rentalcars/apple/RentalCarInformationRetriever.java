package com.rentalcars.apple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class to parse and retrieve the rental car information
 * @author Vamshi
 *
 */
public class RentalCarInformationRetriever {

	private Response parseResponse(String responseString) {
		Response response = new Response();
		ObjectMapper mapper = new ObjectMapper();

		try {

			// Convert JSON string from file to Object
			byte[] jsonData = responseString.getBytes();
			JsonNode rootNode = mapper.readTree(jsonData);

			// Checking for empty response
			if (rootNode == null) {
				return response;
			}

			Iterator<JsonNode> jsonNodes = rootNode.elements();
			while (jsonNodes.hasNext()) {
				JsonNode jsonNode = jsonNodes.next().get("car");
				Car car = mapper.treeToValue(jsonNode, Car.class);
				response.addCar(car);
			}

			return response;

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method to return blue Tesla cars
	 * 
	 * @param responseString
	 * @return
	 */
	public List<Car> getBlueTeslaCars(String responseString) {
		List<Car> result = new ArrayList<Car>();

		// Parsing Response String
		Response response = parseResponse(responseString);
		for (Car car : response.getCars()) {
			if (car.getMake().toUpperCase().equals("TESLA")
					&& car.getMetadata().getColor().toUpperCase().equals("BLUE")) {
				result.add(car);
			}
		}

		// Printing the make and Notes as per the requirement
		for (Car c : result) {
			System.out.println(String.format("Make: %s, Notes: %s", c.getMake(), c.getMetadata().getNotes()));
		}
		return result;
	}

	/**
	 * Method to return car with Lowest rental with and without applying discount
	 * based on the flag
	 * 
	 * @param responseString
	 * @param withDiscount   - True: Apply Discount. False: No Discount
	 * @return
	 */
	public List<Car> getCarsWithLowestRental(String responseString, boolean withDiscount) {
		// Parsing Response String
		Response response = parseResponse(responseString);
		HashMap<Float, List<Car>> carPrices = new HashMap<Float, List<Car>>();
		float prevPrice = Float.MAX_VALUE;
		for (Car car : response.getCars()) {

			// Calculating Car Rental Amount based on the discount flag
			float currentCarOriginalPrice = car.getPerdayrent().getPrice();
			float currentCarDiscountPrice = currentCarOriginalPrice - car.getPerdayrent().getDiscount();
			float currentCarPrice = withDiscount ? currentCarDiscountPrice : currentCarOriginalPrice;
			if (currentCarPrice <= prevPrice) {
				updateCarPrices(carPrices, car, currentCarPrice);
				prevPrice = currentCarPrice;
			}
		}
		// Returning empty list when there are 0 cars with lowest rental
		if (prevPrice == Float.MAX_VALUE) {
			return new ArrayList<Car>();
		}

		return carPrices.get(prevPrice);
	}

	/**
	 * Method to return cars with highest profit
	 * 
	 * @param responseString
	 * @return
	 */
	public List<Car> getCarsWithHighestProfit(String responseString) {
		Response response = parseResponse(responseString);
		HashMap<Float, List<Car>> carRevenue = new HashMap<Float, List<Car>>();
		float prevCarTotalProfit = Float.MIN_VALUE;
		for (Car car : response.getCars()) {

			// Calculating the profit based on the below assumptions.
			// Revenue = yeartodate rental count * per day rent
			// Expense = yearToDate Maintenance + Depreciation
			// Profit = Revenue - Expense
			float currentCarOriginalRevenue = car.getPerdayrent().getPrice()
					* car.getMetrics().getRentalcount().getYeartodate();
			float currentCarOriginalExpense = car.getMetrics().getYoymaintanance() + car.getMetrics().getDepreciation();
			float currentCarTotalProfit = currentCarOriginalRevenue - currentCarOriginalExpense;
			if (currentCarTotalProfit >= prevCarTotalProfit) {
				updateCarPrices(carRevenue, car, currentCarTotalProfit);
				prevCarTotalProfit = currentCarTotalProfit;
			}
		}

		// Returning empty list when there are no cars
		if (prevCarTotalProfit == Float.MIN_VALUE) {
			return new ArrayList<Car>();
		}
		return carRevenue.get(prevCarTotalProfit);
	}

	/**
	 * Method for updating the car prices
	 * @param carPrices
	 * @param car
	 * @param currentCarPrice
	 */
	private void updateCarPrices(HashMap<Float, List<Car>> carPrices, Car car, float currentCarPrice) {
		if (carPrices.containsKey(currentCarPrice)) {
			carPrices.get(currentCarPrice).add(car);
		} else {
			List<Car> temp = new ArrayList<Car>();
			temp.add(car);
			carPrices.put(currentCarPrice, temp);
		}
	}

}