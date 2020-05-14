package com.calltouch.spring5mvcrest.services;

import com.calltouch.spring5mvcrest.api.v1.mapper.CustomerMapper;
import com.calltouch.spring5mvcrest.api.v1.model.CustomerDTO;
import com.calltouch.spring5mvcrest.domain.Customer;
import com.calltouch.spring5mvcrest.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerMapper, customerRepository);
    }

    @Test
    void getAllCustomers() {
        when(customerRepository.findAll()).thenReturn(
                List.of(
                        new Customer(1L, "Michale", "Weston"),
                        new Customer(2L, "Sam", "Axe")
                )
        );

        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();
        assertEquals(2, customerDTOS.size());
    }

    @Test
    void getCustomerByLastName() {
        when(customerRepository.findByLastName(anyString())).thenReturn(Optional.of(new Customer(1L, "Michale", "Weston")));
        CustomerDTO customerDTO = customerService.getCustomerByLastName("Michale");
        assertEquals("Michale", customerDTO.getFirstName());
    }

    @Test
    void getCustomerById() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(new Customer(1L, "Michale", "Weston")));
        CustomerDTO customerDTO = customerService.getCustomerById(1L);
        assertEquals("Michale", customerDTO.getFirstName());
    }


    @Test
    void createNewCustomer() {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        //then
        assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
        assertEquals("/api/v1/customer/1", savedDto.getCustomerUrl());
    }
}