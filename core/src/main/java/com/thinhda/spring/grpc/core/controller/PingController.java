package com.thinhda.spring.grpc.core.controller;

import com.thinhda.spring.grpc.core.model.PingRequest;
import com.thinhda.spring.grpc.core.service.PingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class PingController {
    @Autowired
    private PingService pingService;

    @PostMapping(path = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ping(@RequestBody PingRequest request) {
        return ResponseEntity.ok(pingService.ping(request));
    }

}
