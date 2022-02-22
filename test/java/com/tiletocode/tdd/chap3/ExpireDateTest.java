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

    @Test
    @DisplayName("말일납부후 추가로 만원납부시 다음 만료일도 말일")
    void twoMonth_1() {
        PayData data = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(10_000)
                .build();

        assertExpireDate(data, LocalDate.of(2019, 3, 31));

        PayData data2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 30))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(10_000)
                .build();

        assertExpireDate(data2, LocalDate.of(2019, 3, 30));

        PayData data3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 5, 31))
                .billingDate(LocalDate.of(2019, 6, 30))
                .payAmount(10_000)
                .build();

        assertExpireDate(data3, LocalDate.of(2019, 7, 31));
    }

    @Test
    @DisplayName("이만원 이상 납부했을때 만료일")
    void payMultiple() {
        assertExpireDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2019, 5, 1));

         assertExpireDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2019, 6, 1));

    }

    @Test
    @DisplayName("만료일이 +30일이 아닐때 2만원이상 납부")
    void endOfTheMonthMuliple() {
        assertExpireDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2022, 1, 31))
                        .billingDate(LocalDate.of(2022, 2, 28))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2022, 4, 30)
        );
    }

}
