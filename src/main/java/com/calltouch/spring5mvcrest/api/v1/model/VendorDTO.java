package com.calltouch.spring5mvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by morgan on 17.05.2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {
    @ApiModelProperty(value = "Vendor name", required = true)
    private String name;

    @JsonProperty("vendor_url")
    private String vendorUrl;
}
