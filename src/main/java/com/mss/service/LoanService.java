package com.mss.service;

import com.mss.dto.LoanDto;
import com.mss.dto.LoanSummary;

import java.util.List;

public interface LoanService {

    public void addLoan(LoanDto loanDto) ;

    public List<LoanDto> getAllLoans();

    public LoanDto getLoan(Long loanId);

    public LoanDto updateLoanStatus(Boolean cancel, Long loanId);

    List<LoanSummary> getLoansByCustomerAndLender();
}