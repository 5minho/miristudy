package me.minho.miristiudy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class PingController {

    @GetMapping("/ping")
    public String pong() throws UnknownHostException {
        return "pong " + InetAddress.getLocalHost().getHostName();
    }
}
