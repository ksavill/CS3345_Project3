package project3;

public class DestinationLinkedList {
    String origin;
    MyLinkedList<FlightData> flightDataLinkedList;

    public DestinationLinkedList(String origin) {
        this.origin = origin;
        flightDataLinkedList = new MyLinkedList<>();
    }

    @Override
    public String toString() {
        return "DestinationLinkedList{"+"origin='" + origin + '\''+", flightDataLinkedList=" + flightDataLinkedList +'}';
    }

    public void add(FlightData flightData) {
        flightDataLinkedList.add(flightData);
    }
}
