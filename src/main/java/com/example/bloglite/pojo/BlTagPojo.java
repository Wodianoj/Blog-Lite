package com.example.bloglite.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BlTagPojo
{
    @NotBlank
    private String name;
}
