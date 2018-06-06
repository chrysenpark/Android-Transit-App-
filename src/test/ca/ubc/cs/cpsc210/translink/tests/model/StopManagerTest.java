package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.model.StopManager;
import ca.ubc.cs.cpsc210.translink.model.exception.StopException;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Test the StopManager
 */
public class StopManagerTest {

    @BeforeEach
    public void setup() {
        StopManager.getInstance().clearStops();
    }

    @Test
    public void testBasic() {
        Stop s9999 = new Stop(9999, "My house", new LatLon(-49.2, 123.2));
        Stop r = StopManager.getInstance().getStopWithNumber(9999);
        assertEquals(s9999, r);
        assertEquals(null, StopManager.getInstance().getSelected());

        StopManager.getInstance().iterator();
    }

    @Test
    public void testGetStopWithNumber() {
        Stop s9999 = new Stop(9999, "My house", new LatLon(-49.2, 123.2));
        Stop s = StopManager.getInstance().getStopWithNumber
                (9999,"My house", new LatLon(-49.2, 123.2));
        assertEquals(s9999, s);
        assertEquals(s9999, StopManager.getInstance().getStopWithNumber(9999));
        assertEquals(s9999, StopManager.getInstance().getStopWithNumber(9999,"UBC", new LatLon(11,11)));
        assertEquals(1, StopManager.getInstance().getNumStops());

    }

    @Test
    public void testSetSelected() {
        Stop s9999 = new Stop(9999, "My house", new LatLon(-49.2, 123.2));
        try {
            StopManager.getInstance().setSelected(s9999);
            fail("should not reach this point");
        }
        catch (StopException e) {
            //expected
        }
    }
    @Test
    public void testSetSelected2() {
        Stop s9999 = new Stop(9999, "My house", new LatLon(-49.2, 123.2));
        Stop s = StopManager.getInstance().getStopWithNumber(9999);
        assertEquals(s9999, s);
        try {
            StopManager.getInstance().setSelected(s9999);
            assertEquals(s9999, StopManager.getInstance().getSelected());
        }
        catch (StopException e) {
            fail("StopException was called");
        }
        StopManager.getInstance().clearSelectedStop();
        assertEquals(null, StopManager.getInstance().getSelected());
    }

    @Test
    public void testFindNearestTo() {
        Stop s1 = new Stop(1, "My house", new LatLon(49.277536,-123.127368));
        Stop s2 = new Stop(2, "UBC", new LatLon(49.277536,11));
        Stop s3 = new Stop(3, "UBC", new LatLon(20,20));
        assertEquals(null, StopManager.getInstance().findNearestTo(new LatLon(10,10)));

        assertEquals(s1, StopManager.getInstance().getStopWithNumber(1,"help",
                new LatLon(49.277536, -123.1) ));

        assertEquals(s2, StopManager.getInstance().getStopWithNumber(2, "UBC",new LatLon(49.3,-123.12)));

        assertEquals(s3, StopManager.getInstance().getStopWithNumber(3, "Downtown",
                new LatLon(49.277536, -123.127368)));

        assertEquals(s3, StopManager.getInstance().findNearestTo(new LatLon(49.277536,-123.127368)));
    }


}
