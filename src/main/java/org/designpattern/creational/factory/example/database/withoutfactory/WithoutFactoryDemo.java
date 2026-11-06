package org.designpattern.creational.factory.example.database.withoutfactory;

/**
 * ❌ Demo — WITHOUT Factory Pattern (Database example)
 *
 * Caller knows about every concrete class.
 * Adding a new DB type forces changes in every place that does this switch.
 */
public class WithoutFactoryDemo {

    public static void main(String[] args) {
        String dbType = "mysql";

        // ❌ Tight coupling to concrete classes
        Database db;
        if (dbType.equals("mysql")) {
            db = new MySQLDatabase();
        } else if (dbType.equals("psql")) {
            db = new PostgresDatabase();
        } else {
            throw new IllegalArgumentException("Unknown db type");
        }

        db.connect();
    }
}
