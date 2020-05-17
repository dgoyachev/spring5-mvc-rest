package com.calltouch.spring5mvcrest.services;

import com.calltouch.spring5mvcrest.api.v1.mapper.VendorMapper;
import com.calltouch.spring5mvcrest.api.v1.model.VendorDTO;
import com.calltouch.spring5mvcrest.domain.Vendor;
import com.calltouch.spring5mvcrest.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class VendorServiceImplTest {

    @Mock
    VendorRepository vendorRepository;

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    VendorService vendorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorMapper, vendorRepository);
    }

    @Test
    void getAllVendors() {
        when(vendorRepository.findAll()).thenReturn(
                List.of(
                        new Vendor(1L, "Vendor 1"),
                        new Vendor(2L, "Vendor 2")
                )
        );

        List<VendorDTO> vendorDTOList = vendorService.getAllVendors();
        assertEquals(2, vendorDTOList.size());
    }

    @Test
    void getVendorByName() {
        when(vendorRepository.findByName(anyString()))
                .thenReturn(Optional.of(new Vendor(1L, "Vendor 1")));
        VendorDTO vendorDTO = vendorService.getVendorByName("Vendor 1");
        assertEquals("Vendor 1", vendorDTO.getName());
    }

    @Test
    void getVendorById() {
        when(vendorRepository.findById(anyLong()))
                .thenReturn(Optional.of(new Vendor(1L, "Vendor 1")));
        VendorDTO vendorDTO = vendorService.getVendorById(1L);
        assertEquals("Vendor 1", vendorDTO.getName());
    }

    @Test
    void createNewVendor() {
        VendorDTO vendorDTO = new VendorDTO("Vendor 1" , "/api/v1/vendor/1");

        Vendor savedVendor = new Vendor(1L, "Vendor 1");

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO savedVendorDTO = vendorService.createNewVendor(vendorDTO);

        assertEquals(vendorDTO.getName(), savedVendorDTO.getName());
        assertEquals("/api/v1/vendor/1", savedVendorDTO.getVendorUrl());
    }

    @Test
    void saveVendorByDTO() {
        VendorDTO vendorDTO = new VendorDTO("Vendor 1" , "/api/v1/vendor/1");

        Vendor savedVendor = new Vendor(1L, "Vendor 1");

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO savedVendorDTO = vendorService.saveVendorByDTO(1L, vendorDTO);

        assertEquals(vendorDTO.getName(), savedVendorDTO.getName());
        assertEquals("/api/v1/vendor/1", savedVendorDTO.getVendorUrl());
    }

    @Test
    void patchVendor() {
    }

    @Test
    void deleteVendorById() {
        vendorService.deleteVendorById(1L);
        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}