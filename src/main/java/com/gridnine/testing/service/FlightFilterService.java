package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Service that contains methods for Flights filtration depends on different needs. Names of methods explain themselves.
 */

@Service
public class FlightFilterService {

    public List<Flight> removeFlightsWithDepartureTimeBeforeNow(List<Flight> list) {

        List<Flight> elementToDelete = new ArrayList<>();
        List<Flight> incomingList = new ArrayList<>();
        incomingList.addAll(list);

        for (Flight flight : incomingList) {
            List<Segment> segments = flight.getSegments();

            for (Segment segment : segments) {
                if (segment.getDepartureDate().isBefore(LocalDateTime.now())) {
                    elementToDelete.add(flight);
                }

            }

        }
        for (int i = 0; i < elementToDelete.size(); i++) {
            incomingList.remove(elementToDelete.get(i));
        }
        return incomingList;
    }

    public List<Flight> removeFlightsWithArrivalTimeBeforeDepartureTime(List<Flight> list) {
        List<Flight> elementToDelete = new ArrayList<>();
        List<Flight> incomingList = new ArrayList<>();
        incomingList.addAll(list);

        for (Flight flight : incomingList) {
            List<Segment> segments = flight.getSegments();

            for (Segment segment : segments) {
                if (segment.getDepartureDate().isAfter(segment.getArrivalDate())) {
                    elementToDelete.add(flight);
                }

            }

        }
        for (int i = 0; i < elementToDelete.size(); i++) {
            incomingList.remove(elementToDelete.get(i));
        }
        return incomingList;
    }

    public List<Flight> removeFlightsWithTimeBetweenSegmentsMoreThanTwoHours(List<Flight> list) {
        List<Flight> elementToDelete = new ArrayList<>();
        List<Flight> incomingList = new ArrayList<>();
        incomingList.addAll(list);

        for (Flight flight : incomingList) {
            List<Segment> segments = flight.getSegments();
            long count = 0;

            for (int i = 0; i < segments.size(); i++) {
                Segment current = segments.get(i);

                if (i + 1 < segments.size()) {
                    Segment next = segments.get(i+1);
                    count = count + Duration.between(current.getArrivalDate(), next.getDepartureDate()).toMinutes();
                }
            }
            if (count/60.0 > 2) {
                elementToDelete.add(flight);
                count = 0;
            }


        }
        for (int i = 0; i < elementToDelete.size(); i++) {
            incomingList.remove(elementToDelete.get(i));
        }
        return incomingList;
    }
}
