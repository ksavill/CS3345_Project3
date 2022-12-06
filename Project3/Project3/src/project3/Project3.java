/*
Kevin Savill
12-4-2022
CS3345
Project 3 - Semester Project
 */
package project3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Project3 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        //initialize the GridPane and the horizontal and veritcal gap components
        GridPane menuGrid = new GridPane();
        menuGrid.setHgap(10);
        menuGrid.setVgap(12);
        
        // Main two labels that will go at top of GUI
        Label flightDataLabel = new Label("---Flight Data---");
        Label requestedPlansLabel = new Label("---Requested Flight Plans---");
        
        // repeat labels such as "Textfile" and "Paste text content here"
        Label inputFileNameLabel = new Label("Textfile directory: ");
        Label pasteHereLabel = new Label ("Paste text content here: ");
        Label inputFileNameLabel2 = new Label("Textfile directory: ");
        Label pasteHereLabel2 = new Label ("Paste text content here: ");
        Label inputMethodLabel = new Label("Input Method: ");
        Label inputMethodLabel2 = new Label("Input Method: ");
        
        //input fields for Flight Data
        
        ToggleGroup inputMethodSelection1 = new ToggleGroup();
       
        RadioButton rb1 = new RadioButton("Text File");
        rb1.setToggleGroup(inputMethodSelection1);
        rb1.setSelected(true);      
        //radio  button
        RadioButton rb2 = new RadioButton("Pasted Text");
        rb2.setToggleGroup(inputMethodSelection1);        
        
        
        TextField textFileLocation1 = new TextField();
        // set the default value of the input textfield
        textFileLocation1.setText("flight_data.txt");
        
        
        TextArea flightDataTextArea = new TextArea();
        
        
        
        
        //input fields for Requested Flight Plans
        ToggleGroup inputMethodSelection2 = new ToggleGroup();
       
        RadioButton rb1_2 = new RadioButton("Text File");
        rb1_2.setToggleGroup(inputMethodSelection2);
        rb1_2.setSelected(true);      
        //radio  button
        RadioButton rb2_2 = new RadioButton("Pasted Text");
        rb2_2.setToggleGroup(inputMethodSelection2);        
        
        TextField textFileLocation2 = new TextField();
        // set the default value of the input textfield
        textFileLocation2.setText("requested_flight_plans.txt");
        
        TextArea requestFlightPlanTextArea = new TextArea();
        
        // button for starting operations
        Label startButtonLabel = new Label("Start Operations: ");
        Button startButton = new Button("Start");

        // result gui display items
        Label outputResultsLabel = new Label("Results: ");
        Label outputResults = new Label("");
        
        
        
        //output options
        Label outputFileNameLabel = new Label("Output Filename: ");
        TextField outputFileNameTextField = new TextField();
        outputFileNameTextField.setText("output.txt");
        
        
        
        
        //create Vertical box for algorithm selection radio buttons
        VBox radioButtons1 = new VBox();
        radioButtons1.getChildren().addAll(rb1,rb2);
        
        VBox radioButtons2 = new VBox();
        radioButtons2.getChildren().addAll(rb1_2,rb2_2);

        
        
        //(x,y) for grid arrangement. Can use this to arrange the radio buttons and other elements
        //contents for getting input type
        
        // input method radiobutton group options (Text File or Pasted Text)
        //row 0
        menuGrid.add(flightDataLabel,0,0);
        menuGrid.add(requestedPlansLabel,3,0);
        
        //row 1 
        menuGrid.add(inputMethodLabel,0,1);
        menuGrid.add(radioButtons1,1,1);
        
        menuGrid.add(inputMethodLabel2,3,1);
        menuGrid.add(radioButtons2,4,1);
        
        //row 2
        //content for inputting textfile location
        menuGrid.add(inputFileNameLabel,0,2);
        menuGrid.add(textFileLocation1,1,2);
        
        menuGrid.add(inputFileNameLabel2,3,2);
        menuGrid.add(textFileLocation2,4,2);
        
        //row 3
        //content for inputting text content
        menuGrid.add(pasteHereLabel,0,3);
        menuGrid.add(flightDataTextArea,1,3);
        
        menuGrid.add(pasteHereLabel2,3,3);
        menuGrid.add(requestFlightPlanTextArea,4,3);
        //row 4
        // output textfile settings
        menuGrid.add(outputFileNameLabel,0,4);
        menuGrid.add(outputFileNameTextField,1,4);
        //row 5
        //start gui items
        menuGrid.add(startButtonLabel,0,5);
        menuGrid.add(startButton,1,5);
        //row 6 and 7
        //experiement results
        menuGrid.add(outputResultsLabel,0,6);
        menuGrid.add(outputResults,0,7);
        
        Scene scene = new Scene(menuGrid, 1300, 650);
        
        primaryStage.setTitle("Project 3 - Kevin Savill");
        primaryStage.setScene(scene);
        primaryStage.show();
        

        // event listender for the start action button
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // initialize needed variables
                // variable suffix 1 = FlightPlans
                // variable suffix 2 = Requested Flight Data
                String inputMethod1;
                String inputMethod2;
                String fileLocation1;
                String fileLocation2;
                String pastedText1;
                String pastedText2;
                String outputName;
                String results;
                
                //reset outputResult
                outputResults.setText("");
                
                // declare inputMethod's using current radio selection from JavaFX GUI.
                inputMethod1 = inputMethodSelection1.getSelectedToggle().toString();
                inputMethod2 = inputMethodSelection2.getSelectedToggle().toString();
                
                // declare fileLocation variables
                fileLocation1 = textFileLocation1.getText();
                fileLocation2 = textFileLocation2.getText();
                
                // declare pastedText variables
                pastedText1 = flightDataTextArea.getText();
                pastedText2 = requestFlightPlanTextArea.getText();
                
                // declare output filename variable
                outputName = outputFileNameTextField.getText();
                
                // printing variables for debugging
                System.out.println("Variables: ");
                System.out.println("inputMethod1 : "+inputMethod1);
                System.out.println("inputMethod2 : "+inputMethod2);
                System.out.println("fileLocation1 : "+fileLocation1);
                System.out.println("fileLocation2 : "+fileLocation2);
                System.out.println("pastedText1 : "+pastedText1);
                System.out.println("pastedText2 : "+pastedText2);
                System.out.println("outputName : "+outputName);
                
                System.out.println("Variables initialized and declared, calling beginProcess function");
                outputResults.setText("Variables Initialized and Declared.");
                
                // pass declared variables to startFunction.
                results = startFunctions(inputMethod1,inputMethod2,fileLocation1,fileLocation2,pastedText1,pastedText2,outputName);
                // check if the result was error and state that in the gui.
                // Note: this could be problemtatic if somehow a city is named "Error" and throughts a false positive for this check, but in this case
                // I will assume that is extremely unlikely.
                if(results.contains("Error")) {
                    System.out.println(results);
                    outputResults.setText(results);
                    return;
                }
                
                System.out.println("Main Functions complete.");
                outputResults.setText("Completed.");
                
            }
        });
    }
    
    public static void main(String[] args) {
        launch(args);
        
    }
    
    public static String startFunctions(String inputMethod1, String inputMethod2, String fileLocation1, String fileLocation2, String pastedText1, String pastedText2, String outputName) {
        try {
            CityAdjacentList cityAdjacentList = new CityAdjacentList(fileLocation1);
            ArrayList<FlightPlan> flightPlans = FlightPlan.getFromFile(fileLocation2);
            for (int i = 0; i < flightPlans.size(); i++) {
                System.out.print("Flight " + (i + 1) + ": ");
                flightPlans.get(i).output(cityAdjacentList);
            }

            try {
                FileWriter fileWriter = new FileWriter(outputName, false);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(bufferedWriter);
                for (int i = 0; i < flightPlans.size(); i++) {
                    printWriter.print("Flight " + (i + 1) + ": ");
                    flightPlans.get(i).outputToFile(cityAdjacentList, printWriter);
                }
                printWriter.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                return "Error - Output";
            }
        } catch(Exception e) {
            System.out.println();
            return "Error - CityAdjacent or flightPlans";
        }
        
        
        

        
        return "";
    }    
}