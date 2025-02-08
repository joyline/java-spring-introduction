package com.example.introduction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateUserRequestRecord(
    @NotBlank(message = "fullName is mandatory")
    String fullName,

    @NotBlank
    String nickName,

    @NotBlank
    String email,

    @NotBlank
    String address,

    String city
) {
}
