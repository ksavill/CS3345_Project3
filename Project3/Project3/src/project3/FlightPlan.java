package project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class FlightPlan {
    enum SortBy {
        COST,
        TIME
    }

    String origin;
    String destination;
    SortBy sortBy;

    public FlightPlan(String origin, String destination, SortBy sortBy) {
        this.origin = origin;
        this.destination = destination;
        this.sortBy = sortBy;
    }

    @Override
    public String toString() {
        return "FlightPlan{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", sortBy=" + sortBy +
                '}';
    }

    public static FlightPlan getFromStringArray(String[] array) {
        String origin = array[0];
        String destination = array[1];
        SortBy sortBy = null;
        switch (array[2]) {
            case "T" : sortBy = SortBy.TIME;
            case "C" : sortBy = SortBy.COST;
            default : { }
        }
        return new FlightPlan(origin, destination, sortBy);
    }

    public static ArrayList<FlightPlan> getFromFile(String fileName) {
        ArrayList<FlightPlan> flightPlans = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            int rowCount = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < rowCount; i++) {
                String[] stringArray = scanner.nextLine().split("\\|");
                flightPlans.add(getFromStringArray(stringArray));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return flightPlans;
    }

    public ArrayList<FlightPath> getAllPaths(CityAdjacentList cityAdjacentList) {
        MyStack<FlightData> stack = new MyStack<>();
        ArrayList<FlightPath> flightPaths = new ArrayList<>();
        FlightPath flightPath = new FlightPath(origin);

        String city = origin;
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

    public void output(CityAdjacentList cityAdjacentList) {
        String sortByString = (sortBy == SortBy.COST) ? "Cost": "Time";
        System.out.println(origin + ", " + destination + " (" + sortByString + ")");

        ArrayList<FlightPath> flightPaths = getAllPaths(cityAdjacentList);
        if (!flightPaths.isEmpty()) {
            Comparator<FlightPath> comparator = (sortBy == SortBy.COST) ? FlightPath.byCost : FlightPath.byTime;
            flightPaths.sort(comparator);

            int size = Math.min(flightPaths.size(), 3);
            for (int i = 0; i < size; i++) {
                System.out.print("Path " + (i + 1) + ": ");
                flightPaths.get(i).println();
            }
        } else {
            System.out.println("No flight path can be created.");
        }
    }
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

    public static void main(String[] args) {
        ArrayList<FlightPlan> flightPlans = getFromFile("requested_flight_plans.txt");
        CityAdjacentList cityAdjacentList = new CityAdjacentList("flight_data.txt");
        for (int i = 0; i < flightPlans.size(); i++) {
            System.out.print("Flight " + (i + 1) + ": ");
            flightPlans.get(i).output(cityAdjacentList);
        }
    }
}
