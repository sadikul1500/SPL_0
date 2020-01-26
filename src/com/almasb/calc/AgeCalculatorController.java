package com.almasb.calc;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AgeCalculatorController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private TextField dateOfRequired=new TextField(),monthOfRequired=new TextField(),yearOfRequired=new TextField(),
	dateOfBirth=new TextField(),monthOfBirth=new TextField(),yearOfBirth=new TextField(),
	dayAge=new TextField(),monthAge=new TextField(),yearAge=new TextField();
	
	@FXML
	public void calculate(ActionEvent event) {
		
		try {	
		
		LocalDate birthOfDate = LocalDate.of(Integer.parseInt(yearOfBirth.getText()),
				Integer.parseInt(monthOfBirth.getText()),Integer.parseInt(dateOfBirth.getText()));
		
		LocalDate RequiredDate = LocalDate.of(Integer.parseInt(yearOfRequired.getText()),
				Integer.parseInt(monthOfRequired.getText()),Integer.parseInt(dateOfRequired.getText()));
		
		

		
		if(Period.between(birthOfDate, RequiredDate).getYears()<0 
				||Period.between(birthOfDate, RequiredDate).getMonths()<0
				||Period.between(birthOfDate, RequiredDate).getDays()<0) {
			
			
			yearAge.setText("Wrong");
			monthAge.setText("Wrong");
			dayAge.setText("Wrong");
			
			
		}
		
		
		else{
			
				yearAge.setText(String.format("%d",Period.between(birthOfDate, RequiredDate).getYears()));
				monthAge.setText(String.format("%d",Period.between(birthOfDate, RequiredDate).getMonths()));
				dayAge.setText(String.format("%d",Period.between(birthOfDate, RequiredDate).getDays()));
			
		}
		
		}
		
		catch(NumberFormatException e)
		{

			yearAge.setText("Wrong");
			monthAge.setText("Wrong");
			dayAge.setText("Wrong");
		}
		
		catch(Exception e) {
			
			yearAge.setText("Wrong");
			monthAge.setText("Wrong");
			dayAge.setText("Wrong");
		}

	}
	
	@FXML
	public void backToScientificCalculator(ActionEvent event) throws IOException {
		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
    	
    	Parent root = FXMLLoader.load(getClass().getResource("ScientificCalculator.fxml"));
        
        window.setScene(new Scene(root));
        window.setResizable(false);
        window.setTitle("Scientific Calculator");
        window.show();
	}
	
	@FXML
	public void clearText(ActionEvent event) throws IOException {
	
		dateOfRequired.setText("");
		monthOfRequired.setText("");
		yearOfRequired.setText("");
		
		dateOfBirth.setText("");
		monthOfBirth.setText("");
		yearOfBirth.setText("");
		
		dayAge.setText("");
		monthAge.setText("");
		yearAge.setText("");
		
		
	}
	
	
	public void exit(ActionEvent event) throws IOException {
		
		Platform.exit();
		System.exit(0);
		
	}

}







