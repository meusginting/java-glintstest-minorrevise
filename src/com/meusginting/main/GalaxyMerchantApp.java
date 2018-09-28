/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meusginting.main;


import java.util.ArrayList;

/**
 *
 * @author MeusGinting
 */
public class GalaxyMerchantApp {
    public static void main(String[] args) {
        //Start to type input string parameters to the programm
        Messages.println("'Welcome to glintstest: Please Copy and Paste 'Input Text' bellow and press Enter Button Twice'");
        
        InputProcess linesprocess = new InputProcess();
        
        ArrayList<String> output;
        output = linesprocess.read();
        
        for(int i=0;i<output.size();i++)
		{
			Messages.println(output.get(i));
		}
    
    }
}
