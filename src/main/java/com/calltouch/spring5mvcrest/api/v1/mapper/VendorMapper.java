package com.calltouch.spring5mvcrest.api.v1.mapper;

import com.calltouch.spring5mvcrest.api.v1.model.VendorDTO;
import com.calltouch.spring5mvcrest.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);
    VendorDTO vendorToVendorDTO(Vendor vendor);
    Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}
