package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;

public class ValidSegmentFilter implements FlightFilter {

    @Override
    public List<Flight> filterFlights(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();

        for (Flight flight : flights) {
            if (isValidSegments(flight.getSegments())) {
                filteredFlights.add(flight);
            }
        }

        return filteredFlights;
    }

    private boolean isValidSegments(List<Segment> segments) {
        for (Segment segment : segments) {
            if (segment.getDepartureDate().isAfter(segment.getArrivalDate())) {
                return false;
            }
        }
        return true;
    }
}
