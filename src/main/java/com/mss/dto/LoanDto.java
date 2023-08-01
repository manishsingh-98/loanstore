package com.mss.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanDto {

    private Long loanId;

    @NotNull(message = "customer Id can not be blank")
    private Long customerId;

    @NotNull(message = "lender Id can not be blank")
    private Long lenderId;

    @NotNull(message = "invalid amount")
    private Double amount;

    @NotNull(message = "invalid remaining amount")
    private Double remainingAmount;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate paymentDate;

    @NotNull(message = "invalid interest per day amount")
    private Double interestPerDay;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dueDate;

    @NotNull(message = "invalid penalty per day amount")
    private Double penaltyPerDay;

    private Boolean cancel;
}
