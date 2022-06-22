package com.example.bloglite.rest_controllers;

import com.example.bloglite.entities.BlTag;
import com.example.bloglite.repositories.BlTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlTagController
{
    private final BlTagRepository repository;

    @Autowired
    public BlTagController(BlTagRepository repository)
    {
        this.repository = repository;
    }

    @GetMapping("/tag")
    public List<BlTag> getAll()
    {
        return repository.findAll();
    }
}
