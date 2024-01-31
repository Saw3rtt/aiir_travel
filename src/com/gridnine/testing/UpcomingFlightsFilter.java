package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UpcomingFlightsFilter implements FlightFilter {

    @Override
    public List<Flight> filterFlights(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();

        LocalDateTime currentDateTime = LocalDateTime.now();

        for (Flight flight : flights) {
            if (flight.getSegments().stream().allMatch(segment ->
                    segment.getDepartureDate().isAfter(currentDateTime))) {
                filteredFlights.add(flight);
            }
        }

        return filteredFlights;
    }
}
