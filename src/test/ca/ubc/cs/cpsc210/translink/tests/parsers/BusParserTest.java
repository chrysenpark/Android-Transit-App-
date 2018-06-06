package ca.ubc.cs.cpsc210.translink.tests.parsers;

import ca.ubc.cs.cpsc210.translink.model.Bus;
import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.model.StopManager;
import ca.ubc.cs.cpsc210.translink.model.exception.RouteException;
import ca.ubc.cs.cpsc210.translink.parsers.BusParser;
import ca.ubc.cs.cpsc210.translink.providers.FileDataProvider;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BusParserTest {
    @Test
    public void testBusLocationsParserNormal() throws JSONException, RouteException {
        Stop s = StopManager.getInstance().getStopWithNumber(51479);
        s.clearBuses();
        String data = "";


        try {

            data = new FileDataProvider("buslocations.json").dataSourceToString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't read the bus locations data");
        }


        s.addRoute(new Route("004"));
        s.addRoute(new Route("014"));

        BusParser.parseBuses(s, data);


        assertEquals(4, s.getBuses().size());
    }
}
