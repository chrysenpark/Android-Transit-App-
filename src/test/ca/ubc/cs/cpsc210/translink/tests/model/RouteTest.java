package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.*;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RouteTest {
    Route r;
    Route r2;
    Route r3;
    RoutePattern rp;
    RoutePattern rp2;
    Stop s;
    Stop s2;

    @BeforeEach
    public void setup() {
        r = new Route("43");
        r2 = new Route("44");
        r3 = new Route("44");
        rp = new RoutePattern("44 UBC", "UBC", "South", r);
        rp2 = new RoutePattern("84 UBC","UBC", "South", r);
        s = new Stop(1, "Blanca", new LatLon(10,10 ));
        s2 = new Stop(2, "Alma", new LatLon(11, 11));
    }

    @Test
    public void testConstructor() {
        assertEquals("43", r.getNumber());
        r.setName("");
        assertEquals("", r.getName());
        assertEquals(new ArrayList<Stop>(), r.getStops());
        assertEquals(new ArrayList<RoutePattern>(), r.getPatterns());
    }

    @Test
    public void testAddPattern() {
        r.addPattern(rp);
        List<RoutePattern> patterns = r.getPatterns();
        assertEquals(1, patterns.size());
        r.addPattern(rp);
        List<RoutePattern> patterns1 = r.getPatterns();
        assertEquals(1, patterns1.size());
        r.addPattern(rp2);
        List<RoutePattern> patterns2 = r.getPatterns();
        assertEquals(2, patterns2.size());
    }

    @Test
    public void testAddStop() {
        r.addStop(s);
        List<Stop> stops = r.getStops();
        assertEquals(1, stops.size());
        r.addStop(s);
        List<Stop> stops1 = r.getStops();
        assertEquals(1, stops1.size());
        r.addStop(s2);
        List<Stop> stops2 = r.getStops();
        assertEquals(2, stops2.size());
    }

    @Test
    public void testRemoveStop() {
        r.addStop(s);
        r.removeStop(s);
        List<Stop> stops = r.getStops();
        assertEquals(0, stops.size());
        r.addStop(s);
        r.removeStop(s2);
        List<Stop> stop2 = r.getStops();
        assertEquals(1, stops.size());
    }

    @Test
    public void testGetPattern() {
        r.addPattern(rp);
        assertEquals(new RoutePattern("44 UBC", "Downtown", "North", r),
                r.getPattern("44 UBC", "Downtown", "North"));
        assertEquals(new RoutePattern("99 UBC", "UBC", "South", r),
                r.getPattern("99 UBC", "UBC", "South"));


    }

    @Test
    public void testGetPattern1() {
        r.addPattern(rp);
        assertEquals(rp,r.getPattern("44 UBC"));
        RoutePattern routePattern = new RoutePattern("99 UBC", " ", " ", r);
        assertEquals(routePattern, r.getPattern("99 UBC"));
    }


    @Test
    public void testHasStops() {
        r.addStop(s);
        assertTrue(r.hasStop(s));
        assertFalse(r.hasStop(s2));
    }

    @Test
    public void testToString() {
        assertEquals("Route 43", r.toString());
    }

    @Test
    public void testEquals() {
        assertTrue(r3.equals(r2));

    }
    @Test
    public void testIterator() {
        r.iterator();
    }

    @Test
    public void testHashCode() {
        assertEquals(1663, r.hashCode());
    }
}
