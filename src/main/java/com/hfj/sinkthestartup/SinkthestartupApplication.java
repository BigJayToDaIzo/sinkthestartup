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
		log.debug("STARTING THE APPLICATION");
		SpringApplication.run(SinkthestartupApplication.class, args);
		log.debug("APPLICATION FINISHED");
	}

	@Override
	public void run(String... args) {
		log.debug("EXECUTING : command line runner");
		log.info("\nEnter a name for the first startup: ");
		Startup s1 = new Startup(scanner.nextLine());
		log.info("\nEnter a name for the second startup: ");
		Startup s2 = new Startup(scanner.nextLine());
		log.info("\nEnter a name for the last startup: ");
		Startup s3 = new Startup(scanner.nextLine());
		
		StartupGame game = new StartupGame(s1, s2, s3);
		game.gameLoop();
	}
}
