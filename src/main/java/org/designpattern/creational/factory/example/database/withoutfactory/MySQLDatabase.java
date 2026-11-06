package org.designpattern.creational.factory.example.database.withoutfactory;

public class MySQLDatabase implements Database {
    @Override
    public void connect() {
        System.out.println("Connected to MySQL DB");
    }
}
