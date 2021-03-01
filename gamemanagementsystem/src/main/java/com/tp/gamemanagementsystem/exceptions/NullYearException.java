package com.tp.gamemanagementsystem.exceptions;

public class NullYearException extends Exception{
    public NullYearException(String msg)
    {

        super(msg);
    }

    public NullYearException(String msg, Throwable innerException)
    {

        super(msg, innerException);
    }
}
