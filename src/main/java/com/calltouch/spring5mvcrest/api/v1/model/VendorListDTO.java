package com.calltouch.spring5mvcrest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by morgan on 17.05.2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorListDTO {
    List<VendorDTO> vendors;
}
