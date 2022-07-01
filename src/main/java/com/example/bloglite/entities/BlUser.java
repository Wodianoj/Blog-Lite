package com.example.bloglite.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class BlUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long roleId;

    private String name;
    private String lastName;
    private String email;
}
