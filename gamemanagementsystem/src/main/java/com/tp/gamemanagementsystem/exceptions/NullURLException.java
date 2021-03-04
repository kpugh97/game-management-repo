package com.tp.gamemanagementsystem.exceptions;

public class NullURLException extends Exception{
    public NullURLException(String msg)
    {

        super(msg);
    }

    public NullURLException(String msg, Throwable innerException)
    {

        super(msg, innerException);
    }
}
