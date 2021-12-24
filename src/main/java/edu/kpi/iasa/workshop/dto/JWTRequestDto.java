package edu.kpi.iasa.workshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JWTRequestDto {
    String login;
    String password;
}
