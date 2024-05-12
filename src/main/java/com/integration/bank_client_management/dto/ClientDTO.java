package com.integration.bank_client_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private  Integer id;
    private String name;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private LocalDate birthDate;
    private LocalDateTime creationDate;
}
