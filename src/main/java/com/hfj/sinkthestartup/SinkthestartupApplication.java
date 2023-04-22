package com.hfj.sinkthestartup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hfj.sinkthestartup.classes.StartupGame;

@SpringBootApplication
public class SinkthestartupApplication implements CommandLineRunner{

	private static Logger log = LoggerFactory.getLogger(SinkthestartupApplication.class);

	public static void main(String[] args) {
		log.info("STARTING THE APPLICATION");
		SpringApplication.run(SinkthestartupApplication.class, args);
		log.info("APPLICATION FINISHED");
	}

	@Override
	public void run(String... args) {
		log.info("EXECUTING : command line runner");

		StartupGame.setup();
		StartupGame.gameLoop();
		
		log.info("You've destroyed all the startups in {} guesses. YOU WIN!", StartupGame.getNumOfGuesses());			
	}
}
