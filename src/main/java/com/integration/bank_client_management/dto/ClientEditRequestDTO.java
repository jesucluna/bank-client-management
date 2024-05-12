package com.integration.bank_client_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientEditRequestDTO {
    @Min(0)
    @NotNull
    private  Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotNull
    private String address;
    @NotNull
    private String phone;
    @Email
    private String email;
}
