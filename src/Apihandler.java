import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

public class Apihandler implements HttpHandler {
    public Apihandler() {
    }

    @Override
    public void handle(HttpExchange t) throws IOException {
        InputStream ios = t.getRequestBody();
        byte[] input = ios.readAllBytes();
        String inputString = new String(input, StandardCharsets.UTF_8);
     //   System.out.println( "inputstring " + inputString);
        String response = String.valueOf(handlerequest(inputString));

        t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        t.getResponseHeaders().add("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        t.getResponseHeaders().add("Access-Control-Allow-Credentials", "true");
        t.getResponseHeaders().add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
        t.sendResponseHeaders(200, response.getBytes().length);

        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();}
        public JSONArray handlerequest(String entrystring)  {
        JSONObject object1 = new JSONObject(entrystring);
        System.out.println(object1);
        //    System.out.println("lat:"+object1.get("lat").toString());
        //    System.out.println("lat:"+object1.getDouble("lat"));
        //    System.out.println(object1.get("lat"));
        //    System.out.println(object1.get("lat").getClass());
            System.out.println(object1.get("JobsArray").getClass());
            System.out.println(object1.get("JobsArray"));
            System.out.println(object1.get("radius").getClass());
       //   JSONArray jsonArray = new JSONArray(object1.get("JobsArray"));
       //   System.out.println(jsonArray.getClass());
       //   System.out.println(jsonArray);

        BigDecimal lat2;
        lat2 = (BigDecimal) object1.get("lat");

        BigDecimal long2;
        long2 = (BigDecimal) object1.get("long");

        double  lat1 = lat2.doubleValue();
        double  long1 = long2.doubleValue();

        int radius = object1.getInt("radius");
        JSONArray jobsArray = object1.getJSONArray("JobsArray");
        System.out.println(lat2);
        System.out.println(long1);
        System.out.println(radius);
        System.out.println(jobsArray);

        DistanceCalculator distanceCalculator = new DistanceCalculator();

        JSONArray theresponse = (distanceCalculator.getarray_within_Radius(jobsArray, radius, lat1, long1));
        System.out.println(theresponse);
     //  JSONObject palokobj = new JSONObject();
     //  palokobj.put("paloki" , "paloki1");
     //  JSONArray jsonArray = new JSONArray();
     //  jsonArray.put(palokobj);
     //  System.out.println(jsonArray);

        return theresponse;
//        return  jsonArray;
    }
}