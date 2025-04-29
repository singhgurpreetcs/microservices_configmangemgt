package com.gurpreet.loans.repository;

import com.gurpreet.loans.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Long> {

    /**
     * Finds a loan by the given mobile number.
     *
     * @param mobileNumber
     *            the mobile number of the customer for whom the loan is to be
     *            retrieved
     * @return an Optional containing the loan if found, or an empty Optional if
     *         no loan is associated with the given mobile number
     */
    Optional<Loans> findByMobileNumber(String mobileNumber);

    /**
     * Finds a loan by the given loan number.
     *
     * @param loanNumber
     *            the loan number of the loan to be retrieved
     * @return an Optional containing the loan if found, or an empty Optional if
     *         no loan is associated with the given loan number
     */
    Optional<Loans> findByLoanNumber(String loanNumber);
}
