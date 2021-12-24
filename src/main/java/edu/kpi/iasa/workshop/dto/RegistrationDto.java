package edu.kpi.iasa.workshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class RegistrationDto {
    private String username;
    private String password;
    private String email;
    private int uploads;
    private int downloads;
}
