package com.example.bloglite.repositories;

import com.example.bloglite.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>
{
}
