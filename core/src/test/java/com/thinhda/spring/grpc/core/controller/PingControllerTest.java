package com.thinhda.spring.grpc.core.controller;

import com.google.protobuf.util.JsonFormat;
import com.thinhda.spring.grpc.core.model.PingRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired


    @Test
    public void ping() throws Exception {
        PingRequest request = PingRequest.newBuilder()
            .setTimestamp(System.currentTimeMillis())
            .build();

        mockMvc.perform(post("/api/ping")
            .content(JsonFormat.printer().omittingInsignificantWhitespace().print(request))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(request.getTimestamp() + "")));
    }
}