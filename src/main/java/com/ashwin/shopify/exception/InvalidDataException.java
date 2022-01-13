package com.ashwin.shopify.exception;

/**
 * Represents exception thrown when invalid data is entered
 * @author Ashwin Raghunath
 */
public class InvalidDataException extends Exception{
    public InvalidDataException(String message) {
        super(message);
    }
}
