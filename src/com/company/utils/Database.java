package com.company.utils;

import java.sql.*;

public class Database {

    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:" + fileName;

        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("tabel neceart");
        }
    }

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:database.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS runBob (\n"
                + " nume text NOT NULL,\n "
                + " level integer NOT NULL,\n"
                + " secRamase integer NOT NULL,\n"
                + " tufeDistruse int NOT NULL\n"
                + ");";

        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert(String nume, int level, int secRamase, int tufeDistruse) {
        String sql = "INSERT INTO runBob(nume, level, secRamase, tufeDistruse) VALUES(?,?,?,?)";

        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nume);
            pstmt.setDouble(2, level);
            pstmt.setDouble(3, secRamase);
            pstmt.setDouble(4, tufeDistruse);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void dropTable(){
        String url = "jdbc:sqlite:database.db";

        // SQL statement for creating a new table
        String sql = "DROP TABLE IF EXISTS 'runBob' ";

        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public String selectAll() {
        String sql = "SELECT * FROM runBob";
        String rez = null;

        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                rez= rs.getString("nume") + " " +
                        rs.getInt("level") + " " +
                        rs.getInt("secRamase") + " " +
                        rs.getInt("tufeDistruse");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rez;
    }
}
