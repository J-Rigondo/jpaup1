package com.jpa.jpaup1.exception;

public class NotEnoughStockExcetpion extends RuntimeException {

    //alt + insert override method

    public NotEnoughStockExcetpion() {
        super();
    }

    public NotEnoughStockExcetpion(String message) {
        super(message);
    }

    public NotEnoughStockExcetpion(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStockExcetpion(Throwable cause) {
        super(cause);
    }

    protected NotEnoughStockExcetpion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
