package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.*;
import ca.ubc.cs.cpsc210.translink.model.exception.RouteException;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class StopTest {

    Route r;
    Route r2;
    Route r3;
    Stop s;
    Stop s2;
    LatLon ll;
    Arrival a;
    Arrival a2;
    Bus b;

    @BeforeEach
    public void setup() {

        ll = new LatLon(10,10 );
        s = new Stop(1, "Blanca", ll);
        s2 = new Stop(2, "Alma", new LatLon(15,15));
        r = new Route("43");
        r2 = new Route("44");
        r3 = new Route("44");
        a = new Arrival(23, "Home", r);
        a2 = new Arrival(10, "UBC", r);
        b = new Bus(r,11,20, "home", "3:30pm");
    }

    @Test
    public void testConstructor() {
        assertEquals(1, s.getNumber());
        assertEquals("Blanca", s.getName());
        assertEquals(ll, s.getLocn());

    }

    @Test
    public void testAddRoutes() {
        Set<Route> routes = s.getRoutes();
        assertEquals(0,routes.size());
        s.addRoute(r);
        assertEquals(1, routes.size());
        s.addRoute(r);
        assertEquals(1, routes.size());


    }

    @Test
    public void testRemoveRoute() {
        Set<Route> routes = s.getRoutes();
        s.addRoute(r);
        assertEquals(1, routes.size());
        s.removeRoute(r);
        assertEquals(0, routes.size());
    }

    @Test
    public void testOnRoute() {
        r.addStop(s);
        assertTrue(s.onRoute(r));
        assertFalse(s.onRoute(r2));
    }

    @Test
    public void testAddArrival() {
        List<Arrival> arrivals = s.getArrivals();
        s.addArrival(a);
       assertEquals(1,arrivals.size());
       s.addArrival(a2);
       assertEquals(2, arrivals.size());
       assertEquals(0,arrivals.indexOf(a2));
       assertEquals(1, arrivals.indexOf(a));
       s.clearArrivals();
       assertEquals(0, arrivals.size());

    }

    @Test
    public void testAddBus() {
        r.addStop(s);
        List<Bus> buses = s.getBuses();
        try {
            s.addBus(b);
            assertEquals(1, buses.size());
        } catch (RouteException e) {
            fail("RouteException was called");
        }
        s.clearBuses();
       assertEquals(0, buses.size());

    }
    @Test
    public void testAddBus2() {
        List<Bus> buses = s.getBuses();
        try {
            s.addBus(b);
            fail("should not get here");
        } catch (RouteException e) {
            //expected
        }
    }

    @Test
    public void testEquals() {
        Stop stop = new Stop(1,"Burrad", ll);
        assertTrue(s.equals(stop));
        assertFalse(s.equals(s2));
    }

    @Test
    public void testHashCode() {
        assertEquals(1,s.hashCode());
    }

    @Test
    public void testIterator() {
        s.iterator();
    }
    @Test
    public void testSetMethods() {
        s.setName("HI");
        assertEquals("HI", s.getName());
        s.setLocn(new LatLon(15, 15));
        assertEquals(new LatLon(15,15), s.getLocn());
    }

}
