package com.example.bloglite.services;

import com.example.bloglite.entities.BlTag;
import com.example.bloglite.pojo.BlTagPojo;
import com.example.bloglite.repositories.BlTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlRoleService
{
    private final BlTagRepository repository;

    @Autowired
    public BlRoleService(final BlTagRepository repository)
    {
        this.repository = repository;
    }

    public BlTag create(final BlTagPojo pojo)
    {
        final BlTag entity = new BlTag();
        entity.setName(pojo.getName());

        return repository.save(entity);
    }

    public BlTag update(final Long id, final BlTagPojo tagPojo)
    {
        final BlTag entity = new BlTag();
        entity.setId(id);
        entity.setName(tagPojo.getName());

        return repository.save(entity);
    }

    public void delete(final Long id)
    {
        repository.deleteById(id);
    }
}