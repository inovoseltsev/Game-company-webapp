package com.inovoseltsev.holloball.message;

public final class ErrorMessage {
    public static String getLoginError() {
        return "Login or password is incorrect";
    }
    public static String getBannedError() {
        return "Your account is locked";
    }
}
