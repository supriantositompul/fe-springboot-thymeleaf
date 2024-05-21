package com.clientside.mbkm.Models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountryResponse {
    private Integer regionId;
    private String regionName;
    private Integer countryId;
    private String countryCode;
    private String countryName;
}
