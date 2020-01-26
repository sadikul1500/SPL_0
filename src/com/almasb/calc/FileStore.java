package com.almasb.calc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileStore {
    
	File file = new File("arman.txt");
	Scanner sc;
	PrintWriter pw;
	String s="" ;
	
	public FileStore (){
		try{
			
			sc = new Scanner(file);
		
		}
		
		catch(Exception e ) {
		
			
		}
		
	}
	
	public void append(String equationFile,String resultFile) {
    	   
    	   
		try {
			  pw = new PrintWriter(new FileOutputStream("arman.txt",true));
    		  //System.out.println(equationFile);
    		  //System.out.println(resultFile);
			  pw.println(equationFile);
    		  pw.println(resultFile);
    		  pw.close();
    		   
    	   } 
		
		catch (Exception e) {
    		   
			
    	  }
		
	}
    	
			
	public String readEquation() {
	
	
    		s = " " ;  
    		
   		   if(sc.hasNextLine()==true) {
   			   s = sc.nextLine();
   			   return s;
   		   }
    		   
   		   else {
   			
   			   return s;
   		   }
    	  
     }
	
	public String readResult() {
		  
		s = " ";
		
		if(sc.hasNextLine()) {
			   s = sc.nextLine();
			   return s;
		   }
		   
		   else return s;
		
	}

	
	public void clearFile() {
		
		try {
			
			PrintWriter pw2 = new PrintWriter(file);
			s="";
			pw2.println(s);
			pw2.close();
		}
		
		catch(Exception e) {
			
		}
		
	}

}












