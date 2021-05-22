package com.company.utils;

import com.company.Game;
import com.company.states.OptionsState;

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
                + " tufeDistruse integer NOT NULL,\n"
                + " music integer NOT NULL,\n"
                + " easy integer NOT NULL,\n"
                + " harta string NOT NULL\n"
                + ");";

        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert(String nume) {
        String sql = "INSERT INTO runBob(nume, level, secRamase, tufeDistruse, music, easy, harta) VALUES(?,?,?,?,?,?,?)";

        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nume);
            pstmt.setDouble(2, Game.lvl);
            pstmt.setDouble(3, Game.secRamase);
            pstmt.setDouble(4, Game.killedMobs);
            pstmt.setDouble(5, OptionsState.music);
            pstmt.setDouble(6, OptionsState.easy);
            pstmt.setString(7, "res/worlds/world" + Game.lvl + ".txt");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insert() {
        String sql = "INSERT INTO runBob(nume, level, secRamase, tufeDistruse, music, easy, harta) VALUES(?,?,?,?,?,?,?)";

        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "player");
            pstmt.setDouble(2, Game.lvl);
            pstmt.setDouble(3, Game.secRamase);
            pstmt.setDouble(4, Game.killedMobs);
            pstmt.setDouble(5, OptionsState.music);
            pstmt.setDouble(6, OptionsState.easy);
            pstmt.setString(7, "res/worlds/world" + Game.lvl + ".txt");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getHarta(){
        String sql = "SELECT * FROM runBob";
        String rez="";

        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                rez=rs.getString("harta");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rez;
    }


    public static void dropTable(){
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
                        rs.getInt("tufeDistruse")+ " " +
                        rs.getInt("music")+ " " +
                        rs.getInt("easy");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rez;
    }

    public void printAll(){

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
                        rs.getInt("tufeDistruse")+ " " +
                        rs.getInt("music")+ " " +
                        rs.getInt("easy");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(rez);
    }

}
