package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test the RouteManager
 */
public class RouteManagerTest {
    RouteManager r;


    @BeforeEach
    public void setup() {
        RouteManager.getInstance().clearRoutes();


    }

    @Test
    public void testBasic() {
        Route r43 = new Route("43");
        Route r = RouteManager.getInstance().getRouteWithNumber("43");
        assertEquals(r43, r);
        RouteManager.getInstance().iterator();

    }

    @Test
    public void testGetRouteWithNumber() {
        Route r43 = new Route("43");
        r43.setName("44 UBC");
        Route r44 = new Route("44");
        r43.setName("Downtown");
        Route r = RouteManager.getInstance().getRouteWithNumber("43","44 UBC");
        assertEquals(r43,r);
        assertEquals(1, RouteManager.getInstance().getNumRoutes());
        Route r2 = RouteManager.getInstance().getRouteWithNumber("44", "Downtown");
        assertEquals(r44, r2);
        assertEquals(2, RouteManager.getInstance().getNumRoutes());
    }




}
