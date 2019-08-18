package com.thinhda.spring.grpc.core.controller;

import com.thinhda.spring.grpc.core.model.CoreServiceGrpc;
import com.thinhda.spring.grpc.core.model.PingRequest;
import com.thinhda.spring.grpc.core.model.PingResponse;
import com.thinhda.spring.grpc.core.service.PingService;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by thinhda.
 * Date: 2019-08-18
 */

@Slf4j
@GRpcService
public class GrpcController extends CoreServiceGrpc.CoreServiceImplBase {
    @Autowired
    private PingService pingService;

    @Override
    public void ping(PingRequest request, StreamObserver<PingResponse> responseObserver) {
        responseObserver.onNext(pingService.ping(request));
        responseObserver.onCompleted();
    }
}
