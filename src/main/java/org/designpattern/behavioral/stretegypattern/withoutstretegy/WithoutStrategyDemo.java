package org.designpattern.behavioral.stretegypattern;

/**
 * ❌ Demo — WITHOUT Strategy Pattern
 *
 * <p>Illustrates the problem that the Strategy pattern solves.</p>
 *
 * <pre>
 * Problem
 * -------
 *  • PaymentService owns every algorithm. Adding "crypto" means modifying
 *    a class that already works — risk of regression.
 *  • The caller must pass a magic string ("credit", "paypal" …) — fragile
 *    and not type-safe.
 *  • Cannot inject or mock individual payment behaviour in tests.
 *
 * Fix → see withstretegy package
 *  • PaymentStrategy  : the common interface (one method per algorithm)
 *  • CreditCardPayment / PaypalPayment / UPIPayment : concrete strategies
 *  • PaymentContext   : the context that delegates to whichever strategy
 *                       was set at runtime — zero if-else needed.
 * </pre>
 */
public class WithoutStrategyDemo {

    public static void main(String[] args) {

        PaymentService service = new PaymentService();

        // Works fine — but any new method requires editing PaymentService
        service.pay("credit",  500);
        service.pay("paypal",  1200);
        service.pay("upi",    300);

        // Unknown method — falls through to the catch-all branch
        service.pay("crypto", 750);
    }
}
