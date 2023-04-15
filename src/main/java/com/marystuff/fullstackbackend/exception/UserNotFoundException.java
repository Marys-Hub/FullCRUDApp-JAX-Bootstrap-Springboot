package com.marystuff.fullstackbackend.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("Could not find user with specified id " + id);
        /* When you call super("Could not find user with specified id" + id);
        in the UserNotFoundException constructor, you are calling the constructor
         of RuntimeException and passing in a message string as an argument.
          This message string will be displayed when an instance of
          UserNotFoundException is thrown and caught by an exception handler.*/
    }
}
