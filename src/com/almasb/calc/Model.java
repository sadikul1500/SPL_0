package com.almasb.calc;

import java.util.Stack;

public class Model {
	
	public Stack <Double> stack = new Stack<Double>();
	public Stack <Character> op = new Stack<Character>();
	public boolean function =false;
	public Stack <Character> func=new Stack<Character>();
    
	public double calculate(String value) {
		
    	if(!stack.isEmpty()) {
    		
    		stack.pop();
    	}
    	
    	while(!op.isEmpty()) 
    	{
    		op.pop();
    	}
    	
    	while(!func.isEmpty()) 
    	{
    		func.pop();
    	}
		
//	try {
    	for(int i=0;i<value.length();i++){
    		
    		if(i==0 && value.charAt(i)=='+' )
    		{
    			stack.push((double) 0);
    			op.push('+');
    		}
			
    		 else if(i==0 && value.charAt(i)=='-' && (value.charAt(i+1)=='√'||value.charAt(i+1)=='s'||value.charAt(i+1)=='c'
    				||value.charAt(i+1)=='t'||value.charAt(i+1)=='l'||value.charAt(i+1)=='(' || value.charAt(i+1)=='e'
    				|| value.charAt(i+1)=='π')) 
			{
				stack.push((double) -1);
				op.push('*');
			}
    		
			//System.out.println(value.charAt(i));
			else if(value.charAt(i)=='(')
    		{
				if(function==true)
				{
					op.push('{');
					function=false;
					
				}
				
				else op.push(value.charAt(i));
    		}
    		
    		else if(value.charAt(i)=='√'||value.charAt(i)=='s'||value.charAt(i)=='c'
    				||value.charAt(i)=='t'||value.charAt(i)=='l'){
    			      
    				function=true;
    				if(value.charAt(i)=='l') {
    					
    					//System.out.println(value.charAt(i));
    					if(value.charAt(i+1)=='o') {
    						
    						func.push(value.charAt(i+1));
    						i+=2;
    					}
    					
    					else if(value.charAt(i+1)=='n') {
    						func.push(value.charAt(i+1));
    						i++;
    					}
    					
    				}
    			
    				else if(value.charAt(i)=='√') func.push(value.charAt(i));
    				
    				else 
    				{
    					
    					func.push(value.charAt(i));
    					i+=2;
    				}
    			      
    		}
    		
    		else if(value.charAt(i)=='e' || value.charAt(i)=='π') {
    			
    			if(value.charAt(i)=='e') {
    				stack.push(Math.E);
    			}
    			
    			else {
    				
    				stack.push(Math.PI);
    			}
    			
    		}
    		
    		
    		else if(value.charAt(i)==')') {
    			//stack.push(doIt());
    			
    			while(true)
    			{
    					if(!op.empty() && op.peek()=='(')
    					{
    							op.pop();
    					       break;
    					}
    				
    				else if(!op.empty() && op.peek()=='{')
    				  {
    						//System.out.println(op.peek());
    						op.pop();
    						stack.push(mathFunction(stack.pop(),func.pop()));
    						break;
    				 }
    				
    				else {
    				       stack.push(calc(stack.pop(),stack.pop(),op.pop()));
    				
    				    //   return stack.push(doIt());
    				}
    			}
    			
    		}
    		
    		else if(value.charAt(i)=='!')
    		{
    			if(!stack.isEmpty()) stack.push(factorial(stack.pop()));
    		}
    		
    		else if(value.charAt(i)>='0' && value.charAt(i)<='9' || value.charAt(i)=='.'
    				|| ((value.charAt(i)=='-' || value.charAt(i)=='+' ) && (i==0 ||value.charAt(i-1)=='('))) {
    				
    			StringBuffer sBuf = new StringBuffer();
    			
    			while(i<value.length() && (value.charAt(i)>='0' && value.charAt(i)<='9'||
    					(value.charAt(i)=='.' || ((value.charAt(i)=='-' || value.charAt(i)=='+')
    							&& (i==0 ||value.charAt(i-1)=='(')))) ){
    				
    				sBuf.append(value.charAt(i++));
    			}
    			
    			stack.push(Double.parseDouble(sBuf.toString()));	
    			
    			i--;
    		}
    		
    		else if(value.charAt(i)=='+'||value.charAt(i)=='-'
    				||value.charAt(i)=='*'||value.charAt(i)=='/'
    				||value.charAt(i)=='^') {
    			
    			
    			//System.out.println(stack.peek());
    			
    			while(!op.isEmpty() &&  hasPrecedence(value.charAt(i) , op.peek())) {
    				
    				stack.push(calc(stack.pop(),stack.pop(),op.pop()));
    			
    			}
    			
    			op.push(value.charAt(i));
    			
    			
    		}
    	
//    	}
    	
	}
	
//	catch(Exception e){
//		return "Syntex eror";
//	}
    	
    	while(!op.isEmpty()) {
    		
    		//System.out.println(op.peek());
    		stack.push(calc(stack.pop(),stack.pop(),op.pop()));
    	}
    	
    	//System.out.println(stack.peek());
    	return stack.peek();
    }
	
	
	
