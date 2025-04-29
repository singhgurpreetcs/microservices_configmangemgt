package com.gurpreet.accounts.service;

import com.gurpreet.accounts.dto.CustomerDto;

public interface IAccountsService {
    /**
     * This method is used to create a new account for the given customer.
     *
     * @param customerDto
     * The customer object that contains the customer details.
     */
    void createAccount(CustomerDto customerDto);

    /**
     * This method is used to fetch the account details for the given mobile number.
     *
     * @param mobileNumber
     * The mobile number of the customer.
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     * This method is used to update the account details for the given customer.
     *
     * @param customerDto
     * The customer object that contains the customer details.
     *
     * @return true if the account is updated successfully, false otherwise.
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * This method is used to delete the account associated with the given mobile number.
     *
     * @param mobileNumber
     * The mobile number of the customer whose account is to be deleted.
     *
     * @return true if the account is deleted successfully, false otherwise.
     */
    boolean deleteAccount(String mobileNumber);

}
