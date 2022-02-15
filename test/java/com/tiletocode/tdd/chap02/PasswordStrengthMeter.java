package com.tiletocode.tdd.chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty())
            return PasswordStrength.INVALID;

        int securityLevel = getSecurityLevel(s);
        if(securityLevel <= 1)
            return PasswordStrength.WEAK;
        if(securityLevel == 2)
            return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private int getSecurityLevel(String s) {
        int count = 0;
        if(s.length() >= 8)
            count++;
        if(checkNumber(s))
            count++;
        if(checkUppercase(s))
            count++;
        return count;
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
