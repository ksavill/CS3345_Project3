/*
Kevin Savill
12-4-2022
CS3345
Project 3 - Semester Project
 */
package project3;

import java.io.File;
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
        
        ToggleGroup inputMethodSelection = new ToggleGroup();
       
        RadioButton rb1 = new RadioButton("Text File");
        rb1.setToggleGroup(inputMethodSelection);
        rb1.setSelected(true);      
        //radio  button
        RadioButton rb2 = new RadioButton("Pasted Text");
        rb2.setToggleGroup(inputMethodSelection);        
        
        
        TextField textFileLocation = new TextField();
        // set the default value of the input textfield
        textFileLocation.setText("flight_data.txt");
        
        
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
        menuGrid.add(textFileLocation,1,2);
        
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
                String inputMethod;
                String textfileDirectory;
                String pastedText;
                String bookString;
                String results;
                
                // declare inputMethod using current radio selection from gui
                inputMethod = inputMethodSelection.getSelectedToggle().toString();
                // delcare textfileDirectory from filename typed in gui, by default this will be booklist.txt
                textfileDirectory = textFileLocation.getText();
                // get the typed in text from the textArea in gui
//                pastedText = bookPaste.getText();
                System.out.println("Variables initialized and declared, calling beginProcess function");
//                bookString = startFunctions(inputMethod, textfileDirectory, pastedText);
//                System.out.println(bookString);
                // in startFunctions, if there is an issue with getting the text from either the textArea or text file, "Error" will be returned as the string
                // this if statement, will set the result fields in the gui to "Error" and stop any further functions from being called.
//                if (bookString == "Error") {
//                    outputResults.setText(bookString);
//                    return;
//                }
                outputResults.setText("Imported");
                // update the import field in the gui to the list of books
                System.out.println("Going to pass imported list into book class to create book objects.");
                // pass the bookString to bookToAVL, this function will also handle creating the book objects, and then inserting to AVLTree
                // once done, any actions taken in the AVLTree for balancing will be returned as a long string with \n which will then be displayed to the results field in gui.
//                results = bookToAVL(bookString);
//                outputResults.setText(results);
                System.out.println("Main Functions complete.");
                
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
    public static String startFunctions(String inputMethod, String textfileDirectory, String pastedText) {
        // initalize bookstring. I will return this and then pass this string along to a book creator class to handle appending the lines to author, booktitle, and isbn properties respectively
        // this function is simply for getting the inputs from the gui and getting all 30 lines (although can be variable amount of lines)
        String bookString;
        bookString = "";
        // input validation and getting book list from the text file method
        if (inputMethod.contains("Text File")) {
            System.out.println("Input method is Text File.");
            if(textfileDirectory.length() < 1) {
                System.out.println("The input method you chose is empty.");
                bookString = "Error";
                return bookString;
            } else {
                try{
                    File bookFile = new File(textfileDirectory);
                    Scanner scanner = new Scanner(bookFile);
                    int i =0; // count lines
                    String x;
                    while (scanner.hasNextLine()) { // goes through all lines in text file
                       x = scanner.nextLine(); // content of line
                       if(i!=0){ // if this is not the first line being imported, append a breakline before the next element
                           bookString = bookString + "\n";
                       }
                       bookString = bookString +  x;
                       i++;
                    }  
                } catch(Exception e) {
                    System.out.println(e);
                    bookString = "Error";
                }
            }
        // input validation and getting book list from the pasted text in gui
        } else {
            System.out.println("Input Method is Pasted Text");
            if(pastedText.length() < 1) {
                System.out.println("The input method you chose is empty.");
                bookString = "Error";
                return bookString;
            } else {
                bookString = pastedText;
            }
        }
        return bookString;
    }
    
    public String bookToAVL(String bookString) {
        String results = "";
        // pass bookString to insertBooks function in Book class to be returned the array of book objects
//        results = Book.insertBooks(bookString);
        
        
        
        return results;
    }
    
}