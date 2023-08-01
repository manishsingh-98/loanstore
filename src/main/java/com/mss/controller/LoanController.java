package com.mss.controller;

import com.mss.dto.LoanDto;
import com.mss.dto.LoanSummary;
import com.mss.service.LoanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoanController {


    @Autowired
    private LoanService loanService;

    @PostMapping("/add-loan")
    public void addCustomerLoan(@RequestBody @Valid LoanDto loanDto) {
        loanService.addLoan(loanDto);
    }

    @GetMapping("/get-loan")
    public LoanDto getLoanDetails(@Valid @NotNull(message = "loan Id can not be null") Long loanId) {
        return loanService.getLoan(loanId);
    }

    @GetMapping("/list-loans")
    public List<LoanDto> getAllLoans() {
        return loanService.getAllLoans();
    }

    @PutMapping("/update-loan")
    public LoanDto updateLoanStatus(@NotNull(message = "please provide a valid loan status") Boolean loanStatus, @NotNull(message = "please provide a valid loan id") Long loanId) {
        return loanService.updateLoanStatus(loanStatus, loanId);
    }

    @GetMapping("/get-customer-loan")
    public List<LoanSummary> getCustomerLoansSummary() {
        return loanService.getLoansByCustomerAndLender();
    }

}
