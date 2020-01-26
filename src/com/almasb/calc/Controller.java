package com.almasb.calc;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

import com.almasb.calc.Model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller implements Initializable{
	
	private Stack<Content> storage = new Stack<Content>();
	private Stack<Content> tempStorage = new Stack<Content>();
	public Content newContent = new Content();
	public Content replay = new Content();
	FileStore fileStore;
	
    @FXML
    private TextField equation=new TextField();
    @FXML
    private TextField result=new TextField();
     
    private String operator = "";
    private boolean start = true;

    private Model model = new Model();
    public String value=""; 
  
    
    public Controller(){
		
		//equation.setEditable(false);
		//result.setEditable(false);
		fileStore = new FileStore();
			
		String eqn,res;
		eqn = fileStore.readEquation();
		try {
			eqn = fileStore.readEquation();
			res = fileStore.readResult();
        	while(!eqn.equals(" ")) {
			
				storage.push(new Content(eqn,res));
				eqn = fileStore.readEquation();
				res = fileStore.readResult();
        	}
		
		} catch (Exception e) {}
					
				
		
	}
    
    
    @FXML
    private void processNumpad(ActionEvent event) {
    	
    	//equation.setEditable(true);
        
    	
    	while(!tempStorage.isEmpty()) {
			
			storage.push(tempStorage.pop());
		
		}
    	
    	
    	result.setText("");
    	
    	if (start) {
        	equation.setText("");
            start = false;
        }
        
        
        
        if((
        		((Button)event.getSource()).getText().equals("e")
        		||((Button)event.getSource()).getText().equals("π")
        		)
        		&&  !value.equals("")) {
        	
        	if(value.charAt(value.length()-1)=='+'||
        			value.charAt(value.length()-1)=='-'||
        			value.charAt(value.length()-1)=='*'||
        			value.charAt(value.length()-1)=='/'||
        			value.charAt(value.length()-1)=='^'||
        			value.charAt(value.length()-1)=='(') {
        		
       
        		value += ((Button)event.getSource()).getText();
        	}
        	
        	
        	
        	else
        		 value+='*'+((Button)event.getSource()).getText();
        		
        }
        
        
        
        
        else if((
        		((Button)event.getSource()).getText().equals("(")) &&  !value.equals("")) {
        	
        	if(value.charAt(value.length()-1)=='+'||
        			value.charAt(value.length()-1)=='-'||
        			value.charAt(value.length()-1)=='*'||
        			value.charAt(value.length()-1)=='/'||
        			value.charAt(value.length()-1)=='^'||
        			value.charAt(value.length()-1)=='(') {
        		
       
        		value += ((Button)event.getSource()).getText();
        	}
        	
        	
        	
        	else
        		 value+='*'+((Button)event.getSource()).getText();
        		
        }
     
        
        else {
        
        	value += ((Button)event.getSource()).getText();
        }
        	equation.setText(value);
        	
        //	equation.setEditable(false);
        
    }
    

    @FXML
    private void processOperator(ActionEvent event) {
    	
    	//equation.setEditable(true);
        
    	while(!tempStorage.isEmpty()) {
			
			storage.push(tempStorage.pop());
		
		}
    	
    	if(start) {
    		
    		start=false;

    		value+=result.getText();
    		result.setText("");
    		
    		
    	}
    	
    	if((((Button)event.getSource()).getText().equals(".")||
    			((Button)event.getSource()).getText().equals("-"))
    			&& model.stack.isEmpty()) {
    		
    		value+=((Button)event.getSource()).getText();
    		equation.setText(value);
    		
    		return;
    	}
    	
    	
    	operator=((Button)event.getSource()).getText();
        
    	if(operator.contentEquals("=")) {
    		
    	//	equation.setEditable(false);
    		//result.setEditable(true);
        	
    		//System.out.println(value);
        	String temp;
    		
    		try{
    			
    			temp = String.format("%.12f", new BigDecimal(model.calculate(value)));
    		}
    		
    		catch(UnsupportedOperationException e) {
    			
    			temp="Math Eror";
    			//equation.setText("");
    		}
    		
    		
    		catch(Exception e) {
    			
    			temp="Syntex Eror";
    			equation.setText("");
    			
    			//sssswhile(!model.stack)
    		}
        	
        	for(int i=temp.length()-1;i>=0 && temp!=null;i--) {
        		
        		if(temp.charAt(i)=='.'|| temp.charAt(i)=='0') {
        			
        			if(temp.charAt(i)=='.')
        			{
        			
        				temp=temp.substring(0, i);
        				break;
        			}
        			
        			temp=temp.substring(0, i);
        			
        		}
        		
        		else break;
        	}
        	
        	if(temp.equals("-0")) temp="0";
        	
        	result.setText(temp);
        	value="";
        	start=true;
        	
        	return;
        }
    	
    	else value += ((Button)event.getSource()).getText();
        
        equation.setText(value);
        
       // equation.setEditable(false);
        
    }
    
    @FXML
    public void process(ActionEvent event) {
    	
    	//equation. setEditable(true);
    	
    	if(((Button)event.getSource()).getText().equals("x^2"))
    	{
    		if(start) {
        		
        		start=false;
        			
        		value+=result.getText()+"^2";
        		result.setText("");

        	}
    		
    		else value+="^2";
    	}
            
    	else if((((Button)event.getSource()).getText().equals("sin")
        		||((Button)event.getSource()).getText().equals("cos")
        		||((Button)event.getSource()).getText().equals("tan")
        		||((Button)event.getSource()).getText().equals("ln")
        		||((Button)event.getSource()).getText().equals("log")
        		||((Button)event.getSource()).getText().equals("√")
        		)
        		&&  !value.equals("")) {
    		
    		
    		if(value.charAt(value.length()-1)=='+'||
        			value.charAt(value.length()-1)=='-'||
        			value.charAt(value.length()-1)=='*'||
        			value.charAt(value.length()-1)=='/'||
        			value.charAt(value.length()-1)=='^'||
        			value.charAt(value.length()-1)=='(') {
        		
       
        		value += ((Button)event.getSource()).getText()+'(';
        	}
    		
    		else value+='*'+((Button)event.getSource()).getText()+'(';
    	
    		
    	}
    	
    	else value+=((Button)event.getSource()).getText()+'(';
    	
    	result.setText("");
    	equation.setText(value);
    	
    //	equation .setEditable(false);
    	
    } 
    
    
    @FXML
    public void ModifyString(ActionEvent event) {
    	//equation.setEditable(true);
    	
    	String stringProcess = ((Button)event.getSource()).getText();
    	
    	if(stringProcess.equals("AC")) {
    		
    		while(!tempStorage.isEmpty()) {
    			
    			storage.push(tempStorage.pop());
    		
    		}	
    		
    		value="";
    		equation.setText("");
    		result.setText("");
    		
    	}
    	
    	else if(stringProcess.equals("DEL")) {
    		
    		if(value.length()!=0)	
    		{
    			if(value.charAt(value.length()-1)=='(' &&	value.length()-2>=0 	)
    			{
    				value = value.substring(0,value.length()-1);
    				
    				while(value.length()>0)
    				{
    					if((value.charAt(value.length()-1)>='a' &&
    	    				value.charAt(value.length()-1)<='z')||value.charAt(value.length()-1)=='√')
    					{
    						value = value.substring(0,value.length()-1);
    					}
    					
    					else  break;
    				}
    					
    			}
    			
    			else	value = value.substring(0,value.length()-1);
    			
    			equation.setText(value);
    		}
    		
    	}
    	
    //	equation.setEditable(false);
    	
    }
    
    
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
    
    
    @FXML
    public void changeSceneToGCD(ActionEvent event) throws IOException {
    
    	while(!tempStorage.isEmpty()) {
			
			storage.push(tempStorage.pop());
		
		}
    	
    	Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
    	
    	Parent root = FXMLLoader.load(getClass().getResource("gcd.fxml"));
        
        window.setScene(new Scene(root));
        window.setResizable(false);
        window.setTitle("GCD&LCM");
        window.show();
    }
    
    public void changeSceneToAgeCalculator(ActionEvent event) throws IOException {
    	
    	while(!tempStorage.isEmpty()) {
			
			storage.push(tempStorage.pop());
		
		}
    	
    	Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
    	
    	Parent root = FXMLLoader.load(getClass().getResource("age.fxml"));
        
        window.setScene(new Scene(root));
        window.setResizable(false);
        window.setTitle("Age Calculator");
        window.show();
    }
    
    

	public void memory(ActionEvent event) {
		
		fileStore.append(equation.getText(), result.getText());
		
		storage.push(new Content(equation.getText(),result.getText()));
		
	}
	
	boolean flag1 = false;
	boolean flag2 = false;
    
	public void replyUp(ActionEvent event) {
		
		try {
			
			flag1=true;
			Content con =new Content();
			
			if(flag2) {
				
				con = storage.pop();
				tempStorage.push(con);
				flag2=false;
			}
			
			con=storage.pop();
			
			equation.setText(con.operation);
			result.setText(con.result);
			tempStorage.push(con);
			
		}
		
		catch(Exception e) {
			
		}
		
	}
	
	public void replyDown(ActionEvent event) {
		
		if(tempStorage.isEmpty()) {
			
			equation.setText("");
			result.setText("");
			flag2=false;
			
			return;
		}
		
		try {
			
			flag2=true;
			Content con =new Content();
			
			if(flag1) {
				
				con = tempStorage.pop();
				storage.push(con);
				flag1=false;
			}
			
			con=tempStorage.pop();
			
			equation.setText(con.operation);
			result.setText(con.result);
			storage.push(con);
			
		}
		
		catch(Exception e) {
			
		}
		
	}
	
	
	
	public void clearMemory(ActionEvent event) {
		
		//equation.setEditable(true);
		equation.setText("");
		result.setText("");
		fileStore.clearFile();
		while(!storage.isEmpty()) {
			
			storage.pop();
			
		}
		
		while(!tempStorage.isEmpty()) {
			
			tempStorage.pop();
		}
		
		//equation.setEditable(true);
		
	}
	
	
    
    
}







