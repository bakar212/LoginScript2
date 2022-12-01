package com.example.loginscript2;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class fxmlController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label welcomeText, scoreLabel;
    @FXML
    private Text message;
    @FXML
    private TextField user1;
    @FXML
    private PasswordField pass1;

    @FXML
    private Button quizButton;
    HashMap<String, String> database = new HashMap<>();
    private int attempts = 4;//number of attemps allowed to login

    private int scoreCounter = 0;//score counter for quiz




    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void clicked() throws IOException {
        if(user1.getLength() > 0 && pass1.getLength() > 0 && attempts > 0){//ensures text fields and password fields are not empty
            //user still is allowed to input password
            executeB(user1.getText().toLowerCase(), pass1.getText());

        }else{
            user1.setDisable(true);
            pass1.setDisable(true);
            message.setText("THIS ACCOUNT IS NOW LOCKED!");
        }


    }

    @FXML
    protected void regClicked(){//register button clicked, to add user details to database

        if(user1.getLength() > 0 && pass1.getLength() > 0 && attempts > 0){//ensures text fields and password fields are not empty
            //user still is allowed to input password
            database.put(user1.getText(), pass1.getText());//adds details to hashmap dataset
            message.setText("SUCCESSFULLY REGISTERED USER DETAILS!");
        }



    }

    @FXML
    protected void switch2quiz() throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene3.fxml"));//loading
        //stage = (Stage) ((Node)event.getSource()).getScene().getWindow();//the second screen
        stage = (Stage) (quizButton.getScene().getWindow());
        scene = new Scene(root);//or (scene)
        stage.setScene(scene);//since the user successfully
        stage.setTitle("Quiz");
        stage.show();//logged in to the program

    }

    public int executeB(String login, String password) throws IOException {

        database.put("chipmunks69", "Teamaqua12");//adding 4 entries for username and password
        database.put("cboy9", "fatboySlim919");//to the hashmap

        if(database.containsKey(login) && database.get(login).equals(password)) {//first condition checks whether the login
            message.setText("Access Granted!");//input exists in our database, and then whether the password input matches
            //with the value in our database
            root = FXMLLoader.load(getClass().getResource("scene2.fxml"));//loading
            //stage = (Stage) ((Node)event.getSource()).getScene().getWindow();//the second screen
            stage = new Stage();
            scene = new Scene(root);//or (scene)
            stage.setScene(scene);//since the user successfully
            stage.setTitle("Customer Policy Information");
            stage.show();//logged in to the program

            return 1;//code value to let system know the outcome, 1 = success
            //0 = failure
        }
        else if(database.containsKey(login) && !database.get(login).equals(password)) {//second condition checks whether the login
            //input exists in our database, however the password input  does not match
            //with the value in our database
            attempts--;
            message.setText("Error! The Password is incorrect\n"+"ATTEMPTS REMAINING: "+attempts);


            return 0;
        }
        else {//for all other conditions
            message.setText("Error the username does not exist!");
            return 0;
        }
    }
    @FXML
    protected void add3Points(){//adds 3 to the scoreCounter variable for each correct answer in the quiz
        scoreCounter = scoreCounter + 3;
    }
    @FXML
    protected void add5Points(){//adds 5 to the scoreCounter variable for each correct answer in the quiz
        scoreCounter = scoreCounter + 5;
    }
    @FXML
    protected void add7Points(){//adds 7 to the scoreCounter variable for each correct answer in the quiz
        scoreCounter = scoreCounter + 7;
    }
    @FXML
    protected void displayScore(){
        scoreLabel.setText("SCORE:"+scoreCounter+"/26");
    }
}