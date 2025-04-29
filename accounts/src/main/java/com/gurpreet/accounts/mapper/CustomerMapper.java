package com.gurpreet.accounts.mapper;

import com.gurpreet.accounts.dto.CustomerDto;
import com.gurpreet.accounts.entity.Customer;

public class CustomerMapper {
    /**
     * Maps a {@link Customer} object to a {@link CustomerDto} object.
     *
     * @param customer the object to be mapped
     * @param customerDto the object to map to
     * @return the mapped object
     */
    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    /**
     * Maps a {@link CustomerDto} object to a {@link Customer} object.
     *
     * @param customerDto the object to be mapped
     * @param customer the object to map to
     * @return the mapped object
     */
    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
