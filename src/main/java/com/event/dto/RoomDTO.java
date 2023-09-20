package com.event.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RoomDTO {

    private Integer id;
    @NotNull(message = "Adaugati numele salii!")
    @Pattern(regexp = "Sala [0-9]+", message = "Formatul nu a fost respectat!")
    private String name;
    @NotNull(message = "Adaugati numarul de locuri!")
    @Min(value = 100, message = "Numarul de locuri trebuie sa fie minim 100")
    @Max(value = 2000, message = "Numarul de locuri trebuie sa fie maxim 2000")
    private Integer numberOfSeats;
}

