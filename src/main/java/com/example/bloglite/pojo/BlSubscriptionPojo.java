package com.example.bloglite.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BlSubscriptionPojo
{
    @NotNull
    private Long subscriberId;

    @NotNull
    private Long tagId;
}
