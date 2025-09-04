package com.store.migros.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String addressDetails;
    private String city;
    private String district;
    private String addressType;
}
