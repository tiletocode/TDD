package com.tiletocode.tdd.chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordStrengthMeterTest {

    private final PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength str) {
        PasswordStrength result = meter.meter(password);
        assertEquals(str, result);
    }

    @Test
    @DisplayName("모든 규칙을 충족하는 경우")
    void Strong() {
        assertStrength("12!@abAB", PasswordStrength.STRONG);
    }

    @Test
    @DisplayName("길이 8자 미만, 나머지 조건 충족")
    void normal_1() {
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("숫자 미포함, 나머지 조건 충족")
    void normal_2() {
        assertStrength("qwerAS!@", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("대문자 미포함, 나머지 조건 충족")
    void normal_3() {
        assertStrength("1q!2w@3e#", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("길이가 8자 이상인 조건만 충족")
    void weak_1() {
        assertStrength("qwertasdf", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("숫자 포함 조건만 충족")
    void weak_2() {
        assertStrength("1234", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("대문자 포함 조건만 충족")
    void weak_3() {
        assertStrength("QWERTY", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("아무 조건도 충족하지 않은 경우")
    void weak_4() {
        assertStrength("abc", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("입력값이 null일 경우")
    void inputNull() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    @DisplayName("입력값이 공백일 경우")
    void inputBlank() {
        assertStrength("", PasswordStrength.INVALID);
    }
}