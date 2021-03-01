package com.tp.gamemanagementsystem.exceptions;

public class InvalidIDException extends Exception{

    public InvalidIDException(String msg)
    {

        super(msg);
    }

    public InvalidIDException(String msg, Throwable innerException)
    {

        super(msg, innerException);
    }

}
