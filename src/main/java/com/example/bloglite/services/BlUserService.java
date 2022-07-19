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
    private final BlUserRepository repository;
    private final BlRoleRepository roleRepository;
    private static final String ERROR_INFO = "Incorrect or non existent id";

    @Autowired
    public BlUserService(final BlUserRepository repository, BlRoleRepository roleRepository)
    {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    public BlUser create(final BlUserPojo pojo)
    {
        final BlUser entity = new BlUser();
        entity.setName(pojo.getName());
        entity.setLastName(pojo.getLastName());
        entity.setEmail(pojo.getEmail());
        entity.setRole(roleRepository.findById(pojo.getRoleId()).orElseThrow(() ->
                new IllegalArgumentException(String.format(ERROR_INFO))));

        return repository.save(entity);
    }

    public BlUser update(final Long id, final BlUserPojo userPojo)
    {
        final BlUser entity = new BlUser();
        entity.setId(id);
        entity.setName(userPojo.getName());
        entity.setLastName(userPojo.getLastName());
        entity.setEmail(userPojo.getEmail());
        entity.setRole(roleRepository.findById(userPojo.getRoleId()).orElseThrow(() ->
                new IllegalArgumentException(String.format(ERROR_INFO))));

        return repository.save(entity);
    }

    public void delete(final Long id)
    {
        repository.deleteById(id);
    }
}