package org.basicoops.interfc;

 interface Bank {

     void payAmount(double amount);

     default double payCharges(double payCharges){

         System.out.println("For Bank it is zero");
         return payCharges;

     }

 }
