package me.survival.api;

import java.util.Random;

public class GetRandom {

	
	
	public static int returnRandom(int min, int max) {
		
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
		
	
	}
}
