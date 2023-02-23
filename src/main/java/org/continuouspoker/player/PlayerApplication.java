package org.continuouspoker.player;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlayerApplication {

	public static void main(String[] args) {
		System.out.println("Lets go");
		SpringApplication.run(PlayerApplication.class, args);
	}
}
