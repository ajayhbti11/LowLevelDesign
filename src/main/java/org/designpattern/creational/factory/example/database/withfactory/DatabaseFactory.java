package org.designpattern.creational.factory.example.database.withfactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * ✅ Factory — all creation logic in one place.
 *
 * Uses a registry (Map + Supplier) so adding a new DB type only requires
 * registering it here — zero changes to callers.
 */
public class DatabaseFactory {

    private static final Map<String, Supplier<Database>> registry = new HashMap<>();

    static {
        registry.put("mysql", MySQLDatabase::new);
        registry.put("psql",  PostgresDatabase::new);
    }

    /** Register a new DB type at runtime without modifying this class. ✅ */
    public static void register(String name, Supplier<Database> supplier) {
        registry.put(name.toLowerCase(), supplier);
    }

    public static Database create(String dbType) {
        Supplier<Database> supplier = registry.get(dbType.toLowerCase());
        if (supplier == null) {
            throw new IllegalArgumentException("Unknown DB type: " + dbType);
        }
        return supplier.get();
    }
}
