package com.mss.util;

import com.mss.dto.LoanDto;
import com.mss.entity.Loan;
import com.mss.exceptions.ErrorResponseException;

import java.util.Objects;

public class LoanUtils {

    public static void validateLoan(LoanDto loanDto) {
        if (loanDto.getDueDate().isBefore(loanDto.getPaymentDate())) {
            throw new ErrorResponseException("loan payment date must be before due date");
        }
    }

    public static LoanDto mapLoanToDto(Loan loan) {
        return LoanDto.builder()
                .loanId(loan.getLoanId())
                .amount(loan.getAmount())
                .paymentDate(loan.getPaymentDate())
                .penaltyPerDay(loan.getPenaltyPerDay())
                .remainingAmount(loan.getRemainingAmount())
                .lenderId(loan.getLenderId())
                .dueDate(loan.getDueDate())
                .interestPerDay(loan.getInterestPerDay())
                .customerId(loan.getCustomerId())
                .cancel(!Objects.isNull(loan.getCancel()) && loan.getCancel()).build();
    }

}
