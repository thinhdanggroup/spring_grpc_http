package com.thinhda.spring.grpc.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

/**
 * Created by thinhda.
 * Date: 2019-08-18
 */
@SpringBootApplication
public class CoreApplication implements DisposableBean, CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String pidFile = System.getProperty("pidfile");
        if (pidFile != null) {
            new File(pidFile).deleteOnExit();
        }
    }

    @Override
    public void destroy() {
    }
}