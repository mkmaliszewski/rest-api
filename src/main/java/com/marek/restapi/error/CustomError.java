package com.marek.restapi.error;

public class CustomError {
    String errorMessage;
    
    public CustomError(String errorMessage){
        this.errorMessage = errorMessage;
    }
    
    public String getErrorMessage(){
        return errorMessage;
    }
}