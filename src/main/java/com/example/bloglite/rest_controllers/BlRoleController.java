package com.example.bloglite.rest_controllers;

import com.example.bloglite.entities.BlRole;
import com.example.bloglite.repositories.BlRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlRoleController
{
    private static final String ERROR_INFO = "Incorrect or non existent id";

    private final BlRoleRepository repository;

    @Autowired
    public BlRoleController(BlRoleRepository repository)
    {
        this.repository = repository;
    }

    @GetMapping("/role")
    public List<BlRole> getAll()
    {
        return repository.findAll();
    }

    @GetMapping("/role/{id}")
    public BlRole getById(@PathVariable("id") final Long id)
    {
        return repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(String.format(ERROR_INFO)));
    }
}
