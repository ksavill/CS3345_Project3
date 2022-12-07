package project3;

// Represents flight data, including the origin and destination cities, cost, and duration.
public class FlightData {
    String origin; // The origin city.
    String destination; // The destination city.
    float cost; // The cost of the flight.
    int time; // The duration of the flight in minutes.

    // Constructs a new FlightData instance with the specified origin, destination, cost, and time.
    public FlightData(String origin, String destination, float cost, int time) {
        this.origin = origin;
        this.destination = destination;
        this.cost = cost;
        this.time = time;
    }

    // Returns a string representation of the FlightData instance.
    @Override
    public String toString() {
        return "FlightData{"+"origin='"+origin +'\''+", destination='"+destination+'\''+", cost=" + cost +", time=" + time +'}';
    }

    // Converts a string array containing flight data into a FlightData array with two elements,
    // representing flights in both directions (origin to destination and destination to origin).
    public static FlightData[] get2FromStringArray(String[] array) {
        String city1 = array[0];
        String city2 = array[1];
        float cost = Float.parseFloat(array[2]);
        int time = Integer.parseInt(array[3]);

        return new FlightData[] {
            new FlightData(city1, city2, cost, time),
            new FlightData(city2, city1, cost, time)
        };
    }
}