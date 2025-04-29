package com.gurpreet.accounts.repository;

import com.gurpreet.accounts.entity.Accounts;
import com.gurpreet.accounts.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    /**
     * Finds an account by the given customer ID.
     *
     * @param customerIdr the ID of the customer whose account is to be retrieved
     * @return an Optional containing the account if found, or an empty Optional if no account is associated with the given customer ID
     */
    Optional<Accounts> findByCustomerId(Long customerIdr);

    /**
     * Deletes an account associated with the given customer ID.
     *
     * @param customerId the ID of the customer whose account is to be deleted
     *
     * Transactional annotation is required for custom methods
     * @param customerId
     */
    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
