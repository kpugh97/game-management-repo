package com.tp.gamemanagementsystem.exceptions;

public class NullReviewException extends Exception {

    public NullReviewException(String msg)
    {

        super(msg);
    }

    public NullReviewException(String msg, Throwable innerException)
    {

        super(msg, innerException);
    }
}
