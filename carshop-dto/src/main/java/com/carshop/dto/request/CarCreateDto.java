package com.carshop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "make", "model", "year" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarCreateDto {

    @NotNull
    @NotBlank
    private String make;

    @NotNull
    @NotBlank
    private String model;

    @NotNull
    @Positive
    private Integer year;
}
