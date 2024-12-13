package com.diegoliveiraa.desafio_crud.entitys;

import com.diegoliveiraa.desafio_crud.dtos.StudentDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "students")
@Table(name = "students")
@EqualsAndHashCode(of = "id")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date birthday;
    private String cep;
    private String city;
    private String course;

    public Student(StudentDTO data) {
        this.name = data.name();
        this.birthday = data.birthday();
        this.cep = data.cep();;
        this.city = data.city();
        this.course = data.course();
    }

    public Student(Long id, String name, Date birthday, String cep, String city, String course) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.cep = cep;
        this.city = city;
        this.course = course;
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
