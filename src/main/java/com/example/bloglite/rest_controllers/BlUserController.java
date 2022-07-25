package com.example.bloglite.rest_controllers;

import com.example.bloglite.entities.BlTag;
import com.example.bloglite.entities.BlUser;
import com.example.bloglite.pojo.BlTagPojo;
import com.example.bloglite.pojo.BlUserPojo;
import com.example.bloglite.repositories.BlUserRepository;
import com.example.bloglite.services.BlUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlUserController
{
    private final BlUserRepository repository;
    private final BlUserService service;
    private static final String ERROR_INFO = "Incorrect or non existent id";


    @Autowired
    public BlUserController(BlUserRepository repository, BlUserService service)
    {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/user")
    public List<BlUser> getAll()
    {
        return repository.findAll();
    }

    @GetMapping("/user/{id}")
    public BlUser getById(@PathVariable("id") final Long id)
    {
        return repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(ERROR_INFO));
    }

    @PostMapping("/user")
    public BlUser create(@RequestBody final BlUserPojo userPojo)
    {
        return service.create(userPojo);
    }

    @PutMapping("/user/{id}")
    public BlUser update(@PathVariable("id") final Long userId,
                        @RequestBody final BlUserPojo userPojo)
    {
        if (repository.existsById(userId))
        {
            return service.update(userId, userPojo);
        }
        else
        {
            throw new IllegalArgumentException(ERROR_INFO);
        }
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable("id") final Long userId)
    {
        if (repository.existsById(userId))
        {
            service.delete(userId);
        }
        else
        {
            throw new IllegalArgumentException(ERROR_INFO);
        }
    }

}
