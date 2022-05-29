package com.connet.app.integration.installer.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InstallerDTO implements Serializable {

    private static final long serialVersionUID = -1115379078948536214L;

    @JsonProperty("intern_id")
    private UUID internId;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("rating")
    private Integer rating;

    @JsonProperty("price_per_km")
    private Integer pricePerKm;

    @JsonProperty("lat")
    private Double lat;

    @JsonProperty("lng")
    private Double lng;

    @JsonProperty("password")
    private String password;

    @JsonProperty("phone")
    private String phone;
}
