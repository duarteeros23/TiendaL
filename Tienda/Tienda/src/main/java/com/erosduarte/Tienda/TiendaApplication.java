package com.erosduarte.Tienda;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TiendaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TiendaApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Api funcionando correctamente...");
    }
}
