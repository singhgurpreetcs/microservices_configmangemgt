package com.gurpreet.loans.service.impl;

import com.gurpreet.loans.constants.LoansConstants;
import com.gurpreet.loans.dto.LoansDto;
import com.gurpreet.loans.entity.Loans;
import com.gurpreet.loans.exception.LoanAlreadyExistsException;
import com.gurpreet.loans.exception.ResourceNotFoundException;
import com.gurpreet.loans.mapper.LoansMapper;
import com.gurpreet.loans.repository.LoansRepository;
import com.gurpreet.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;

    /**
     * Creates a new loan for the given mobile number.
     *
     * This method first checks if a loan already exists for the given mobile number.
     * If a loan does exist, a LoanAlreadyExistsException is thrown.
     * If no loan exists, a new loan is created with the given mobile number and the default loan type and limit.
     *
     * @param mobileNumber the mobile number for whom the loan is to be created
     * @throws LoanAlreadyExistsException if a loan already exists for the given mobile number
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent())
        {
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber " + mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    /**
     * Creates a new loan object for the given mobile number.
     *
     * @param mobileNumber the mobile number for whom the loan is to be created
     * @return a Loans object with the given mobile number, a random loan number, the default loan type, and the default loan limit
     */
    private Loans createNewLoan(String mobileNumber){
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    /**
     * Fetches the loan details for the given mobile number.
     *
     * If a loan is associated with the given mobile number, the method returns a LoansDto object containing the loan details.
     * If no loan is associated with the given mobile number, the method throws a ResourceNotFoundException.
     *
     * @param mobileNumber the mobile number of the customer whose loan is to be fetched
     * @return a LoansDto object containing the loan details
     * @throws ResourceNotFoundException if no loan is associated with the given mobile number
     */
    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        return LoansMapper.maptoLoansDto(loans, new LoansDto());
    }

    /**
     * Updates the loan details for the given loan number.
     *
     * This method first checks if a loan exists for the given loan number.
     * If a loan does not exist, a ResourceNotFoundException is thrown.
     * If a loan does exist, the method updates the loan details with the given loan details and saves the loan.
     *
     * @param loansDto the LoansDto object containing the loan details to be updated
     * @return true if the loan is updated successfully, false otherwise
     * @throws ResourceNotFoundException if no loan is associated with the given loan number
     */
    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loans  = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                ()-> new ResourceNotFoundException("Loan", "LoanNumber", loansDto.getLoanNumber())
        );
        LoansMapper.mapToLoans(loansDto, loans);
        loansRepository.save(loans);
        return true;
    }

    /**
     * Deletes the loan associated with the given mobile number.
     *
     * This method first retrieves the loan associated with the given mobile number.
     * If the loan is not found, a ResourceNotFoundException is thrown.
     * If the loan is found, the method deletes the loan and returns true.
     *
     * @param mobileNumber the mobile number of the customer whose loan is to be deleted
     * @return true if the loan is deleted successfully, false otherwise
     * @throws ResourceNotFoundException if no loan is associated with the given mobile number
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }

}
