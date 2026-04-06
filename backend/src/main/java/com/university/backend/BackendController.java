package com.university.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class BackendController {

    @GetMapping("/api/hello")
    public String hello() throws UnknownHostException {
        String hostname = InetAddress.getLocalHost().getHostName();
        return "Hello from backend pod: " + hostname;
    }

    @GetMapping("/api/health")
    public String health() {
        return "OK";
    }

    @GetMapping("/api/kill")
    public void kill() {
        System.exit(1);
    }

    @GetMapping("/api/heavy")
    public String heavy() throws UnknownHostException, InterruptedException {
        String hostname = InetAddress.getLocalHost().getHostName();
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= 50000; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        Thread.sleep(3000);
        return "Pod: " + hostname + " finished heavy task, result length: " + result.toString().length() + " digits";
    }
}
