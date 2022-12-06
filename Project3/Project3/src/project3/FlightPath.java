package project3;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;

public class FlightPath {
    public static Comparator<FlightPath> byCost = Comparator.comparingDouble(path -> path.cost);
    public static Comparator<FlightPath> byTime = Comparator.comparingInt(path -> path.time);
    
    String origin;
    float cost;
    int time;
    MyStack<FlightData> path;
    HashMap<String, Boolean> cities;

    public FlightPath(String origin) {
        path = new MyStack<>();
        cities = new HashMap<>();
        cities.put(origin, Boolean.TRUE);
        this.origin = origin;
        cost = 0;
        time = 0;
    }

    public void push(FlightData flightData) {
        String destination = flightData.destination;
        if (!cities.containsKey(destination)) {
            cities.put(destination, Boolean.FALSE);
        }
        if (cities.get(destination) == Boolean.FALSE) {
            cost += flightData.cost;
            time += flightData.time;
            path.push(flightData);
            cities.replace(destination, Boolean.TRUE);
        }
    }

    public FlightData pop() {
        FlightData flightData = path.pop();
        cost -= flightData.cost;
        time -= flightData.time;
        cities.replace(flightData.destination, Boolean.FALSE);
        return flightData;
    }

    public FlightData peek() {
        return path.peek();
    }

    public boolean contains(String city) {
        return cities.getOrDefault(city, false);
    }

    public FlightPath copy() {
        FlightPath newFlightPath = new FlightPath(origin);
        for (FlightData flightData : path) {
            newFlightPath.push(flightData);
        }
        return newFlightPath;
    }

    public boolean isEmpty() {
        return path.empty();
    }

    @Override
    public String toString() {
        return "FlightPath{"+"origin='" + origin + '\'' +", cost="+ cost+", time="+ time+", path="+ path+'}';
    }

    public void println() {
        int i = 0;
        System.out.print(origin);

        for (FlightData flightData : path) {
            if (i < path.size()) {
                System.out.print(" -> ");
            }
            System.out.print(flightData.destination);
            i += 1;
        }
        System.out.printf(". Time: %d Cost: %.2f\n", time, cost);
    }
    
    public void printlnToFile(PrintWriter printWriter) {
        printWriter.print(origin);
        int i = 0;
        for (FlightData flightData : path) {
            if (i < path.size()) {
                printWriter.print(" -> ");
            }
            printWriter.print(flightData.destination);
            i += 1;
        }
        printWriter.printf(". Time: %d Cost: %.2f\n", time, cost);
    }
}
