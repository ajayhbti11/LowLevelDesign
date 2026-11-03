package org.basicoops.dry.withdry;

public class Validator {

    public boolean validateEmail(String email){

        return email!=null && !email.isEmpty() && email.contains("@");

    }
    public boolean validatePassword(String password){

        return password!=null && !password.isEmpty() && password.length()>=6;


    }
}
