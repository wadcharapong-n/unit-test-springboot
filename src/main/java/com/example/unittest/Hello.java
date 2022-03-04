package com.example.unittest;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Hello {

    @Id
    private Long Id;

    @Column
    private String name;

    @Column
    private String message;
}
