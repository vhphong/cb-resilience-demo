package com.pnk.cbresiliencedemo.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


@Service
@Slf4j
public class DemoService {


    @CircuitBreaker(name = "cb")
    @Retry(name = "demo-retry", fallbackMethod = "fallback")
    public void send(String msg) {
        log.info("Sending Data");
        RestClient client = RestClient.builder().build();
        client.post().uri("http://localhost:12000/abc")
                .contentType(MediaType.TEXT_PLAIN)
                .body(msg)
                .retrieve()
                .onStatus(status -> status.is2xxSuccessful(), (req, res) -> System.out.println("Success")).
                onStatus(status -> status.isError(), (req, res) -> System.out.println("Error")).toBodilessEntity();
    }

    public void fallback(String msg, Exception ex) {
        log.info("Inside Fallback for retry");
        //send(msg);
    }

}
