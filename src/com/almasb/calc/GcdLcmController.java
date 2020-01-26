package com.almasb.calc;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

public class GcdLcmController implements Initializable {
	
	@FXML private TextField expression = new TextField();
	@FXML private TextField gcdValue=new TextField();
	@FXML private TextField lcmValue=new TextField();
	
	
	@FXML
	public void calculate(ActionEvent event)
	{
			String str=new String(expression.getText());
			str=str.trim();
			
			gcdValue.setEditable(false);
			lcmValue.setEditable(false);
			
			ArrayList<Integer> list=new ArrayList<Integer>();
			int gcd,lcm;
			
			String  substr="";
		    
			
			try {
			
				for(int i=0;i<str.length();i++)
			
				{
					if(str.charAt(i)==' ' ) 
					{
						if(str.charAt(i+1)==' ') continue;
						list.add(Integer.parseInt(substr));
						substr=substr.replace(substr, "");
					}
					else substr+=str.charAt(i);
				}
			
				list.add(Integer.parseInt(substr));
			
				gcd=list.get(0);
				lcm=gcd;
			
			
				
				for(int i=1;i<list.size();i++)
				{
					 gcd=getGCD(list.get(i),gcd);
					 lcm=getLCM(list.get(i),lcm);
				}
			
				gcdValue.setText(String.format("%d",gcd));
				lcmValue.setText(String.format("%d",lcm));
				
			}
			
			catch(ArithmeticException e) {
				
				gcdValue.setText("Math Eror");
				lcmValue.setText("Math Eror");
			
			}
			
			catch(Exception e) {
				
				gcdValue.setText("Syntax Eror");
				lcmValue.setText("Syntax Eror");
			
			}
			
			
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
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
	
		gcdValue.setText("");
		lcmValue.setText("");
		expression.setText("");
		
	}
	
	
	public void exit(ActionEvent event) throws IOException {
		
		Platform.exit();
		System.exit(0);
		
	}
		
	public int getGCD(int a, int b)
	{
		if(a%b==0) return b;
		return getGCD(b,a%b);
	}
		
	public int getLCM(int a,int b)
	{
		 int gcd=getGCD(a,b); 
		 return a*b/gcd;
	}

	
	

	
}




