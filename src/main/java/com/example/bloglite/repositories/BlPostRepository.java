package com.example.bloglite.repositories;

import com.example.bloglite.entities.BlPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlPostRepository extends JpaRepository<BlPost, Long>
{
}
