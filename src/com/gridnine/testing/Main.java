package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Original flights:");
        printFlights(flights);

        System.out.println("\nFiltered flights (UpcomingFlightsFilter):");
        FlightFilter upcomingFilter = new UpcomingFlightsFilter();
        printFlights(upcomingFilter.filterFlights(flights));

        System.out.println("\nFiltered flights (ValidSegmentFilter):");
        FlightFilter validSegmentFilter = new ValidSegmentFilter();
        printFlights(validSegmentFilter.filterFlights(flights));

        System.out.println("\nFiltered flights (LongLayoverFilter):");
        FlightFilter layoverFilter = new LongLayoverFilter();
        printFlights(layoverFilter.filterFlights(flights));
    }

    private static void printFlights(List<Flight> flights) {
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }
}
