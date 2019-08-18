package com.thinhda.spring.grpc.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
@Slf4j
public class PingController {

    @GetMapping("/ping")
    public ResponseEntity<?> ping() {
        log.debug("Ping message from ", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseEntity.ok("");
    }

}
