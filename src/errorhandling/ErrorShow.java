/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package errorhandling;


/**
 *
 * @author MeusGinting
 */
public class ErrorShow {
    
    public ErrorShow(){
		
	}
	
	
	public void printMessage(ErrorParameters error)
	{
		String message= getMessage(error);
		
		if(message !=null)
		Messages.println(message);
		
	}
	
	public String getMessage(ErrorParameters error)
	{
		String message= null;
		
		switch(error)
		{
			case NOINPUT : message = "Hi There No input was specified !!! Program exited";break;
							
			case WRONGINPUT : message = "Hi There Input format is wrong ! input discarded";break;
			
			case WRONG_ROMAN_CHARACTER : message = "Hi There Wrong character specified in roman number !!!";break;
			
			case WRONG_ROMAN_STRING : message =  "Hi There wrong Roman number, voilated roman number format";break;
			
			case WRONG_LINE_TYPE : message =  "Hi There Exception caused during processing due to incorrect line type supplied";break;
			
			case NO_IDEA : message = "I have no idea what you are talking about";break;
			
			default : break;
		}
		return message;
	}
    
}
