package com.carshop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "scheduleDate" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentCreateDto {

    @NotNull
    @FutureOrPresent
    private OffsetDateTime scheduleDate;
}
