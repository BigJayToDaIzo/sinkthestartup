package com.hfj.sinkthestartup.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StartupGame {
	// instance variables
	private static final Logger log = LoggerFactory.getLogger(StartupGame.class);
	private static Scanner scanner = new Scanner(System.in);
	private List<Startup> startups = new ArrayList<>();
	private List<String> occupiedCells = new ArrayList<>();
	private List<String> userGuesses = new ArrayList<>();
	private int numOfGuesses = 0;

	// constructor
	public StartupGame(){
		String[] startupNames = setStartupNames();
		Startup s1 = new Startup(startupNames[0]);
		s1.randomizeCells();
		startups.add(s1);
		addOccupiedCells(s1);

		Startup s2 = new Startup(startupNames[1]);
		s2.randomizeCells();
		while(containsOverlap(s2)){
			log.debug("Startup {} contained a dupe cell. Rerolling!", s2.getName());
			s2.clearCells();
			s2.randomizeCells();
		} 
		startups.add(s2);
		addOccupiedCells(s2);

		Startup s3 = new Startup(startupNames[2]);
		s3.randomizeCells();
		while(containsOverlap(s3)){
			log.debug("Startup {} contained a dupe cell. Rerolling!", s3.getName());
			s3.clearCells();
			s3.randomizeCells();
		} 
		startups.add(s3);
		addOccupiedCells(s3);

		outputBoardState();

	}

	public List<Startup> getStartups(){
		return startups;
	}

	public void removeStartup(Startup s){
		startups.remove(s);
	}

	public List<String> getUserGuesses(){
		return userGuesses;
	}

	public void setUserGuess(String guess){
		userGuesses.add(guess);
	}

	public int getNumOfGuesses(){
		return numOfGuesses;
	}

	public void iterateNumOfGuesses(){
		numOfGuesses++;
	}

	public boolean containsOverlap(Startup su){
		List<String> suCells = su.getCells();
		for(String cell : occupiedCells){
			if(suCells.contains(cell)) return true;
		}
		return false;
	}

	public void addOccupiedCells(Startup s){
		for(String cell : s.getCells()){
			occupiedCells.add(cell);
		}
	}

	public boolean checkForHit(String userGuess){
		for(Startup su : startups){
			if(su.containsCell(userGuess)) {
				log.info("\nDirect hit on startup {} at {}!", su.getName(), userGuess);
				su.removeCell(userGuess);
				if(su.getCells().isEmpty()) {
					removeStartup(su);
					log.info("\nYou've destroyed startup {}!", su.getName());
				}
				return true;
			}
		}
		return false;
	}

	public void outputBoardState(){
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

	public String[] setStartupNames(){
		log.info("\nName your first startup to be randomly placed on the grid: ");
		String startup1Name = scanner.nextLine();
		log.info("\nName your second startup to be randomly placed on the grid: ");
		String startup2Name = scanner.nextLine();
		log.info("\nName your third startup to be randomly placed on the grid: ");
		String startup3Name = scanner.nextLine();

		return new String[]{startup1Name, startup2Name, startup3Name};

	}

	public void gameLoop(){
		while(!getStartups().isEmpty()){
			log.info("\nGuess a cell in row/col format, i.e. A0 or H7: ");
			String userGuess = scanner.nextLine().toUpperCase();
			if(getUserGuesses().contains(userGuess)){
				log.info("\nYou've already guessed {}, guess again.", userGuess);
			} else {
				setUserGuess(userGuess);
				iterateNumOfGuesses();
				if(!checkForHit(userGuess)) log.info("\nMiss at {}! Keep trying!", userGuess);
			}
			outputBoardState();
		}
		log.info("\n You have sunk every startup in {} turns! YOU WIN!", getNumOfGuesses());

	}

}
