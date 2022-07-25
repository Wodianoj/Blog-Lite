package com.example.bloglite.services;

import com.example.bloglite.entities.BlUser;
import com.example.bloglite.pojo.BlUserPojo;
import com.example.bloglite.repositories.BlRoleRepository;
import com.example.bloglite.repositories.BlUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlUserService
{
    private static final String ERROR_INFO = "Incorrect or non existent id";

    private final BlUserRepository repository;
    private final BlRoleRepository roleRepository;

    @Autowired
    public BlUserService(final BlUserRepository repository, BlRoleRepository roleRepository)
    {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    public BlUser create(final BlUserPojo userPojo)
    {
        BlUser entity = createEntityFromPojo(userPojo);
        return repository.save(entity);
    }

    public BlUser update(final Long id, final BlUserPojo userPojo)
    {
        BlUser entity = createEntityFromPojo(userPojo);
        entity.setId(id);
        return repository.save(entity);
    }

    private BlUser createEntityFromPojo(BlUserPojo userPojo)
    {
        BlUser entity = new BlUser();
        entity.setName(userPojo.getName());
        entity.setLastName(userPojo.getLastName());
        entity.setEmail(userPojo.getEmail());
        entity.setRole(roleRepository.findById(userPojo.getRoleId()).orElseThrow(() ->
                new IllegalArgumentException(ERROR_INFO)));
        return entity;
    }

    public void delete(final Long id)
    {
        repository.deleteById(id);
    }
}