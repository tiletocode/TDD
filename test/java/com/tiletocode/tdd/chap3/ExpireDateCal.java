package com.tiletocode.tdd.chap3;

import java.time.LocalDate;

public class ExpireDateCal {
    public LocalDate calculateExpireDate(PayData data) {
        return data.getBillingDate().plusMonths(1);
    }
}
