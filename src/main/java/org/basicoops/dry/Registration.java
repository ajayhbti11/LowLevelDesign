package org.basicoops.dry;

public class Registration {

    public boolean validate(String email, String password) {

        if (email == null || email.isEmpty())
            return false;
        if (password == null || password.isEmpty())
            return false;

        return true;

    }
}
