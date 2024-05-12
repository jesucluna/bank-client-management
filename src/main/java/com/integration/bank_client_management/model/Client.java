package com.integration.bank_client_management.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bank_client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
    private String email;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    public Client() {}

    public Client(String name, String lastName, String address, String phone, String email, LocalDate birthDate) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public LocalDate getBirthDate() { return birthDate; }

    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public LocalDateTime getCreationDate() { return creationDate; }

    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }

    @PrePersist
    public void prePersist() {
        // Set the creationDate to the current timestamp
        this.creationDate = LocalDateTime.now();
    }
}
