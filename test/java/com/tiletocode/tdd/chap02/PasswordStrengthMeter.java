package com.tiletocode.tdd.chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if (s == null)
            return PasswordStrength.INVALID;

        boolean lengthEnough = s.length() >= 8;
        boolean containsNum = checkNumber(s);
        boolean containsUpp = checkUppercase(s);

        if(lengthEnough && !containsNum && !containsUpp)
            return PasswordStrength.WEAK;
        if(!lengthEnough && containsNum && !containsUpp)
            return PasswordStrength.WEAK;
        if(!lengthEnough && !containsNum && containsUpp)
            return PasswordStrength.WEAK;
        if (!lengthEnough)
            return PasswordStrength.NORMAL;
        if (!containsNum)
            return PasswordStrength.NORMAL;
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
