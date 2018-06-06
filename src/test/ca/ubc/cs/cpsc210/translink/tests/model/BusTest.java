package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.Bus;
import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BusTest {
    Bus b;
    Route r;

    @BeforeEach
    public void setup() {
        r = RouteManager.getInstance().getRouteWithNumber("43");
        b = new Bus(r,10,10, "home", "3:30pm");
    }


    @Test
    public void testConstructor() {
        assertEquals(r,b.getRoute());
        assertEquals( new LatLon(10,10), b.getLatLon());
        assertEquals("home", b.getDestination());
        assertEquals("3:30pm", b.getTime());

    }


}
