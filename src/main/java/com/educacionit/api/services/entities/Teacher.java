package com.educacionit.api.services.entities;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class Teacher implements Serializable {
    @Id
    private String id;

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 20)
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

    @NotNull
    @NotEmpty
    @Email
    @Size (max = 30)
    private String birthDate;

    @NotNull
    @NotEmpty
    @Email
    @Size (max = 30)
    private String title;

    @NotNull
    @NotEmpty
    @Email
    @Size (max = 30)
    private String profession;

    @NotNull
    @NotEmpty
    @Email
    @Size (max = 10)
    private String cuil;

    public Teacher () {
    }

    public Teacher (String name, String lastName, String email,
                    String mobile, String birthDate, String profession, String title, String cuil) {

        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.birthDate = birthDate;
        this.profession=profession;
        this.title=title;
        this.cuil=cuil;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }
}

