package me.nadha.hostelmanagement;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.net.URL;

public class LoginController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMsgLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    public void loginButtonOnAction(ActionEvent event){

        if(usernameField.getText().isBlank()==false && passwordField.getText().isBlank()==false){
            //loginMsgLabel.setText("You try to login");
            validateLogin();
        }
        else {
            loginMsgLabel.setText("Please enter valid username and password");
        }
    }

    public void  cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM student WHERE username = '" + usernameField.getText() + "' AND password = '" + passwordField.getText() + "'";

        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery("VerifyLogin");

            while (queryResult.next()){

                if (queryResult.getInt(1) == 1){
                    loginMsgLabel.setText("Congratulations!");
                }else{
                    loginMsgLabel.setText("Invalid login! Try again!");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File brandingFile = new File("Images/dsh1.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);
    }
}