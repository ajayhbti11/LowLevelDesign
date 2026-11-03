package org.basicoops.interfc;

public class Payment implements Bank{

    @Override
    public void payAmount(double amount) {

        System.out.println("Pay amount is :"+amount);

    }

}
