package com.example.bloglite.rest_controllers;

import com.example.bloglite.entities.BlTag;
import com.example.bloglite.pojo.BlTagPojo;
import com.example.bloglite.repositories.BlTagRepository;
import com.example.bloglite.services.BlTagService;
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
public class BlTagController
{
    private final BlTagRepository repository;
    private final BlTagService service;
    private static final String ERROR_INFO = "Incorrect or non existent id";

    @Autowired
    public BlTagController(BlTagRepository repository, BlTagService service)
    {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/tag")
    public List<BlTag> getAll()
    {
        return repository.findAll();
    }

    @GetMapping("/tag/{id}")
    public BlTag getById(@PathVariable("id") final Long id)
    {
        return repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(String.format(ERROR_INFO)));
    }

    @PostMapping("/tag")
    public BlTag create(@RequestBody final BlTagPojo tagPojo)
    {
        return service.create(tagPojo);
    }

    @PutMapping("/tag/{id}")
    public BlTag update(@PathVariable("id") final Long tagId,
                        @RequestBody final BlTagPojo tagPojo)
    {
        if (repository.existsById(tagId))
        {
            return service.update(tagId, tagPojo);
        }
        else
        {
            throw new IllegalArgumentException(String.format(ERROR_INFO));
        }
    }

    @DeleteMapping("/tag/{id}")
    public void delete(@PathVariable("id") final Long tagId)
    {
        if (repository.existsById(tagId))
        {
            service.delete(tagId);
        }
        else
        {
            throw new IllegalArgumentException(String.format(ERROR_INFO));
        }
    }
}
