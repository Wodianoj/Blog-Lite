package com.example.bloglite.repositories;

import com.example.bloglite.entities.BlRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlRoleRepository extends JpaRepository<BlRole, Long>
{
}
