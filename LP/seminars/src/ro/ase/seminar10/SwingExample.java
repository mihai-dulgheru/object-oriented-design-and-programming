package ro.ase.seminar10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SwingExample extends JFrame implements ActionListener {
    String Driver = null;
    String Database = null;
    String User = null;
    String Pass = null;
    String SQL = null;

    JTextField tfDriver;
    JTextField tfDatabase;
    JTextField tfUsername;
    JTextField tfPassword;
    JTextField tfSQL;
    JButton button;
    JTextArea textArea;

    public SwingExample() {
        setTitle("Database application");
        setLayout(new FlowLayout());

        JLabel labelDriver = new JLabel("Driver");
        add(labelDriver);
        tfDriver = new JTextField(60);
        tfDriver.setText("org.sqlite.JDBC");
        add(tfDriver);

        JLabel labelDatabase = new JLabel("Database URL");
        add(labelDatabase);
        tfDatabase = new JTextField(60);
        tfDatabase.setText("jdbc:sqlite:D:\\CSIE\\E-Business\\PPOO\\DBs\\MySQLiteDB");
        add(tfDatabase);

        JLabel labelUsername = new JLabel("Username");
        add(labelUsername);
        tfUsername = new JTextField(60);
        add(tfUsername);

        JLabel labelPassword = new JLabel("Password");
        add(labelPassword);
        tfPassword = new JTextField(60);
        add(tfPassword);

        JLabel labelSQL = new JLabel("SQL command");
        add(labelSQL);
        tfSQL = new JTextField(60);
        tfSQL.setText("SELECT * FROM studenti");
        add(tfSQL);

        button = new JButton("Execute");
        add(button);
        button.addActionListener(this);

        textArea = new JTextArea(10, 60);
        add(textArea);
    }

    public static void main(String[] args) {
        SwingExample frame = new SwingExample();
        frame.setBounds(100, 100, 640, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void processData() {
        Driver = tfDriver.getText();
        if (Driver.equals("")) {
            Driver = "org.sqlite.JDBC";
        }
        Database = tfDatabase.getText();
        if (Database.equals("")) {
            Database = "jdbc:sqlite:D:\\CSIE\\E-Business\\PPOO\\DBs\\MySQLiteDB";
        }
        User = tfUsername.getText();
        Pass = tfPassword.getText();
        SQL = tfSQL.getText();
        if (SQL.equals("")) {
            SQL = "SELECT * FROM studenti";
        }
    }

    private String getField(ResultSet rs, int dataType, int col) throws SQLException {
        return switch (dataType) {
            case Types.INTEGER -> Integer.toString(rs.getInt(col));
            case Types.VARCHAR -> rs.getString(col);
            default -> "";
        };
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        textArea.setText("");
        processData();
        try {
            Class.forName(Driver);
            Connection conn = DriverManager.getConnection(Database, User, Pass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            while (rs.next()) {
                StringBuilder record = new StringBuilder();
                for (int i = 1; i <= colCount; i++) {
                    int dataType = rsmd.getColumnType(i);
                    record.append(getField(rs, dataType, i)).append(" ");
                }
                textArea.append(record + "\n");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
