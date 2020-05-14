package com.calltouch.spring5mvcrest.services;

import com.calltouch.spring5mvcrest.api.v1.mapper.CustomerMapper;
import com.calltouch.spring5mvcrest.api.v1.model.CustomerDTO;
import com.calltouch.spring5mvcrest.domain.Customer;
import com.calltouch.spring5mvcrest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by morgan on 12.05.2020
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerByLastName(String lastName) {
        return customerMapper.customerToCustomerDTO(customerRepository.findByLastName(lastName).orElseThrow(() -> new IllegalArgumentException("No customer found with Last name " + lastName)));
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerMapper.customerToCustomerDTO(customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No customer found with id " + id)));
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

        return saveAndReturnDTO(customerMapper.customerDtoToCustomer(customerDTO));
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);

        returnDto.setCustomerUrl("/api/v1/customer/" + savedCustomer.getId());

        return returnDto;
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturnDTO(customer);
    }
}
