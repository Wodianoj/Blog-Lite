package com.example.bloglite.rest_controllers;

import com.example.bloglite.entities.Users;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class UsersController
{
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/users")
    public Users users(@RequestParam(value = "name", defaultValue = "World") String name)
    {
        return new Users(counter.incrementAndGet(), String.format(template, name));
    }
}
