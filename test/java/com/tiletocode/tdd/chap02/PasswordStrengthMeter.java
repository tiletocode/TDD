package com.tiletocode.tdd.chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if (s.length() < 8) {
            return PasswordStrength.NORMAL;
        }
        boolean containsNum = checkNumber(s);

        if (!containsNum)
            return PasswordStrength.NORMAL;
        boolean containsUpp = checkUppercase(s);
        if (!containsUpp)
            return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;
    }

    private boolean checkNumber(String s) {
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                return true;
            }
        }
        return false;
    }

    private boolean checkUppercase(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }
}
