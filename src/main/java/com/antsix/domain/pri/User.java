package com.antsix.domain.pri;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//lombok 可以省略 getter和setter
@Data

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false,length = 4)
    private String name;

    @Column(nullable = false)
    private Integer age;

    public User(){}
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    // 省略setter和getter

}