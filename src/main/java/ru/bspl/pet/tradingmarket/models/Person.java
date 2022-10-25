package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@SequenceGenerator(name="PERSON", sequenceName="PERSON_GENERATOR")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COUNTERPARTY")
    private Long id;

    @NotEmpty(message = "Имя не должно быть пустым.")
    @Size(min=2, max=100, message = "Длинна имени должна быть от 2 до 100 символов")
    private String username;

    private String role;
    private String password;

    @Email
    private String eMail;

    public Person(Long id, String name, String password, String eMail, String role) {
        this.id = id;
        this.username = name;
        this.role = role;
        this.password = password;
        this.eMail = eMail;
    }

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}
