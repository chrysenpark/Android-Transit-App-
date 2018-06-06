package ca.ubc.cs.cpsc210.translink.parsers;

import ca.ubc.cs.cpsc210.translink.model.Bus;
import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.model.exception.RouteException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



// Parser for bus data
public class BusParser {

    /**
     * Parse buses from JSON response produced by TransLink query.  All parsed buses are
     * added to the given stop.  Bus location data that is missing any of the required
     * fields (RouteNo, Latitude, Longitude, Destination, RecordedTime) is silently
     * ignored and not added to stop. Bus that is on route that does not pass through
     * this stop is silently ignored and not added to stop.
     *
     * @param stop            stop to which parsed buses are to be added
     * @param jsonResponse    the JSON response produced by Translink
     * @throws JSONException  when:
     * <ul>
     *     <li>JSON response does not have expected format (JSON syntax problem)</li>
     *     <li>JSON response is not a JSON array</li>
     * </ul>
     */
    public static void parseBuses(Stop stop, String jsonResponse) throws JSONException {
        // TODO: implement this method

        JSONArray jsonArray = new JSONArray(jsonResponse);
        //List<Bus> listofBuses = new ArrayList<Bus>();

        for (int i = 0;i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            try {
                Bus bus = (parseOneBus(obj, stop));
                stop.addBus(bus);
            } catch (RouteException e) {
                continue;
            }
        }
    }

    private static Bus parseOneBus(JSONObject BusObject, Stop stop) throws JSONException {
        //route number, latitude, longitude, destination and recorded time
        String Destination = BusObject.getString("Destination");
        double Latitude = BusObject.getDouble("Latitude");
        double Longitude = BusObject.getDouble("Longitude");
        String RouteNo = BusObject.getString("RouteNo");
        String RecordedTime = BusObject.getString("RecordedTime");

        Route route = RouteManager.getInstance().getRouteWithNumber(RouteNo);
        route.addStop(stop);
        Bus newBus = new Bus(route, Latitude, Longitude, Destination, RecordedTime);


        return newBus;
    }
}
