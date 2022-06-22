package com.example.bloglite.repositories;

import com.example.bloglite.entities.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Integer>
{
}
