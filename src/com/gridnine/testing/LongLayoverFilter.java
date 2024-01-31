package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LongLayoverFilter implements FlightFilter {

    @Override
    public List<Flight> filterFlights(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();

        for (Flight flight : flights) {
            if (!hasLongLayover(flight)) {
                filteredFlights.add(flight);
            }
        }

        return filteredFlights;
    }

    private boolean hasLongLayover(Flight flight) {
        List<Segment> segments = flight.getSegments();
        for (int i = 0; i < segments.size() - 1; i++) {
            LocalDateTime arrivalTime = segments.get(i).getArrivalDate();
            LocalDateTime nextDepartureTime = segments.get(i + 1).getDepartureDate();
            Duration layoverDuration = Duration.between(arrivalTime, nextDepartureTime);

            // Проверка, если ожидание больше чем два часа
            if (layoverDuration.toHours() > 2) {
                return true;
            }
        }
        return false;
    }
}
