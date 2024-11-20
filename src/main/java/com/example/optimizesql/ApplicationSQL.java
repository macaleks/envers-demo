package com.example.optimizesql;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class ApplicationSQL implements CommandLineRunner {

    private final UserGrantsRepository userGrantsRepository;
    private final DataPopulator dataPopulator;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationSQL.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        dataPopulator.populate();

        List<String> names = List.of("userLogin1", "userLogin2", "userLogin3");

        List<UserGrants> byNameIn = userGrantsRepository.findByUserLoginIn(names);

        System.out.println(byNameIn);

    }
}
