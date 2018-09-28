/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meusginting.main;

/**
 *
 * @author MeusGinting
 */
public class InputParameters {
    
public static enum Type{
		
		 Intergalatic_to_Roman, //For Translate Itergalatic to Roman Value then to desimal
		 Metal_Price, //For Translate Metal Price to Roman Value then to decimal
		 HOW_MUCH, //For counter How_Much Questions
		 HOW_MANY, //For counter How Many Questions
		 NOMATCH
		 
	}
	
	//Using LineFilter Class for input string filtering
	public class LineFilter
	{
		private InputParameters.Type type;
		private String pattern;
		public LineFilter(InputParameters.Type type,String pattern)
		{
			this.type = type;
			this.pattern = pattern;
		}
		
		public String getPattern()
		{
			return this.pattern;
					
		}
		
		public InputParameters.Type getType()
		{
			return this.type;
		}
	}
	
	
	// for Roman Numeral
	public static String IntergalatictoRoman = "^([A-Za-z]+) is ([I|V|X|L|C|D|M])$";
        //For Credits
	public static String MetalPrice = "^([A-Za-z]+)([A-Za-z\\s]*) is ([0-9]+) ([c|C]redits)$";
        //For How Much Questions
	public static String HowMuch = "^how much is (([A-Za-z\\s])+)\\?$";
        //For How Many Questions
	public static String HowMany= "^how many [c|C]redits is (([A-Za-z\\s])+)\\?$";
        
	private LineFilter[] linefilter;

	
	
	//Start Input String Filtering
	public InputParameters()
	{
		
		this.linefilter = new LineFilter[4];
		this.linefilter[0] = new LineFilter(InputParameters.Type.Intergalatic_to_Roman, IntergalatictoRoman);
		this.linefilter[1] = new LineFilter(InputParameters.Type.Metal_Price, MetalPrice);
		this.linefilter[2] = new LineFilter(InputParameters.Type.HOW_MUCH, HowMuch);
		this.linefilter[3] = new LineFilter(InputParameters.Type.HOW_MANY, HowMany);
		
	}
		
		
	
	
	//Error Handling for others question format
	public InputParameters.Type getLineType(String line)
	{
		line = line.trim();
		InputParameters.Type result = Type.NOMATCH;
		
		boolean matched = false;
			
		for(int i =0;i<linefilter.length && !matched ;i++)
		{
			if( line.matches(linefilter[i].getPattern()) )
			{
				matched = true;
				result = linefilter[i].getType();
			}
			
		}
		
		return result;
		
	}
	
}
