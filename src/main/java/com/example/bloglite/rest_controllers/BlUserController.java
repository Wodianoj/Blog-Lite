package com.example.bloglite.rest_controllers;

import com.example.bloglite.entities.BlUser;
import com.example.bloglite.repositories.BlUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlUserController
{
    private final BlUserRepository repository;

    @Autowired
    public BlUserController(BlUserRepository repository)
    {
        this.repository = repository;
    }

    @GetMapping("/user")
    public List<BlUser> getAll()
    {
        return repository.findAll();
    }
}
