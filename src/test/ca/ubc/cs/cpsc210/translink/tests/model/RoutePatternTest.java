package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.Route;

import ca.ubc.cs.cpsc210.translink.model.RoutePattern;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoutePatternTest {

    Route r;
    RoutePattern rp;

    @BeforeEach
    public void setup() {
        r = new Route("44");
        rp = new RoutePattern("44 UBC", "UBC", "South", r);
    }

    @Test
    public void testConstructor() {
        assertEquals("44 UBC", rp.getName());
        assertEquals("UBC", rp.getDestination());
        rp.setDestination("Downtown");
        assertEquals("Downtown", rp.getDestination());
        assertEquals("South", rp.getDirection());
        rp.setDirection("North");
        assertEquals("North", rp.getDirection());
        List<LatLon> path = new ArrayList<LatLon>();
        path.add(new LatLon(10,10));
        path.add(new LatLon(11,11));
        rp.setPath(path);
        assertEquals(path, rp.getPath());


    }


    @Test
    public void testEquals() {
        RoutePattern rp2 = new RoutePattern("44 UBC","ALMA", "North", r);
        assertTrue(rp.equals(rp2));

    }

    @Test
    public void testHashCode() {
        assertEquals(1537776054,rp.hashCode());
    }

}
