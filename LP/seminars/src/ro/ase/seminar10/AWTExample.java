package ro.ase.seminar10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AWTExample extends Frame implements ActionListener {
    String Driver = null;
    String Database = null;
    String User = null;
    String Pass = null;
    String SQL = null;

    TextField tfDriver;
    TextField tfDatabase;
    TextField tfUsername;
    TextField tfPassword;
    TextField tfSQL;
    Button button;
    TextArea textArea;
    Label status;

    public AWTExample() {
        setTitle("Database application");
        setLayout(new FlowLayout());

        JLabel labelDriver = new JLabel("Driver");
        add(labelDriver);
        tfDriver = new TextField(60);
        tfDriver.setText("org.sqlite.JDBC");
        add(tfDriver);

        JLabel labelDatabase = new JLabel("Database URL");
        add(labelDatabase);
        tfDatabase = new TextField(60);
        tfDatabase.setText("jdbc:sqlite:D:\\CSIE\\E-Business\\PPOO\\DBs\\MySQLiteDB");
        add(tfDatabase);

        JLabel labelUsername = new JLabel("Username");
        add(labelUsername);
        tfUsername = new TextField(60);
        add(tfUsername);

        JLabel labelPassword = new JLabel("Password");
        add(labelPassword);
        tfPassword = new TextField(60);
        add(tfPassword);

        JLabel labelSQL = new JLabel("SQL command");
        add(labelSQL);
        tfSQL = new TextField(60);
        tfSQL.setText("SELECT * FROM studenti");
        add(tfSQL);

        button = new Button("Execute");
        add(button);
        button.addActionListener(this);

        textArea = new TextArea(10, 60);
        add(textArea);

        addWindowListener(new MyWindowAdapter());

        status = new Label("Status: ");
        add(status);

        textArea.addMouseListener(new MouseHandler());
        textArea.addMouseMotionListener(new MouseHandler());
    }

    public static void main(String[] args) {
        AWTExample frame = new AWTExample();
        frame.setBounds(100, 100, 560, 500);
        frame.setVisible(true);
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

    class MyWindowAdapter extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            System.exit(0);
        }
    }

    class MouseHandler implements MouseListener, MouseMotionListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            status.setText("Mouse clicked");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            status.setText("Mouse pressed");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            status.setText("Mouse released");
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            status.setText("Mouse entered");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            status.setText("Mouse exited");
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            status.setText("Mouse dragged");
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            status.setText("Mouse moved");
        }
    }


}
