package com.calltouch.spring5mvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by morgan on 12.05.2020
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private String firstName;

    private String lastName;

    @JsonProperty("customer_url")
    private String customerUrl;
}
