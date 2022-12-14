package ru.bspl.pet.tradingmarket.utils;

public class OrdersIsCreatedException extends Exception{

    public OrdersIsCreatedException() {
    }

    public OrdersIsCreatedException(String message) {
        super(message);
    }

    public OrdersIsCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrdersIsCreatedException(Throwable cause) {
        super(cause);
    }

    public OrdersIsCreatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
