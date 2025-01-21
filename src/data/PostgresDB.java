package data;

import data.interfaces.IDB;

import java.sql.Connection;

public class PostgresDB implements IDB {
    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public void close() {

    }
}
