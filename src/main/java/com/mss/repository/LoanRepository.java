package com.mss.repository;

import com.mss.dto.LoanSummary;
import com.mss.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>, JpaSpecificationExecutor<Loan> {
    @Query("SELECT new com.mss.dto.LoanSummary(" +
            "e.lenderId, e.interestPerDay, e.customerId, " +
            "SUM(e.remainingAmount), SUM(e.interestPerDay * e.remainingAmount), SUM(e.penaltyPerDay * e.remainingAmount)) " +
            "FROM Loan e " +
            "GROUP BY e.lenderId, e.interestPerDay, e.customerId")
    List<LoanSummary> aggregateRemainingAmountInterestPenalty();
}