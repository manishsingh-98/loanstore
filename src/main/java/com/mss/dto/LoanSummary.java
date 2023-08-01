package com.mss.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class LoanSummary {
    private Long lenderId;
    private Double interestPerDay;
    private Long customerId;
    private Double totalRemainingAmount;
    private Double totalInterest;
    private Double totalPenalty;
}
