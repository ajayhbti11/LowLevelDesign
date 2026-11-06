package org.designpattern.creational.factory.example.database.withfactory;

/**
 * ✅ Demo — WITH Factory Pattern (Database example)
 *
 * Caller depends only on the Database interface.
 * Adding a new DB type (e.g. MongoDB) = register it in DatabaseFactory.
 * This file never needs to change. ✅
 */
public class FactoryDemo {

    public static void main(String[] args) {

        // ✅ No concrete class reference in the caller
        Database mysql   = DatabaseFactory.create("mysql");
        Database postgres = DatabaseFactory.create("psql");

        mysql.connect();
        postgres.connect();
    }
}
