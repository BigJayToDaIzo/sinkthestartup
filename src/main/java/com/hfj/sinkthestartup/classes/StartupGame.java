package com.hfj.sinkthestartup.classes;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class StartupGame {
	//Static variable reference of StartupGame of type Singleton
	private static StartupGame INSTANCE;

	// instance variables
	private static final Logger log = LoggerFactory.getLogger(StartupGame.class);
	private static List<Startup> startups = new ArrayList<>();
	private static List<String> occupiedCells = new ArrayList<>();
	private static List<String> userGuesses = new ArrayList<>();
	private static int numOfGuesses = 0;

	// constructor
	public static StartupGame getInstance(Startup s1, Startup s2, Startup s3){
		if(INSTANCE == null) INSTANCE = new StartupGame();

		s1.randomizeCells();
		startups.add(s1);
		addOccupiedCells(s1);
		log.info("Startup 1 cells:{}:", s1.cellsToString());
		System.console();

		s2.randomizeCells();
		while(containsOverlap(s2)){
			log.info("Startup 2 contained a dupe cell. Rerolling!");
			s2.clearCells();
			s2.randomizeCells();
		} 
		startups.add(s2);
		addOccupiedCells(s2);
		log.info("Startup 2 cells:{}", s2.cellsToString());


		s3.randomizeCells();
		while(containsOverlap(s3)){
			log.info("Startup 3 contained a dupe cell. Rerolling!");
			s3.clearCells();
			s3.randomizeCells();
		} 
		startups.add(s3);
		addOccupiedCells(s3);
		log.info("Startup 3 cells:{}", s3.cellsToString());
		
		return INSTANCE;
	}

	public static List<Startup> getStartups(){
		return startups;
	}

	public static void removeStartup(Startup s){
		startups.remove(s);
	}

	public static List<String> getUserGuesses(){
		return userGuesses;
	}

	public static void setUserGuess(String guess){
		userGuesses.add(guess);
	}

	public static int getNumOfGuesses(){
		return numOfGuesses;
	}

	public static void iterateNumOfGuesses(){
		numOfGuesses++;
	}

	public static boolean containsOverlap(Startup su){
		List<String> suCells = su.getCells();
		for(String cell : occupiedCells){
			if(suCells.contains(cell)) return true;
		}
		return false;
	}

	public static void addOccupiedCells(Startup s){
		for(String cell : s.getCells()){
			occupiedCells.add(cell);
		}
	}

}
