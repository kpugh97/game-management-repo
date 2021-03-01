package com.tp.gamemanagementsystem.exceptions;

public class NullTitleException extends Exception{

    public NullTitleException(String msg)
    {

        super(msg);
    }

    public NullTitleException(String msg, Throwable innerException)
    {

        super(msg, innerException);
    }
}
