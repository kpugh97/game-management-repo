package com.tp.gamemanagementsystem.exceptions;

public class NullDescriptionException extends Exception{

    public NullDescriptionException(String msg)
    {

        super(msg);
    }

    public NullDescriptionException(String msg, Throwable innerException)
    {

        super(msg, innerException);
    }
}
