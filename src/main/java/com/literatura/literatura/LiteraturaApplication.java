package com.literatura.literatura;

import com.literatura.literatura.principal.Principal;
import com.literatura.literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

     @Autowired
     LibroRepository repository;


    public static void main(String[] args) {
        SpringApplication.run(LiteraturaApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(repository);
        principal.muestraElMenu();
    }

}
