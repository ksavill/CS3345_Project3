package project3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String flightDataFileName = "flight_data.txt";
        String requestedFlightPlansFileName = "requested_flight_plans.txt";
        String outputFileName = "output.txt";

        ArrayList<FlightPlan> flightPlans = FlightPlan.getFromFile(requestedFlightPlansFileName);
        CityAdjacentList cityAdjacentList = new CityAdjacentList(flightDataFileName);

        for (int i = 0; i < flightPlans.size(); i++) {
            System.out.print("Flight " + (i + 1) + ": ");
            flightPlans.get(i).output(cityAdjacentList);
        }

        try {
            FileWriter fileWriter = new FileWriter(outputFileName, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            for (int i = 0; i < flightPlans.size(); i++) {
                printWriter.print("Flight " + (i + 1) + ": ");
                flightPlans.get(i).outputToFile(cityAdjacentList, printWriter);
            }
            printWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
