package org.designpattern.behavioral.stretegypattern.withstretegy;

public class PaypalPayment implements PaymentStrategy{
    @Override
    public void pay(int amount) {
        System.out.println("Paid ₹" + amount + " using PayPal");
    }
}
