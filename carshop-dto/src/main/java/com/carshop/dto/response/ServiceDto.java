package com.carshop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.carshop.dto.enums.ServiceStatusDto;
import com.carshop.dto.enums.ServiceTypeDto;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "uuid", "createDate", "updateDate", "type", "status" })
public class ServiceDto {

    private UUID uuid;

    private OffsetDateTime createDate;

    private OffsetDateTime updateDate;

    private ServiceTypeDto type;

    private ServiceStatusDto status;

}
