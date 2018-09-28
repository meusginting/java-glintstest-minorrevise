/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meusginting.main;

import errorhandling.ErrorParameters;
import errorhandling.ErrorShow;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author MeusGinting
 */
public class InputProcess {

        private final Scanner scan;
	private final InputParameters LinesParameter;
	private final ErrorShow eMessage;
        private final HashMap<String, String> constantAssignments;
        private final HashMap<String, String> computedLiterals;
        private final ArrayList<String> output;
        
        public InputProcess()
	{
		this.scan = new Scanner(System.in);
		this.LinesParameter = new InputParameters();
                //For Error Handling
		this.eMessage = new ErrorShow();
                //For Contain Assigment
		this.constantAssignments = new HashMap<String, String>();
                //For Contain Literal
		this.computedLiterals = new  HashMap<String, String>();
                //For String Output
		this.output = new ArrayList<String>();
	}
        
        //Read Array String input parameter and questions
        public ArrayList<String> read()
	{
		String line;
		int count=0;
		ErrorParameters error = null;
		
		
		while(this.scan.hasNextLine() && (line = this.scan.nextLine()).length()>0 )
		{
			error = validate(line);
			
			switch(error)
			{
				case NO_IDEA :  this.output.add(this.eMessage.getMessage(error));break;
				
				default : this.eMessage.printMessage(error);
			}
			
			count++;
		}
		
		switch(count)
		{
			case 0: error = ErrorParameters.NOINPUT;
					this.eMessage.printMessage(error);
					break;
					
			default : 
		}
		
		return this.output;
		
	}
        //validate string input
        private ErrorParameters validate(String line)
	{
		
		ErrorParameters error = ErrorParameters.SUCCESS;
		
		InputParameters.Type lineType = this.LinesParameter.getLineType(line);
		
		switch(lineType)
		{
			case Intergalatic_to_Roman : 		 processAssignmentLine(line);
							         break;
							
			case Metal_Price :			 processCreditsLine(line);
						    		 break;
						    
			case HOW_MUCH : processHowMuchQuestion(line);
									 break;
									 
			case HOW_MANY : processHowManyCreditsQuestion(line);
									 break;
			
			default : error = ErrorParameters.NO_IDEA; break;
		}
				
		return error;
	}
        //step to assign string input 
        private void processAssignmentLine(String line)
	{
		
		String[] splited = line.trim().split("\\s+");
		

		try
		{
			constantAssignments.put(splited[0], splited[2]);
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			this.eMessage.printMessage(ErrorParameters.WRONG_LINE_TYPE);
			Messages.println(e.getMessage());
		}
	}
	//capture string How Much
        private void processHowMuchQuestion(String line)
	{
		try
		{
			
			String formatted = line.split("\\sis\\s")[1].trim();
			formatted = formatted.replace("?","").trim();
			String keys[] = formatted.split("\\s+");
			
			
			String romanResult="";
			String completeResult = null;
			boolean errorOccured = false;
			
			for(String key : keys)
			{
				String romanValue = constantAssignments.get(key);
				if(romanValue==null)
				{
					completeResult = this.eMessage.getMessage(ErrorParameters.NO_IDEA);
					errorOccured = true;
					break;
				}
				romanResult += romanValue;
			}
			
			if(!errorOccured)
			{
				romanResult = RomanConversions.romanToArabic(romanResult);
				completeResult = formatted+" is "+romanResult;
			}
				
			output.add(completeResult);
			
		}
		catch(Exception e)
		{
			this.eMessage.printMessage(ErrorParameters.WRONG_LINE_TYPE);
			Messages.println(e.getMessage());
			
		}
	}
	//Capture each Metal Price
        private void processCreditsLine(String line)
	{
		try
		{
			String formatted = line.replaceAll("(is\\s+)|([c|C]redits\\s*)","").trim();
			String[] keys = formatted.split("\\s");
			String toBeComputed = keys[keys.length-2];
			float value = Float.parseFloat(keys[keys.length-1]);	
			String roman="";
			
			for(int i=0;i<keys.length-2;i++)
			{
				roman += constantAssignments.get(keys[i]);
			}
			
			int romanNumber = Integer.parseInt(RomanConversions.romanToArabic(roman));
			float credit = (float)(value/romanNumber);
			
					
			computedLiterals.put(toBeComputed, credit+"");
		}
		catch(Exception e)
		{
			
			this.eMessage.printMessage(ErrorParameters.WRONG_LINE_TYPE);
			Messages.println(e.getMessage());
			
		}
	}
        //Capture How Many Questions
        private void processHowManyCreditsQuestion(String line) {
		
		try
		{
			String formatted = line.split("(\\sis\\s)")[1];
			formatted = formatted.replace("?","").trim();
			String[] keys = formatted.split("\\s");
			
			boolean found = false;
			String roman = "";
			String outputResult = null;
			Stack<Float> cvalues = new Stack<Float>();
			
			for(String key : keys)
			{
				found = false;
				
				String romanValue = constantAssignments.get(key);
				if(romanValue!=null)
				{
					roman += romanValue;
					found = true;
				}
				
				String computedValue = computedLiterals.get(key);
				if(!found && computedValue!=null)
				{
					cvalues.push(Float.parseFloat(computedValue));
					found = true;
				}
				
				if(!found)
				{
					outputResult = this.eMessage.getMessage(ErrorParameters.NO_IDEA);
					break;
				}
			}
			
			if(found)
			{
				float res=1;
				for(int i =0;i<cvalues.size();i++)
				res *= cvalues.get(i);
					
				int finalres= (int) res;
				if(roman.length()>0)
				finalres = (int)(Integer.parseInt(RomanConversions.romanToArabic(roman))*res);
				outputResult = formatted +" is "+ finalres +" Credits";
			}
			
			this.output.add(outputResult);
			
		}
		catch(Exception e)
		{
			this.eMessage.printMessage(ErrorParameters.WRONG_LINE_TYPE);
			Messages.println(e.getMessage());
		}
		
	}
    
}
