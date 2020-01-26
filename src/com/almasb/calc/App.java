package com.almasb.calc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	@Override
    public void start(Stage primaryStage) throws Exception {
       
    	Parent root = FXMLLoader.load(getClass().getResource("ScientificCalculator.fxml"));
        
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Scientific Calculator");
        primaryStage.show();
        
    }
    
	
	
    public static void main(String[] args) {
        launch(args);
    }
}