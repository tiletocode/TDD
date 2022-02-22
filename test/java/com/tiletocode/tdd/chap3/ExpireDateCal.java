package com.tiletocode.tdd.chap3;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpireDateCal {
    public LocalDate calculateExpireDate(PayData data) {

        int addedMonth = data.getPayAmount() / 10_000;

        if (data.getFirstBillingDate() != null) {
            LocalDate candidateExp = data.getBillingDate().plusMonths(addedMonth);
            if (data.getFirstBillingDate().getDayOfMonth() != candidateExp.getDayOfMonth()) {
                if(YearMonth.from(candidateExp).lengthOfMonth() < data.getFirstBillingDate().getDayOfMonth()) {
                    return candidateExp.withDayOfMonth(YearMonth.from(candidateExp).lengthOfMonth());
                }
                return candidateExp.withDayOfMonth(data.getFirstBillingDate().getDayOfMonth());
            }
        }
        return data.getBillingDate().plusMonths(addedMonth);
    }
}
