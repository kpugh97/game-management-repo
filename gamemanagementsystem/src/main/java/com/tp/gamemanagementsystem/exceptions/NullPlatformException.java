package com.tp.gamemanagementsystem.exceptions;

public class NullPlatformException extends Exception{

    public NullPlatformException(String msg)
    {

        super(msg);
    }

    public NullPlatformException(String msg, Throwable innerException)
    {

        super(msg, innerException);
    }
}
