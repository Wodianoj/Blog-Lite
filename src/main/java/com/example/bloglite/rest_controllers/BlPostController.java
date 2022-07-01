package com.example.bloglite.rest_controllers;

import com.example.bloglite.entities.BlPost;
import com.example.bloglite.repositories.BlPostRepository;
import com.example.bloglite.repositories.BlUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlPostController
{
    private final BlPostRepository repository;

    @Autowired
    public BlPostController(BlPostRepository repository)
    {
        this.repository = repository;
    }

    @GetMapping("/post")
    public List<BlPost> getAll()
    {
        return repository.findAll();
    }
}
