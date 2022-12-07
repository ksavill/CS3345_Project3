package project3;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;

public class FlightPath {

    // Create a comparator that compares two FlightPath objects by their cost.
    public static Comparator<FlightPath> byCost = Comparator.comparingDouble(path -> path.cost);
    
    // Create a comparator that compares two FlightPath objects by their time.
    public static Comparator<FlightPath> byTime = Comparator.comparingInt(path -> path.time);

    // The origin city of the flight path.
    String origin;

    // The cost of the flight path.
    float cost;

    // The time taken to complete the flight path.
    int time;

    // A stack of FlightData objects that represents the sequence of flights taken in the flight path.
    MyStack<FlightData> path;

    // A map that keeps track of the cities visited in the flight path.
    HashMap<String, Boolean> cities;

    // Constructor that initializes a FlightPath object with the given origin city.
    public FlightPath(String origin) {
        // Initialize the path stack and the cities map.
        path = new MyStack<>();
        cities = new HashMap<>();

        // Add the origin city to the map with the value "true" to indicate that it has been visited.
        cities.put(origin, Boolean.TRUE);

        // Set the origin city of the flight path.
        this.origin = origin;

        // Initialize the cost and time of the flight path to 0.
        cost = 0;
        time = 0;
    }

    // Push a FlightData object onto the path stack.
    public void push(FlightData flightData) {
        // Get the destination city of the flight.
        String destination = flightData.destination;

        // If the destination city is not in the map, add it with the value "false" to indicate that it has not been visited.
        if (!cities.containsKey(destination)) {
            cities.put(destination, Boolean.FALSE);
        }

        // If the destination city has not been visited, update the cost and time of the flight path and add the flight to the path stack.
        if (cities.get(destination) == Boolean.FALSE) {
            cost += flightData.cost;
            time += flightData.time;
            path.push(flightData);

            // Update the value in the map to indicate that the destination city has been visited.
            cities.replace(destination, Boolean.TRUE);
        }
    }

    // Pop a FlightData object from the path stack.
    public FlightData pop() {
        // Get the FlightData object at the top of the stack.
        FlightData flightData = path.pop();

        // Subtract the cost and time of the flight from the total cost and time of the flight path.
        cost -= flightData.cost;
        time -= flightData.time;

        // Update the value in the map to indicate that the destination city is no longer visited.
        cities.replace(flightData.destination, Boolean.FALSE);

        // Return the FlightData object.
        return flightData;
    }

    // Peek at the FlightData object at the top of the path stack.
    public FlightData peek() {
        return path.peek();
    }
    // Check whether the given city is in the map of visited cities.
    public boolean contains(String city) {
        return cities.getOrDefault(city, false);
    }

    // Create a copy of the current FlightPath object.
    public FlightPath copy() {
        // Create a new FlightPath object with the same origin city as the current object.
        FlightPath newFlightPath = new FlightPath(origin);

        // Push all the flights in the current path onto the new FlightPath object.
        for (FlightData flightData : path) {
            newFlightPath.push(flightData);
        }

        // Return the new FlightPath object.
        return newFlightPath;
    }

    // Check whether the path stack is empty.
    public boolean isEmpty() {
        return path.empty();
    }

    // Override the toString method to return a string representation of the FlightPath object.
    @Override
    public String toString() {
        return "FlightPath{"+"origin='" + origin + '\'' +", cost="+ cost+", time="+ time+", path="+ path+'}';
    }

    // Print the flight path to the console.
    public void println() {
        // Initialize a counter variable.
        int i = 0;

        // Print the origin city.
        System.out.print(origin);

        // Iterate over the flights in the path stack.
        for (FlightData flightData : path) {
            // If this is not the last flight in the path, print an arrow separator.
            if (i < path.size()) {
                System.out.print(" -> ");
            }

            // Print the destination city of the flight.
            System.out.print(flightData.destination);

            // Increment the counter variable.
            i += 1;
        }

        // Print the total time and cost of the flight path.
        System.out.printf(". Time: %d Cost: %.2f\n", time, cost);
    }
    // Return the flight path to the calling function(s).
    public String printlnToGUI() {
        String results = "";
        // Initialize a counter variable.
        int i = 0;

        // Print the origin city.
        results = origin;

        // Iterate over the flights in the path stack.
        for (FlightData flightData : path) {
            // If this is not the last flight in the path, print an arrow separator.
            if (i < path.size()) {
                results+=" -> ";
            }

            // Print the destination city of the flight.
            results+=flightData.destination;

            // Increment the counter variable.
            i += 1;
        }

        // Print the total time and cost of the flight path.
        results+= ". Time: "+time+" Cost: "+cost;
        return results;
    }

    // Print the flight path to the given PrintWriter object.
    public void printlnToFile(PrintWriter printWriter) {
        // Print the origin city to the PrintWriter object.
        printWriter.print(origin);

        // Initialize a counter variable.
        int i = 0;

        // Iterate over the flights in the path stack.
        for (FlightData flightData : path) {
            // If this is not the last flight in the path, print an arrow separator to the PrintWriter object.
            if (i < path.size()) {
                printWriter.print(" -> ");
            }

            // Print the destination city of the flight to the PrintWriter object.
            printWriter.print(flightData.destination);

            // Increment the counter variable.
            i += 1;
        }

        // Print the total time and cost of the flight path to the PrintWriter object.
        printWriter.printf(". Time: %d Cost: %.2f\n", time, cost);
    }
}
