package com.thinhda.spring.grpc.core.service;

import com.google.gson.Gson;
import com.thinhda.spring.grpc.core.model.PingRequest;
import com.thinhda.spring.grpc.core.model.PingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by thinhda.
 * Date: 2019-08-18
 */

@Service
@Slf4j
public class PingService {
    public PingResponse ping(PingRequest request) {
        log.info("Received Ping message {}", new Gson().toJson(request));
        return PingResponse.newBuilder()
            .setTimestamp(request.getTimestamp())
            .setMessage("Pong")
            .build();
    }
}
