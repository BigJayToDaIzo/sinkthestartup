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
		log.info("Name your first startup to be randomly placed on the grid: ");
		String startup1Name = scanner.nextLine();
		log.info("Name your second startup to be randomly placed on the grid: ");
		String startup2Name = scanner.nextLine();
		log.info("Name your third startup to be randomly placed on the grid: ");
		String startup3Name = scanner.nextLine();

		// ensure no duplication of cells exists in creation (abstract?)
		Startup startup1 = new Startup(startup1Name);
		Startup startup2 = new Startup(startup2Name);
		Startup startup3 = new Startup(startup3Name);

		// create board and pass in startups
		StartupGame game = StartupGame.getInstance(startup1, startup2, startup3);

		// gameplay loop (abstract?)
		// refactor to use StartupGame Singleton? i.e. iterateNumOfGuesses linting pathway
		while(!game.getStartups().isEmpty()){
			log.info("Guess a cell in row/col format, i.e. A0 or H7: ");
			String userGuess = scanner.nextLine();
			if(game.getUserGuesses().contains(userGuess)){
				log.info(String.format("You've already guessed %s, guess again.", userGuess));
			} else {
				game.setUserGuess(userGuess);
				game.iterateNumOfGuesses();
				boolean contains = false;
				for(Startup su : game.getStartups()){
					if(su.containsCell(userGuess)) {
						contains = true;
						log.info(String.format("direct hit on startup %s at %s!", su.getName(), userGuess));
						su.removeCell(userGuess);
						if(su.getCells().isEmpty()) {
							game.removeStartup(su);
							log.info(String.format("You've destroyed startup %s!", su.getName()));
						}
						break;
					}
				}
				if(!contains) log.info(String.format("miss at %s! Keep trying!", userGuess));
			}
		}
		log.info(String.format("You've destroyed all the startups in %s guesses.  YOU WIN!", game.getNumOfGuesses()));
			
	}
}
