package test;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import com.gridnine.testing.ValidSegmentFilter;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidSegmentFilterTest {

    @Test
    public void testFilterFlightsWithValidSegments() {
        ValidSegmentFilter validSegmentFilter = new ValidSegmentFilter();

        List<Segment> validSegments = new ArrayList<>();
        validSegments.add(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(2)));
        validSegments.add(new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(5)));
        Flight validFlight = new Flight(validSegments);

        List<Segment> invalidSegments = new ArrayList<>();
        invalidSegments.add(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(2)));
        invalidSegments.add(new Segment(LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(3)));
        Flight invalidFlight = new Flight(invalidSegments);

        List<Flight> flights = new ArrayList<>();
        flights.add(validFlight);
        flights.add(invalidFlight);

        List<Flight> filteredFlights = validSegmentFilter.filterFlights(flights);

        assertEquals(1, filteredFlights.size());
        assertEquals(validFlight, filteredFlights.get(0));
    }

    @Test
    public void testFilterFlightsWithEmptySegments() {
        ValidSegmentFilter validSegmentFilter = new ValidSegmentFilter();

        Flight emptySegmentFlight = new Flight(new ArrayList<>());

        List<Flight> flights = new ArrayList<>();
        flights.add(emptySegmentFlight);

        List<Flight> filteredFlights = validSegmentFilter.filterFlights(flights);

        assertEquals(1, filteredFlights.size());
        assertEquals(emptySegmentFlight, filteredFlights.get(0));
    }
}
