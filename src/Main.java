

import java.io.IOException;


 class Main {
    public static void main(String[]args) throws IOException {
        DistanceCalculator distanceCalculator = new DistanceCalculator();
        distanceCalculator.calculate_distance( 10 , 20, 30, 40 );
        APIserver apIserver = new APIserver();
        apIserver.launch();
}}
