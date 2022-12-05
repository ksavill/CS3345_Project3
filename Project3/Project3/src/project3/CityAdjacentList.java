package project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public class CityAdjacentList {
    LinkedList<DestinationLinkedList> cityLinkedList;

    public CityAdjacentList(String fileName) {
        cityLinkedList = new LinkedList<>();
        getFlightDataFromFile(fileName);
    }

    public int indexOf(String city) {
        for (int i = 0; i < cityLinkedList.size(); i++) {
            if (Objects.equals(cityLinkedList.get(i).origin, city)) {
                return i;
            }
        }
        return -1;
    }

    public void add(FlightData flightData) {
        int index = indexOf(flightData.origin);
        if (index == -1) {
            DestinationLinkedList destinationLinkedList = new DestinationLinkedList(flightData.origin);
            cityLinkedList.add(destinationLinkedList);
            index = cityLinkedList.size() - 1;
        }
        cityLinkedList.get(index).add(flightData);
    }
    public void add(FlightData[] flightDataArray) {
        for (FlightData flightData : flightDataArray) {
            add(flightData);
        }
    }

    public DestinationLinkedList getAt(int index) {
        return cityLinkedList.get(index);
    }

    private void getFlightDataFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            int rowCount = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < rowCount; i++) {
                String[] stringArray = scanner.nextLine().split("\\|");
                add(FlightData.get2FromStringArray(stringArray));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
