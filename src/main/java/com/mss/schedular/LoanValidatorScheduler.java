package com.mss.schedular;

import com.mss.entity.Loan;
import com.mss.repository.LoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
public class LoanValidatorScheduler {

    @Autowired
    private LoanRepository loanRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkLoanDates() {
        Specification<Loan> paymentDueLoanSpecification = (root, query, builder) ->
                builder.and(builder.lessThan(root.get("dueDate"), LocalDate.now()), builder.isFalse(root.get("cancel")));

        List<Loan> dueLoans = loanRepository.findAll(paymentDueLoanSpecification);

        dueLoans.forEach(x -> {
            log.debug("loan with Id " + x.getLoanId() + " amount was due on " + x.getDueDate());
        });

    }
}
