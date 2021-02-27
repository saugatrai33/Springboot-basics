package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student ram = new Student(
                    "Ram",
                    "ram@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5));

            Student shyam = new Student(
                    "Shyam",
                    "shyam@gmail.com",
                    LocalDate.of(2001, Month.FEBRUARY, 10));

            Student hari = new Student(
                    "Hari",
                    "hari@gmail.com",
                    LocalDate.of(1992, Month.JANUARY, 5));

            Student sita = new Student(
                    "Sita",
                    "sita@gmail.com",
                    LocalDate.of(1995, Month.JANUARY, 5));
            studentRepository.saveAll(List.of(ram, shyam, hari, sita));
        };
    }

}
