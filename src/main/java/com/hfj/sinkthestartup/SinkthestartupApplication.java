package com.hfj.sinkthestartup;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hfj.sinkthestartup.classes.Startup;
import com.hfj.sinkthestartup.classes.StartupGame;

@SpringBootApplication
public class SinkthestartupApplication implements CommandLineRunner{

	private static Logger log = LoggerFactory.getLogger(SinkthestartupApplication.class);
	private static Scanner scanner = new Scanner(System.in);


	public static void main(String[] args) {
		log.info("STARTING THE APPLICATION");
		SpringApplication.run(SinkthestartupApplication.class, args);
		log.info("APPLICATION FINISHED");
	}

	@Override
	public void run(String... args) {
		log.info("EXECUTING : command line runner");
		// board setup (abstract?)
		// Abstraction for setup() BEGIN
		StartupGame.setup();

		// gameplay loop (abstract?)
		// StartupGame.gameLoop();
		// refactor to use StartupGame Singleton? i.e. iterateNumOfGuesses linting pathway
		while(!StartupGame.getStartups().isEmpty()){
			log.info("Guess a cell in row/col format, i.e. A0 or H7: ");
			String userGuess = scanner.nextLine();
			if(StartupGame.getUserGuesses().contains(userGuess)){
				log.info("You've already guessed {}, guess again.", userGuess);
			} else {
				StartupGame.setUserGuess(userGuess);
				StartupGame.iterateNumOfGuesses();
				boolean contains = false;
				for(Startup su : StartupGame.getStartups()){
					if(su.containsCell(userGuess)) {
						contains = true;
						log.info("Direct hit on startup {} at {}!", su.getName(), userGuess);
						su.removeCell(userGuess);
						if(su.getCells().isEmpty()) {
							StartupGame.removeStartup(su);
							log.info("You've destroyed startup {}!", su.getName());
						}
						break;
					}
				}
				if(!contains) log.info("Miss at {}! Keep trying!", userGuess);
			}
		}
		log.info("You've destroyed all the startups in {} guesses. YOU WIN!", StartupGame.getNumOfGuesses());			
	}
}
