package com.timothy.libraree.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class UserRequest {
    @NotNull(message = "NIK tidak boleh kosong")
    @JsonProperty("NIK")
    private String NIK;
    @NotNull(message = "Name tidak boleh kosong")
    private String name;
    @NotNull(message = "Email tidak boleh kosong")
    private String email;
}
