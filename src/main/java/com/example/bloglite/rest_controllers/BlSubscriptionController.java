package com.example.bloglite.rest_controllers;

import com.example.bloglite.entities.BlSubscription;
import com.example.bloglite.pojo.BlSubscriptionPojo;
import com.example.bloglite.repositories.BlSubscriptionRepository;
import com.example.bloglite.services.BlSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class BlSubscriptionController
{
    private static final String ERROR_INFO = "Incorrect or non existent id";
    private static final String DUPLICATE_INFO = "Cannot create this subscription. User already subscribes this tag.";

    private final BlSubscriptionRepository repository;
    private final BlSubscriptionService service;

    @Autowired
    public BlSubscriptionController(BlSubscriptionRepository repository, final BlSubscriptionService service)
    {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/subscription")
    public List<BlSubscription> getAll()
    {
        return repository.findAll();
    }

    @GetMapping("/subscription/{id}")
    public BlSubscription getById(@PathVariable("id") final Long id)
    {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ERROR_INFO));
    }

    @Transactional
    @PostMapping("/subscription")
    public BlSubscription create(@RequestBody @Valid final BlSubscriptionPojo subscriptionPojo)
    {
        if (service.exists(subscriptionPojo))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, DUPLICATE_INFO);
        }
        return service.create(subscriptionPojo);
    }

    @DeleteMapping("/subscription/{id}")
    public void deactivate(@PathVariable("id") final Long subscriptionId)
    {
        if (repository.existsById(subscriptionId))
        {
            service.deactivate(subscriptionId);
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ERROR_INFO);
        }
    }
}