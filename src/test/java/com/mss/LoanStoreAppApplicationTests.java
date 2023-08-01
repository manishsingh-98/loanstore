package com.mss;

import com.mss.dto.LoanDto;
import com.mss.dto.LoanSummary;
import com.mss.exceptions.ErrorResponseException;
import com.mss.repository.LoanRepository;
import com.mss.service.LoanServiceImpl;
import com.mss.util.LoanUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class LoanStoreAppApplicationTests {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanServiceImpl loanService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testValidateLoan_ValidLoanDto_NoException() {

        LoanDto validLoanDto = new LoanDto();
        validLoanDto.setDueDate(LocalDate.of(2023, 8, 15));
        validLoanDto.setPaymentDate(LocalDate.of(2023, 8, 10));


        assertDoesNotThrow(() -> LoanUtils.validateLoan(validLoanDto));
    }

    @Test
    public void testValidateLoan_InvalidLoanDto_ExceptionThrown() {

        LoanDto invalidLoanDto = new LoanDto();
        invalidLoanDto.setDueDate(LocalDate.of(2023, 8, 10));
        invalidLoanDto.setPaymentDate(LocalDate.of(2023, 8, 15));

        ErrorResponseException exception = assertThrows(ErrorResponseException.class,
                () -> LoanUtils.validateLoan(invalidLoanDto));
        assertEquals("loan payment date must be before due date", exception.getMessage());
    }


    @Test
    public void testGetLoansByCustomerAndLender() {

        LoanSummary loanSummary = new LoanSummary();
        loanSummary.setLenderId(123L);
        loanSummary.setInterestPerDay(0.05);
        loanSummary.setCustomerId(456L);
        loanSummary.setTotalRemainingAmount(1000.0);
        loanSummary.setTotalInterest(50.0);
        loanSummary.setTotalPenalty(10.0);

        when(loanRepository.aggregateRemainingAmountInterestPenalty())
                .thenReturn(Collections.singletonList(loanSummary));

        List<LoanSummary> result = loanService.getLoansByCustomerAndLender();

        assertEquals(1, result.size());
        LoanSummary retrievedLoanSummary = result.get(0);
        assertEquals(123L, retrievedLoanSummary.getLenderId());
        assertEquals(0.05, retrievedLoanSummary.getInterestPerDay(), 0.001);
        assertEquals(456L, retrievedLoanSummary.getCustomerId());
        assertEquals(1000.0, retrievedLoanSummary.getTotalRemainingAmount(), 0.001);
        assertEquals(50.0, retrievedLoanSummary.getTotalInterest(), 0.001);
        assertEquals(10.0, retrievedLoanSummary.getTotalPenalty(), 0.001);
    }


}
