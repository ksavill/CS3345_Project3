package project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public class CityAdjacentList {
    // Declares a LinkedList of DestinationLinkedList objects called cityLinkedList
    LinkedList<DestinationLinkedList> cityLinkedList;

    // Constructor for CityAdjacentList that initializes the cityLinkedList and calls the getFlightDataFromFile method
    public CityAdjacentList(String fileLocation1, String inputMethod1, String pastedText1) {
        cityLinkedList = new LinkedList<>();
        getFlightDataFromFile(fileLocation1,inputMethod1,pastedText1);
    }

    // Method that returns the index of a given city in the cityLinkedList
    public int indexOf(String city) {
        for (int i = 0; i < cityLinkedList.size(); i++) {
            if (Objects.equals(cityLinkedList.get(i).origin, city)) {
                return i;
            }
        }
        // Returns -1 if the given city is not found in the cityLinkedList
        return -1;
    }

    // Method that adds a FlightData object to the cityLinkedList
    public void add(FlightData flightData) {
        int index = indexOf(flightData.origin);
        // If the given city is not found in the cityLinkedList, a new DestinationLinkedList is created and added to the cityLinkedList
        if (index == -1) {
            DestinationLinkedList destinationLinkedList = new DestinationLinkedList(flightData.origin);
            cityLinkedList.add(destinationLinkedList);
            index = cityLinkedList.size() - 1;
        }
        // The FlightData object is then added to the DestinationLinkedList of the given city
        cityLinkedList.get(index).add(flightData);
    }

    // Method that adds an array of FlightData objects to the cityLinkedList
    public void add(FlightData[] flightDataArray) {
        for (FlightData flightData : flightDataArray) {
            add(flightData);
        }
    }

    // Method that returns the DestinationLinkedList at a given index in the cityLinkedList
    public DestinationLinkedList getAt(int index) {
        return cityLinkedList.get(index);
    }

    // get flight data either from the input file or the texted pasted in the textarea.
    private void getFlightDataFromFile(String fileLocation1, String inputMethod1, String pastedText1) {
        if(inputMethod1.contains("Text File")) {
            try {
                File file = new File(fileLocation1);
                Scanner scanner = new Scanner(file);
                int rowCount = Integer.parseInt(scanner.nextLine());

                for (int i = 0; i < rowCount; i++) {
                    String[] stringArray = scanner.nextLine().split("\\|");
                    add(FlightData.get2FromStringArray(stringArray));
                }
            } catch (FileNotFoundException e) {}
        } else {
            Scanner scanner = new Scanner(pastedText1);
            int rowCount = Integer.parseInt(scanner.nextLine());
            
            for (int i=0; i < rowCount; i++) {
                String[] stringArray = scanner.nextLine().split("\\|");
                add(FlightData.get2FromStringArray(stringArray));
            }
        }
    }
}
