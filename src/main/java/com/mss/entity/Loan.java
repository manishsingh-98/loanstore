package com.mss.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @Column
    private Long customerId;

    @Column
    private Long lenderId;

    @Column
    private Double amount;

    @Column
    private Double remainingAmount;

    @Column
    private LocalDate paymentDate;

    @Column
    private Double interestPerDay;

    @Column
    private LocalDate dueDate;

    @Column
    private Double penaltyPerDay;

    @Column
    private Boolean cancel;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
