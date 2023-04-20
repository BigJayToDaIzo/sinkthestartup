package com.hfj.sinkthestartup.utility;

import java.util.Random;

public class RandomizerUtility {
	private Random r = new Random();
	
	public int getCoinFlip(){
		return r.nextInt(2);
	}

	public int getRandInt(int i){
		return r.nextInt(i);
	}
	
}
