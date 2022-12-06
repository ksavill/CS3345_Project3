package project3;

public class FlightData {
    String origin;
    String destination;
    float cost;
    int time;

    public FlightData(String origin, String destination, float cost, int time) {
        this.origin = origin;
        this.destination = destination;
        this.cost = cost;
        this.time = time;
    }

    @Override
    public String toString() {
        return "FlightData{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", cost=" + cost +
                ", time=" + time +
                '}';
    }

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
