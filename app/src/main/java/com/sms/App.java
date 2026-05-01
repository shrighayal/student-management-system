package com.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("  Student Management System Started!");
        System.out.println("  DevOps Project 3 - Maven Build Automation");
        System.out.println("===========================================");
        SpringApplication.run(App.class, args);
    }
}
