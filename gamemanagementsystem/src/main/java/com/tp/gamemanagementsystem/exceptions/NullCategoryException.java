package com.tp.gamemanagementsystem.exceptions;

public class NullCategoryException extends Exception{

    public NullCategoryException(String msg)
    {

        super(msg);
    }

    public NullCategoryException(String msg, Throwable innerException)
    {

        super(msg, innerException);
    }
}