	public double mathFunction(double x,char ch)
	{
		//System.out.println("ch:"+ch);
		//System.out.println("x:"+x);
		
		if(ch=='√') {
			
			if(x<0)
				throw new
				UnsupportedOperationException("math error ");
			//System.out.println("âˆš:" + Math.sqrt(x) );
			return Math.sqrt(x);
		}
		
		else if(ch=='s') {
			
			//System.out.println("sin:" + Math.sin(x) );
			return Math.sin(x);
		}
		
		else if(ch=='c') {
			
			//System.out.println("cos:" + Math.cos(x) );
			return Math.cos(x);
		}
		
		else if(ch=='t') {
			//System.out.println("tan:" + Math.tan(x) );
			
			if(x==Math.PI/2)
			throw new
			UnsupportedOperationException("math error ");
			return Math.tan(x);
		}
		
		else if(ch=='o') {
			
			if(x<=0 || x==Math.cos(Math.PI/2) ||x==Math.sin(0)|| x==Math.tan(0))
			{
				throw new
				UnsupportedOperationException(" math error");
			}
			
			return Math.log10(x);
		}
		
		else if(ch=='n') {
			
			if(x<=0 || x==Math.cos(Math.PI/2) ||x==Math.sin(0)|| x==Math.tan(0))
			{
				throw new
				UnsupportedOperationException(" math error");
			}
			
			return Math.log(x);
		}
		
		//function=false;
		return 0;
	}
	
	
	
	public double factorial(double n)
	{
		int k=(int)n;
		double d=k;
		
		if(d!=n)
			throw new
			UnsupportedOperationException(" ");
		
		else {
			
			double j=1;
			for(int i=1;i<=k;i++) {
				j*=i;
			}
			
			return j; 
		}
	}
	
	public boolean hasPrecedence(char op1,char op2) {
		
		if(op2=='(' ||op2=='{') return false;
		
		else if((op1=='*'||op1=='/')&& (op2=='+'||op2=='-')) return false;
		
		else if(op1=='^'&& (op2=='+'||op2=='-')) return false;
		
		else if(op1=='^'&& (op2=='*'||op2=='/')) return false;
		
		else
			return true;
	}
    		
	
	
	public double calc(double num1,double num2,char x)
	{
		if(x=='/') {
			
			if(num1==0) throw new
			UnsupportedOperationException(" "); 
			
			return num2/num1;
		}
		
		if(x=='*') return num2*num1;
		if(x=='-') return num2-num1;
		if(x=='+') return num2+num1;
		if(x=='^') return Math.pow(num2,num1);
		
		return 0;
	}
	
	
	

}