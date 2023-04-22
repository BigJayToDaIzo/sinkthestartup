package com.hfj.sinkthestartup.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class StartupGame {
	//Static variable reference of StartupGame of type Singleton
	private static StartupGame INSTANCE;

	// instance variables
	private static final Logger log = LoggerFactory.getLogger(StartupGame.class);
	private static Scanner scanner = new Scanner(System.in);
	private static List<Startup> startups = new ArrayList<>();
	private static List<String> occupiedCells = new ArrayList<>();
	private static List<String> userGuesses = new ArrayList<>();
	private static int numOfGuesses = 0;

	// constructor
	private static StartupGame getInstance(Startup s1, Startup s2, Startup s3){
		if(INSTANCE == null) INSTANCE = new StartupGame();

		s1.randomizeCells();
		startups.add(s1);
		addOccupiedCells(s1);

		s2.randomizeCells();
		while(containsOverlap(s2)){
			log.debug("Startup {} contained a dupe cell. Rerolling!", s2.getName());
			s2.clearCells();
			s2.randomizeCells();
		} 
		startups.add(s2);
		addOccupiedCells(s2);


		s3.randomizeCells();
		while(containsOverlap(s3)){
			log.debug("Startup {} contained a dupe cell. Rerolling!", s3.getName());
			s3.clearCells();
			s3.randomizeCells();
		} 
		startups.add(s3);
		addOccupiedCells(s3);

		outputBoardState();
		
		return INSTANCE;
	}

	private static List<Startup> getStartups(){
		return startups;
	}

	private static void removeStartup(Startup s){
		startups.remove(s);
	}

	private static List<String> getUserGuesses(){
		return userGuesses;
	}

	private static void setUserGuess(String guess){
		userGuesses.add(guess);
	}

	public static int getNumOfGuesses(){
		return numOfGuesses;
	}

	private static void iterateNumOfGuesses(){
		numOfGuesses++;
	}

	private static boolean containsOverlap(Startup su){
		List<String> suCells = su.getCells();
		for(String cell : occupiedCells){
			if(suCells.contains(cell)) return true;
		}
		return false;
	}

	private static void addOccupiedCells(Startup s){
		for(String cell : s.getCells()){
			occupiedCells.add(cell);
		}
	}

	private static boolean checkForHit(String userGuess){
		boolean contains = false;
		for(Startup su : StartupGame.getStartups()){
			if(su.containsCell(userGuess)) {
				contains = true;
				log.info("\nDirect hit on startup {} at {}!", su.getName(), userGuess);
				su.removeCell(userGuess);
				if(su.getCells().isEmpty()) {
					StartupGame.removeStartup(su);
					log.info("\nYou've destroyed startup {}!", su.getName());
				}
				break;
			}
		}
		return contains;
	}

	private static void outputBoardState(){
		StringBuilder sb = new StringBuilder("\n***** Game Board State *****\n");
		sb.append("* Startups/Cells reamining *\n");
		for(Startup su : startups){
			String suAppend = String.format("%s%n", su);
			sb.append(suAppend);
		}
		String userGuessesAppend = String.format("* Number of user guesses: %d%n", getNumOfGuesses());
		sb.append(userGuessesAppend);
		String sb2s = sb.toString();
		log.debug(sb2s);
	}

	public static StartupGame setup(){
		log.info("\nName your first startup to be randomly placed on the grid: ");
		String startup1Name = scanner.nextLine();
		log.info("\nName your second startup to be randomly placed on the grid: ");
		String startup2Name = scanner.nextLine();
		log.info("\nName your third startup to be randomly placed on the grid: ");
		String startup3Name = scanner.nextLine();

		return getInstance(new Startup(startup1Name), new Startup(startup2Name), new Startup(startup3Name));
	}

	public static void gameLoop(){
		while(!StartupGame.getStartups().isEmpty()){
			log.info("\nGuess a cell in row/col format, i.e. A0 or H7: ");
			String userGuess = scanner.nextLine().toUpperCase();
			if(StartupGame.getUserGuesses().contains(userGuess)){
				log.info("\nYou've already guessed {}, guess again.", userGuess);
			} else {
				StartupGame.setUserGuess(userGuess);
				StartupGame.iterateNumOfGuesses();
				if(!StartupGame.checkForHit(userGuess)) log.info("\nMiss at {}! Keep trying!", userGuess);
			}
			outputBoardState();
		}

	}

}
