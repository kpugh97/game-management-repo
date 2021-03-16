package com.tp.gamemanagementsystem.exceptions;

public class InvalidUsernameException extends Exception{

    public InvalidUsernameException(String msg)
    {

        super(msg);
    }

    public InvalidUsernameException(String msg, Throwable innerException)
    {

        super(msg, innerException);
    }
}
