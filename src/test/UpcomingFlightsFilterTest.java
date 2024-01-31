package test;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.UpcomingFlightsFilter;
import org.junit.jupiter.api.Test;
import com.gridnine.testing.Segment;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpcomingFlightsFilterTest {

    @Test
    void filterFlights_shouldFilterOutPastFlights() {
        List<Flight> flights = FlightBuilder.createFlights();
        UpcomingFlightsFilter filter = new UpcomingFlightsFilter();

        List<Flight> result = filter.filterFlights(flights);

        assertEquals(5, result.size(), "Expected 4 upcoming flights, but found " + result.size());

        result.forEach(flight -> System.out.println("Upcoming Flight: " + flight));
    }

    @Test
    void filterFlights_shouldNotFilterOutFutureFlights() {
        List<Flight> flights = FlightBuilder.createFlights();
        UpcomingFlightsFilter filter = new UpcomingFlightsFilter();

        List<Flight> result = filter.filterFlights(flights);

        for (Flight flight : result) {
            for (Segment segment : flight.getSegments()) {
                LocalDateTime departureDate = segment.getDepartureDate();
                LocalDateTime currentDate = LocalDateTime.now();
                assertEquals(true, departureDate.isAfter(currentDate),
                        "Filtered flight has a segment with a past departure date: " + flight);
            }
        }
    }

    @Test
    void filterFlights_shouldHandleEmptyList() {
        List<Flight> flights = List.of();
        UpcomingFlightsFilter filter = new UpcomingFlightsFilter();

        List<Flight> result = filter.filterFlights(flights);

        assertEquals(0, result.size(), "Expected 0 upcoming flights, but found " + result.size());
    }
}