package com.gurpreet.loans.service;

import com.gurpreet.loans.dto.LoansDto;

public interface ILoansService {

    /**
     * This method is used to create a new loan for the given mobile number.
     *
     * @param mobileNumber
     * The mobile number of the customer for whom the loan is to be created.
     */
    void createLoan(String mobileNumber);

    /**
     * This method is used to fetch the loan details for the given mobile number.
     *
     * @param mobileNumber
     *            the mobile number of the customer for whom the loan is to be
     *            fetched
     * @return a LoansDto object containing the loan details if found, or null if
     *         no loan is associated with the given mobile number
     */
    LoansDto fetchLoan(String mobileNumber);

    /**
     * This method is used to update the loan details for the given mobile number.
     *
     * @param loansDto
     *            the LoansDto object containing the loan details to be updated.
     * @return true if the loan is updated successfully, false otherwise.
     */
    boolean updateLoan(LoansDto loansDto);

    /**
     * Deletes the loan associated with the given mobile number.
     *
     * @param mobileNumber
     * The mobile number of the customer whose loan is to be deleted.
     * @return true if the loan is deleted successfully, false otherwise.
     */
    boolean deleteLoan(String mobileNumber);
}
