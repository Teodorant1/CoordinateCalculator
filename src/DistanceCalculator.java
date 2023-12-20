import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;


public class DistanceCalculator {

    public DistanceCalculator() {
    }


    public JSONArray getarray_within_Radius(JSONArray array1, int radius , double lat1 , double long1) {

        System.out.println("details are as follows");
        System.out.println(radius);
        System.out.println(lat1);
        System.out.println(long1);
        System.out.println(array1);

        JSONArray locations_within_radius = new JSONArray();

        for ( int i = 0 ; i<array1.length(); i++  ) {

        JSONObject object = (JSONObject) array1.get(i);
        double lat2 = object.getDouble("latitude");
        double long2 = object.getDouble("longitude");

        int distance = calculate_distance(lat1 , lat2 , long1 , long2);
        if ( distance <= radius ) {

            JSONObject jobplusdistance = new JSONObject();
            jobplusdistance.put("distance" , distance);
            object.put("distance" , distance);
            locations_within_radius.put(object);}
        }


        System.out.println(locations_within_radius);

        return locations_within_radius;
    }

    public int calculate_distance(double lat1,
                                  double lat2, double lon1,
                                  double lon2)
    {

        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        int i = (int) (c * r);
        System.out.println(i + " km");
        // calculate the result
        return (int) (c * r);
    }

    // driver code
    public  void test()    {
        double lat1 = 53.32055555555556;
        double lat2 = 53.31861111111111;
        double lon1 = -1.7297222222222221;
        double lon2 = -1.6997222222222223;
        System.out.println( calculate_distance(lat1, lat2,
                lon1, lon2) + " K.M");
    }

}
