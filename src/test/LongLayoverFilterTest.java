package test;

import com.gridnine.testing.Flight;
import com.gridnine.testing.LongLayoverFilter;
import com.gridnine.testing.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongLayoverFilterTest {

    @Test
    public void testFilterFlightsWithNoLongLayovers() {
        LongLayoverFilter longLayoverFilter = new LongLayoverFilter();

        List<Segment> shortLayoverSegments = new ArrayList<>();
        shortLayoverSegments.add(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(2)));
        shortLayoverSegments.add(new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(5)));
        Flight shortLayoverFlight = new Flight(shortLayoverSegments);

        List<Segment> noLayoverSegments = new ArrayList<>();
        noLayoverSegments.add(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(2)));
        noLayoverSegments.add(new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(4)));
        Flight noLayoverFlight = new Flight(noLayoverSegments);

        List<Flight> flights = new ArrayList<>();
        flights.add(shortLayoverFlight);
        flights.add(noLayoverFlight);

        List<Flight> filteredFlights = longLayoverFilter.filterFlights(flights);

        assertEquals(2, filteredFlights.size());
        assertEquals(shortLayoverFlight, filteredFlights.get(0));
        assertEquals(noLayoverFlight, filteredFlights.get(1));
    }

    @Test
    public void testFilterFlightsWithLongLayovers() {
        LongLayoverFilter longLayoverFilter = new LongLayoverFilter();

        List<Segment> longLayoverSegments = new ArrayList<>();
        longLayoverSegments.add(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(2)));
        longLayoverSegments.add(new Segment(LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(6)));
        Flight longLayoverFlight = new Flight(longLayoverSegments);

        List<Flight> flights = new ArrayList<>();
        flights.add(longLayoverFlight);

        List<Flight> filteredFlights = longLayoverFilter.filterFlights(flights);

        assertEquals(1, filteredFlights.size()); // Исправлено ожидаемое значение
        assertEquals(longLayoverFlight, filteredFlights.get(0));
    }

    @Test
    public void testFilterFlightsWithEmptySegments() {
        LongLayoverFilter longLayoverFilter = new LongLayoverFilter();

        Flight emptySegmentFlight = new Flight(new ArrayList<>());

        List<Flight> flights = new ArrayList<>();
        flights.add(emptySegmentFlight);

        List<Flight> filteredFlights = longLayoverFilter.filterFlights(flights);

        assertEquals(1, filteredFlights.size());
        assertEquals(emptySegmentFlight, filteredFlights.get(0));
    }
}
