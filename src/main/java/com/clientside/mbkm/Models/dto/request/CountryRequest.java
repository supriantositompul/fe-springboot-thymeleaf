package com.clientside.mbkm.Models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryRequest {
    private String code;
    private String name;
    public Integer regionId;
}