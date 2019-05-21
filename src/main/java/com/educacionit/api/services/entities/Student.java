package com.educacionit.api.services.entities;

import java.io.Serializable;
import java.time.LocalDate;

import  javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
public class Student implements Serializable {
    @Id
    private String id;

    @NotNull
    @NotEmpty
    @Size (min = 5, max = 20)
    private String name;

    @NotNull
    @NotEmpty
    @Size (min = 5, max = 20)
    private String lastName;

    @NotNull
    @NotEmpty
    @Email
    @Size (max = 30)
    private String email;

    @NotNull
    @NotEmpty
    @Size (max = 20)
    private String mobile;

    private String birthDate;


    public Student () {
    }

    public Student (String name, String lastName, String email,
                    String mobile, String birthDate) {

        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.birthDate = birthDate;
    }


    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getMobile() {

        return mobile;
    }

    public void setMobile(String mobile) {

        this.mobile = mobile;
    }

    public String getBirthDate() {

        return birthDate;
    }

    public void setBirthDate(String birthDate) {

        this.birthDate = birthDate;
    }
}
