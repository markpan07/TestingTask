package com.gridnine.testing.service;

import static com.gridnine.testing.constants.FlightFilterServiceConstants.*;

import com.gridnine.testing.model.Flight;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Class for testing FlightFilterService
 */

public class FlightFilterServiceTest {

    private final FlightFilterService out = new FlightFilterService();



    @Test
    void removeFlightsWithDepartureTimeBeforeNowTest() {
        List<Flight> result = out.removeFlightsWithDepartureTimeBeforeNow(BASIC_LIST_OF_FLIGHTS);
        List<Flight> expected = LIST_OF_FLIGHTS_WITHOUT_DEPARTURE_BEFORE_NOW;
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    void removeFlightsWithArrivalTimeBeforeDepartureTimeTest() {
        List<Flight> result = out.removeFlightsWithArrivalTimeBeforeDepartureTime(BASIC_LIST_OF_FLIGHTS);
        List<Flight> expected = LIST_OF_FLIGHTS_ARRIVAL_BEFORE_DEPARTURE;
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    void removeFlightsWithTimeBetweenSegmentsMoreThanTwoHoursTest() {
        List<Flight> result = out.removeFlightsWithTimeBetweenSegmentsMoreThanTwoHours(BASIC_LIST_OF_FLIGHTS);
        List<Flight> expected = LIST_OF_FLIGHTS_TIME_BETWEEN_SEGMENTS_LESS_2H;
        Assertions.assertThat(result).isEqualTo(expected);
    }
}
