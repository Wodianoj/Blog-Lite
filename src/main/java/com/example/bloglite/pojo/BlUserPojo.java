package com.example.bloglite.pojo;

import com.example.bloglite.entities.BlRole;
import lombok.Data;

@Data
public class BlUserPojo
{
    private String name;
    private String lastName;
    private String email;

    private Long roleId;
}
