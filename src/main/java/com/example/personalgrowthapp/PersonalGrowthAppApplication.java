package com.example.personalgrowthapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hlavní třída aplikace Personal Growth App.
 * Tato třída slouží jako vstupní bod pro spuštění celé aplikace.
 */
@SpringBootApplication // Kombinace anotací: @Configuration, @EnableAutoConfiguration a @ComponentScan
public class PersonalGrowthAppApplication {

	/**
	 * Hlavní metoda, která spouští aplikaci pomocí Spring Boot.
	 *
	 * @param args argumenty příkazové řádky (mohou být použity pro konfiguraci aplikace při spuštění)
	 */
	public static void main(String[] args) {
		// Logovací zpráva při startu aplikace
		System.out.println("Starting Personal Growth App...");

		// Spuštění aplikace pomocí SpringApplication
		SpringApplication.run(PersonalGrowthAppApplication.class, args);

		// Logovací zpráva po úspěšném spuštění aplikace
		System.out.println("Personal Growth App has started successfully!");
	}
}