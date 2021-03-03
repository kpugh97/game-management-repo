package com.tp.gamemanagementsystem.exceptions;

public class InvalidRatingException extends Exception{
    public InvalidRatingException(String msg)
    {

        super(msg);
    }

    public InvalidRatingException(String msg, Throwable innerException)
    {

        super(msg, innerException);
    }
}
