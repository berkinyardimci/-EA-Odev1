package com.odev1.entities;


import jdk.nashorn.internal.runtime.regexp.joni.exception.ErrorMessages;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull()
    @NotBlank
    @Size(min=3, max = 40)
    private String title;

    @NotBlank
    @NotNull()
    private String detail;

    @NotNull
    @Min(value=3, message="3 ten az sayı giremezzsiniz")
    private int price;
}
