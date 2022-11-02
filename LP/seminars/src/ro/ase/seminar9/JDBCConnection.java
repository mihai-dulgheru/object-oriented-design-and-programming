package ro.ase.seminar9;

import java.sql.*;

public class JDBCConnection {
    public static final String DB_URL = "jdbc:derby:D:/CSIE/E-Business/PPOO/DBs/DBJavaDB;create=true";
    public static final String USER = "admin";
    public static final String PASS = "admin";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Conectat cu succes!");

            stmt = conn.createStatement();

            String sqlCreate = "CREATE TABLE studenti (cod INT PRIMARY KEY, nume VARCHAR(10), varsta INT, " + "inaltime INT, CNP VARCHAR(13), sex VARCHAR(1))";
//            stmt.executeUpdate(sqlCreate);
            System.out.println("Tabela creata cu succes!");

            String sqlInsert = "INSERT INTO studenti VALUES (3, 'Popescu', 20, 180, '1234567890123', 'M')";
            String sqlInsert2 = "INSERT INTO studenti VALUES (4, 'Ionescu', 21, 170, '1234567890124', 'F')";
//            stmt.executeUpdate(sqlInsert);
//            stmt.executeUpdate(sqlInsert2);
            System.out.println("Date inserate cu succes!");

            String sqlUpdate = "UPDATE studenti SET nume = 'Dorel' WHERE cod = 3";
            stmt.executeUpdate(sqlUpdate);
            System.out.println("Date actualizate cu succes!");

            String sqlDelete = "DELETE FROM studenti WHERE cod = 4";
//            stmt.executeUpdate(sqlDelete);
            System.out.println("Date sterse cu succes!");

            ResultSet rs = stmt.executeQuery("SELECT * FROM studenti");
            while (rs.next()) {
                System.out.println("Cod: " + rs.getString("cod") + ", Nume: " + rs.getString("nume") + ", CNP: " + rs.getString("CNP"));
            }

            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("Numarul de coloane: " + rsmd.getColumnCount());
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                System.out.println("Nume coloana: " + rsmd.getColumnName(i + 1) + ", Tip coloana: " + rsmd.getColumnTypeName(i + 1));
            }

            rs.close();
        } catch (SQLException e) {
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
