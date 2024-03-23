package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightBuilder;
import com.gridnine.testing.service.FlightFilterService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingApplication.class, args);

		FlightFilterService filterService = new FlightFilterService();
		List<Flight> initialFlightSet = FlightBuilder.createFlights();

		System.out.println("\nInitial flight set -> \n" + initialFlightSet.toString() + "\n"+
				"___________________ \n"
		);
		System.out.println("Flights With Departure Time Before Now Was Removed -> \n" +
				filterService.removeFlightsWithDepartureTimeBeforeNow(initialFlightSet).toString() + "\n"+
				"___________________ \n"
				);
		System.out.println("Flights With Arrival Time Before Departure Time Was Removed-> \n" +
				filterService.removeFlightsWithArrivalTimeBeforeDepartureTime(initialFlightSet).toString() + "\n"+
				"___________________ \n"
		);
		System.out.println("Flights With Time Between Segments More Than Two Hours Was Removed -> \n" +
				filterService.removeFlightsWithTimeBetweenSegmentsMoreThanTwoHours(initialFlightSet).toString() + "\n"+
				"___________________ \n"
		);
	}

}
