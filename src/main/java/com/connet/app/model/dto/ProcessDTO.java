package com.connet.app.model.dto;

import com.connet.app.model.entity.Process;
import com.connet.app.model.enumeration.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProcessDTO implements Serializable {

    private static final long serialVersionUID = 9222359516166159054L;

    @JsonProperty("process_id")
    private UUID processId;

    @JsonProperty("installer_id")
    private Long installerId;

    @JsonProperty("client_id")
    private UUID clientId;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("final_price")
    private String finalPrice;

    @JsonProperty("final_distance")
    private String finalDistance;

    @JsonProperty("plan_id")
    private Long planId;

    @JsonProperty("rating")
    private Float rating;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonProperty("data")
    private LocalDate date;

    public static ProcessDTO convert(Process process) {
        ProcessDTO processDTO = new ProcessDTO();
        BeanUtils.copyProperties(process, processDTO);
        return processDTO;
    }
}
