package com.event.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PersonDTO {

    private Integer id;
    @NotNull
    private String firstName;
    private String lastName;
    @NotNull
    @Pattern(regexp = "[0-9]{10}", message = "Numar invalid!")
    private String phoneNumber;
    private String email;
}
