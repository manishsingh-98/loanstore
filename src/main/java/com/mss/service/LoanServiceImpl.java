package com.mss.service;

import com.mss.dto.LoanDto;
import com.mss.dto.LoanSummary;
import com.mss.entity.Loan;
import com.mss.repository.LoanRepository;
import com.mss.util.LoanUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService{


    @Autowired
    private LoanRepository loanRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addLoan(LoanDto loanDto) {

        LoanUtils.validateLoan(loanDto);

        Loan loan = Loan.builder()
                .amount(loanDto.getAmount())
                .customerId(loanDto.getCustomerId())
                .lenderId(loanDto.getLenderId())
                .dueDate(loanDto.getDueDate())
                .interestPerDay(loanDto.getInterestPerDay())
                .remainingAmount(loanDto.getRemainingAmount())
                .paymentDate(loanDto.getPaymentDate())
                .penaltyPerDay(loanDto.getPenaltyPerDay()).build();

        loanRepository.save(loan);

    }

    @Override
    public List<LoanDto> getAllLoans() {
        List<Loan> loanList = loanRepository.findAll();

        return loanList.stream()
                .map(LoanUtils::mapLoanToDto).collect(Collectors.toList());
    }

    @Override
    public LoanDto getLoan(Long loanId) {
        Optional<Loan> loanOptional = loanRepository.findById(loanId);
        return loanOptional.map(LoanUtils::mapLoanToDto).orElse(null);
    }

    @Override
    public LoanDto updateLoanStatus(Boolean cancelStatus, Long loanId) {

        Optional<Loan> loanOptional = loanRepository.findById(loanId);

        if (loanOptional.isPresent()) {
            loanOptional.get().setCancel(cancelStatus);
            return LoanUtils.mapLoanToDto(loanOptional.get());
        }

        return null;
    }

    @Override
    public List<LoanSummary> getLoansByCustomerAndLender() {
        return loanRepository.aggregateRemainingAmountInterestPenalty();
    }
}
