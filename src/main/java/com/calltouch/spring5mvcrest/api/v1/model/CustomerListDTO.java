package com.calltouch.spring5mvcrest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by morgan on 12.05.2020
 */

@Data
@AllArgsConstructor
public class CustomerListDTO {
    List<CustomerDTO> customers;
}
