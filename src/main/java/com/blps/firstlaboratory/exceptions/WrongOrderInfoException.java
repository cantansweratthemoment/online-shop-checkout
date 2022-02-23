package com.blps.firstlaboratory.exceptions;

public class WrongOrderInfoException extends RuntimeException {
    public WrongOrderInfoException(String msg) {
        super(msg);
    }
}