package com.tiletocode.tdd.chap3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ExpireDateTest {

    private void assertExpireDate(PayData data, LocalDate expectedExpireDate) {
        ExpireDateCal cal = new ExpireDateCal();
        LocalDate realExpireDate = cal.calculateExpireDate(data);
        assertEquals(expectedExpireDate, realExpireDate);
    }

    @Test
    @DisplayName("만원 납부시 한달뒤가 만료일이 됨.")
    void standard() {
        assertExpireDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2022,2,15))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2022,3,15)
        );
        assertExpireDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2022,5,4))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2022,6,4)
        );
    }

    @Test
    @DisplayName("만료일이 +30일이 아님")
    void endOfTheMonth() {

        assertExpireDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2022,1,31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2022,2,28)
        );

        assertExpireDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2020,1,31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2020,2,29)
        );

        assertExpireDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2022,5,31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2022,6,30)
        );
    }
}
