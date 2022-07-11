package com.example.bloglite.services;

import com.example.bloglite.entities.BlRole;
import com.example.bloglite.pojo.BlRolePojo;
import com.example.bloglite.repositories.BlRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlRoleService
{
    private final BlRoleRepository repository;

    @Autowired
    public BlRoleService(final BlRoleRepository repository)
    {
        this.repository = repository;
    }

    /*public BlRole create(final BlRolePojo pojo)
    {
        final BlRole entity = new BlRole();
        entity.setName(pojo.getName());

        return repository.save(entity);
    }

    public BlRole update(final Long id, final BlRolePojo tagPojo)
    {
        final BlRole entity = new BlRole();
        entity.setId(id);
        entity.setName(tagPojo.getName());

        return repository.save(entity);
    }

    public void delete(final Long id)
    {
        repository.deleteById(id);
    }*/
}