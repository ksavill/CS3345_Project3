package project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class FlightPlan {
    // Enum for sorting criteria
    enum SortBy {
        COST,
        TIME
    }

    // Fields for origin, destination, and sorting criteria
    String origin;
    String destination;
    SortBy sortBy;

    // Constructor to initialize fields
    public FlightPlan(String origin, String destination, SortBy sortBy) {
        this.origin = origin;
        this.destination = destination;
        this.sortBy = sortBy;
    }

    @Override
    public String toString() {
        return "FlightPlan{origin="+origin+"'\', destination='"+destination+"'\', sortBy="+sortBy+'}';
    }

    // Creates a FlightPlan object from a string array
    public static FlightPlan getFromStringArray(String[] array) {
        String origin = array[0];
        String destination = array[1];
        SortBy sortBy = null;
        if("C".equals(array[2])) {
            sortBy = SortBy.COST;
        } else {
            sortBy = SortBy.TIME;
        }
        return new FlightPlan(origin, destination, sortBy);
    }

    // Creates a list of FlightPlan objects from a file or pasted text
    public static ArrayList<FlightPlan> getFromFile(String fileLocation2,String inputMethod2,String pastedText2) {
        ArrayList<FlightPlan> flightPlans = new ArrayList<>();
        if(inputMethod2.contains("Text File")) {
            try {
                File file = new File(fileLocation2);
                Scanner scanner = new Scanner(file);
                int rowCount = Integer.parseInt(scanner.nextLine());

                for (int i = 0; i < rowCount; i++) {
                    String[] stringArray = scanner.nextLine().split("\\|");
                    flightPlans.add(getFromStringArray(stringArray));
                }
            } catch (Exception e) {} 
        } else {
            Scanner scanner = new Scanner(pastedText2);
            int rowCount = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < rowCount; i++) {
                String[] stringArray = scanner.nextLine().split("\\|");
                flightPlans.add(getFromStringArray(stringArray));
            }
        }
        return flightPlans;
    }

    // Returns all possible flight paths from the origin to the destination
    public ArrayList<FlightPath> getAllPaths(CityAdjacentList cityAdjacentList) {
        // initialize variables
        MyStack<FlightData> stack = new MyStack<>();
        ArrayList<FlightPath> flightPaths = new ArrayList<>();
        FlightPath flightPath = new FlightPath(origin);

        String city = origin;
        //loop
        while (true) {
            int index = cityAdjacentList.indexOf(city);
            MyLinkedList<FlightData> flightDataLinkedList = cityAdjacentList.getAt(index).flightDataLinkedList;
            for (FlightData flightData : flightDataLinkedList) {
                if (flightPath.contains(flightData.destination)) {
                    continue;
                }
                if (Objects.equals(flightData.destination, destination)) {
                    flightPath.push(flightData);
                    flightPaths.add(flightPath.copy());
                    flightPath.pop();
                    continue;
                }
                stack.push(flightData);
            }

            while (!stack.empty() && !flightPath.isEmpty() && stack.peek() == flightPath.peek()) {
                stack.pop();
                flightPath.pop();
            }

            if (stack.empty()) {
                break;
            }
            flightPath.push(stack.peek());
            city = stack.peek().destination;
        }
        return flightPaths;
    }
    // outputs to console and returns as a string
    public String output(CityAdjacentList cityAdjacentList) {
        String results = "";
        String sortByString = (sortBy == SortBy.COST) ? "Cost": "Time";
        System.out.println(origin + ", " + destination + " (" + sortByString + ")");
        results+=origin + ", " + destination + " (" + sortByString + ")\n";

        ArrayList<FlightPath> flightPaths = getAllPaths(cityAdjacentList);
        if (!flightPaths.isEmpty()) {
            Comparator<FlightPath> comparator = (sortBy == SortBy.COST) ? FlightPath.byCost : FlightPath.byTime;
            flightPaths.sort(comparator);

            int size = Math.min(flightPaths.size(), 3);
            for (int i = 0; i < size; i++) {
                results += "Path " + (i+1) + ": ";
                System.out.print("Path " + (i + 1) + ": ");
                results += flightPaths.get(i).printlnToGUI() + "\n";
                flightPaths.get(i).println();   
            }
        } else {
            System.out.println("No flight path can be created.");
            return "No flight path can be created.\n";
        }
        return results;
    }
    // outputs the path to target filename
    public void outputToFile(CityAdjacentList cityAdjacentList, PrintWriter printWriter) {
        String sortByString = (sortBy == SortBy.COST) ? "Cost": "Time";
        printWriter.println(origin + ", " + destination + " (" + sortByString + ")");

        ArrayList<FlightPath> flightPaths = getAllPaths(cityAdjacentList);

        if (!flightPaths.isEmpty()) {
            Comparator<FlightPath> comparator = (sortBy == SortBy.COST) ? FlightPath.byCost : FlightPath.byTime;
            flightPaths.sort(comparator);

            int size = Math.min(flightPaths.size(), 3);
            for (int i = 0; i < size; i++) {
                printWriter.print("Path " + (i + 1) + ": ");
                flightPaths.get(i).printlnToFile(printWriter);
            }
        } else {
            printWriter.println("No flight path can be created.");
        }
    }

    
}
