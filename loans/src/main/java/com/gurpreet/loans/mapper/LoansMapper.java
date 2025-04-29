package com.gurpreet.loans.mapper;

import com.gurpreet.loans.dto.LoansDto;
import com.gurpreet.loans.entity.Loans;

public class LoansMapper {

    /**
     * Maps a Loans object to a LoansDto object.
     *
     * @param loans
     *            the Loans object to be mapped
     * @param loansDto
     *            the LoansDto object to map to
     * @return the mapped object
     */
    public static LoansDto maptoLoansDto(Loans loans, LoansDto loansDto){
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setLoanType(loans.getLoanType());
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setTotalLoan(loans.getTotalLoan());
        loansDto.setAmountPaid(loans.getAmountPaid());
        loansDto.setOutstandingAmount(loans.getOutstandingAmount());
        return loansDto;
    }

    /**
     * Maps a LoansDto object to a Loans object.
     *
     * @param loansDto
     *            the LoansDto object to be mapped
     * @param loans
     *            the Loans object to map to
     * @return the mapped object
     */
    public static Loans mapToLoans(LoansDto loansDto, Loans loans){
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setLoanType(loansDto.getLoanType());
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());
        return loans;
    }
}
