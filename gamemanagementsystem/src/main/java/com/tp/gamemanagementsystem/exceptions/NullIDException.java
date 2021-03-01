package com.tp.gamemanagementsystem.exceptions;

public class NullIDException extends Exception {

    public NullIDException(String msg)
    {

        super(msg);
    }

    public NullIDException(String msg, Throwable innerException)
    {

        super(msg, innerException);
    }
}
