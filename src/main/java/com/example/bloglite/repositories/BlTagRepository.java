package com.example.bloglite.repositories;

import com.example.bloglite.entities.BlTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlTagRepository extends JpaRepository<BlTag, Long>
{
}
