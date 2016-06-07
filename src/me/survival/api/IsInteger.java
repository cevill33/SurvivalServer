package me.survival.api;

public class IsInteger {

	public static boolean isInteger(String value) {
		 try {
		    int number = Integer.parseInt(value);
		    return number < 100; 
		 }
		 catch(NumberFormatException e) {
		   return false;
		 }
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
