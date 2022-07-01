package com.example.bloglite.repositories;

import com.example.bloglite.entities.BlUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlUserRepository extends JpaRepository<BlUser, Long>
{
}
