package com.gurpreet.accounts.service.impl;

import com.gurpreet.accounts.constants.AccountsConstants;
import com.gurpreet.accounts.dto.AccountsDto;
import com.gurpreet.accounts.dto.CustomerDto;
import com.gurpreet.accounts.entity.Accounts;
import com.gurpreet.accounts.entity.Customer;
import com.gurpreet.accounts.exception.CustomerAlreadyExistsException;
import com.gurpreet.accounts.exception.ResourceNotFoundException;
import com.gurpreet.accounts.mapper.AccountsMapper;
import com.gurpreet.accounts.mapper.CustomerMapper;
import com.gurpreet.accounts.repository.AccountsRepository;
import com.gurpreet.accounts.repository.CustomerRepository;
import com.gurpreet.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    /**
     * This method is used to create a new account for the given customer.
     *
     * @param customerDto
     * The customer object that contains the customer details.
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());
        Optional<Customer> optionalCustomer= customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent())
        {
            //custom validation exception
            throw new CustomerAlreadyExistsException("Customer Already registered with given mobileNumber"+ customerDto.getMobileNumber());
        }
        //customer.setCreatedAt(LocalDateTime.now());
        //customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
       // newAccount.setCreatedAt(LocalDateTime.now());
       // newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }

    /**
     * Fetches the account details for a customer using the provided mobile number.
    *
    * This method first retrieves the customer information using the mobile number.
    * If the customer is found, it then retrieves the associated account details using the customer's ID.
    * If either the customer or the account is not found, a ResourceNotFoundException is thrown.
    *
    * @param mobileNumber the mobile number of the customer whose account details are to be fetched
    * @return a CustomerDto object containing the customer's details along with their account information
    * @throws ResourceNotFoundException if the customer or account is not found for the given mobile number
    */

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    /**
     * Updates the account details of a customer.
     *
     * This method first retrieves the account to be updated using the provided account number.
     * If the account is found, it then retrieves the associated customer information using the customer's ID.
     * If either the customer or the account is not found, a ResourceNotFoundException is thrown.
     * The customer details are then updated with the given information.
     * The method returns true if the account is updated successfully, false otherwise.
     *
     * @param customerDto
     * The customer object that contains the customer details to be updated.
     * @return true if the account is updated successfully, false otherwise.
     * @throws ResourceNotFoundException if the customer or account is not found for the given account number
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;

        AccountsDto accountsDto = customerDto.getAccountsDto();

        if(accountsDto != null)
        {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    ()->new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );

            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();

            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    ()-> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );

            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * Deletes the account associated with the given mobile number.
     *
     * This method first retrieves the customer using the given mobile number.
     * If the customer is found, it then deletes the associated account and the customer.
     * If the customer is not found, a ResourceNotFoundException is thrown.
     *
     * @param mobileNumber
     * The mobile number of the customer whose account is to be deleted.
     * @return true if the account is deleted successfully, false otherwise.
     * @throws ResourceNotFoundException if the customer is not found for the given mobile number
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());

        return true;
    }
}
