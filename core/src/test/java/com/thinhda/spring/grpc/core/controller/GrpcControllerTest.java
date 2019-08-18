package com.thinhda.spring.grpc.core.controller;

import com.thinhda.spring.grpc.core.model.CoreServiceGrpc;
import com.thinhda.spring.grpc.core.model.PingRequest;
import com.thinhda.spring.grpc.core.model.PingResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.Test;
import sun.jvm.hotspot.utilities.Assert;

public class GrpcControllerTest {
    @Test
    public void ping() {
        PingRequest request = PingRequest.newBuilder()
            .setTimestamp(System.currentTimeMillis())
            .build();

        PingResponse response = smsServiceStub().ping(request);

        Assert.that(request.getTimestamp() == response.getTimestamp(),"Timestamp must be equal");
    }

    private CoreServiceGrpc.CoreServiceBlockingStub smsServiceStub() {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:6790").usePlaintext().build();
        return CoreServiceGrpc.newBlockingStub(channel);
    }
}