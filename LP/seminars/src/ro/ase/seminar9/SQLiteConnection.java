package ro.ase.seminar9;

import java.sql.*;

public class SQLiteConnection {
    public static final String DB_URL = "jdbc:sqlite:D:/CSIE/E-Business/PPOO/DBs/DBSQLite.db";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);
            System.out.println("Conectat cu succes!");

            stmt = conn.createStatement();

            String sqlCreate = "CREATE TABLE studenti (cod INT PRIMARY KEY, nume VARCHAR(10), varsta INT, " + "inaltime INT, CNP VARCHAR(13), sex VARCHAR(1))";
//            stmt.executeUpdate(sqlCreate);
            System.out.println("Tabela creata cu succes!");

            String sqlInsert = "INSERT INTO studenti VALUES (300, 'Popescu', 20, 180, '1234567890123', 'M')";
            String sqlInsert2 = "INSERT INTO studenti VALUES (400, 'Ionescu', 21, 170, '1234567890124', 'F')";
//            stmt.executeUpdate(sqlInsert);
//            stmt.executeUpdate(sqlInsert2);
            System.out.println("Date inserate cu succes!");

            String sqlUpdate = "UPDATE studenti SET nume = 'Dorel' WHERE cod = 300";
//            stmt.executeUpdate(sqlUpdate);
            System.out.println("Date actualizate cu succes!");

            String sqlDelete = "DELETE FROM studenti WHERE cod = 400";
//            stmt.executeUpdate(sqlDelete);
            System.out.println("Date sterse cu succes!");

            ResultSet rs = stmt.executeQuery("SELECT * FROM studenti");
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("Numarul de coloane: " + rsmd.getColumnCount());
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                System.out.println("Nume coloana: " + rsmd.getColumnName(i + 1) + ", Tip coloana: " + rsmd.getColumnTypeName(i + 1));
            }

            while (rs.next()) {
                System.out.println("Cod: " + rs.getString("cod") + ", Nume: " + rs.getString("nume") + ", CNP: " + rs.getString("CNP"));
            }

            rs.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTable() {
        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_URL);

            Statement stmt = conn.createStatement();

            String sqlCreate = "CREATE TABLE studenti (cod INT PRIMARY KEY, nume VARCHAR(10), varsta INT, " + "inaltime INT, CNP VARCHAR(13), sex VARCHAR(1))";
            stmt.executeUpdate(sqlCreate);
            System.out.println("Tabela creata cu succes!");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void insertRecord() {
        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_URL);

            Statement stmt = conn.createStatement();

            String sqlInsert = "INSERT INTO studenti VALUES (300, 'Popescu', 20, 180, '1234567890123', 'M')";
            stmt.executeUpdate(sqlInsert);
            System.out.println("Date inserate cu succes!");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}