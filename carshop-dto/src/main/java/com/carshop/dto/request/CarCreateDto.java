package com.carshop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "make", "model", "year" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarCreateDto {

    private String make;

    private String model;

    private Integer year;
}
