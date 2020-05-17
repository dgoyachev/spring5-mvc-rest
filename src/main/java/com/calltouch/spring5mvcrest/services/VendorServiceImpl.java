package com.calltouch.spring5mvcrest.services;

import com.calltouch.spring5mvcrest.api.v1.mapper.VendorMapper;
import com.calltouch.spring5mvcrest.api.v1.model.VendorDTO;
import com.calltouch.spring5mvcrest.controllers.v1.VendorController;
import com.calltouch.spring5mvcrest.domain.Vendor;
import com.calltouch.spring5mvcrest.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by morgan on 18.05.2020
 */
@Service
public class VendorServiceImpl implements VendorService {

    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendorMapper::vendorToVendorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorByName(String name) {
        return vendorMapper.vendorToVendorDTO(
                vendorRepository.findByName(name)
                        .orElseThrow(ResourceNotFoundException::new)
        );
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorMapper.vendorToVendorDTO(
                vendorRepository.findById(id)
                        .orElseThrow(ResourceNotFoundException::new)
        );
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return saveAndReturnDTO(vendorMapper.vendorDTOToVendor(vendorDTO));
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        vendor.setId(id);
        return saveAndReturnDTO(vendor);
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        vendorDTO.setVendorUrl(VendorController.BASE_URL + savedVendor.getId());
        return vendorDTO;
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }
}
