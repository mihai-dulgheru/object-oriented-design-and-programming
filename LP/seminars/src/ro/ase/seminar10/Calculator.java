package ro.ase.seminar10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JComboBox comboBox1;
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton calculeazaButton;

    public Calculator() {
        setTitle("Calculator");

        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());


        String[] operations = {"+", "-", "*", "/"};
        comboBox1 = new JComboBox(operations);
        comboBox1.setBounds(40, 40, 80, 25);
        panel1.add(comboBox1);


        panel1.add(new JLabel("Primul operand"));
        textField1 = new JTextField(10);
        panel1.add(textField1);


        panel1.add(new JLabel("Al doilea operand"));
        textField2 = new JTextField(10);
        panel1.add(textField2);

        panel1.add(new JLabel("Rezultat"));
        textField3 = new JTextField(10);
        panel1.add(textField3);

        calculeazaButton = new JButton("Calculeaza");
        panel1.add(calculeazaButton);

        add(panel1);

        calculeazaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (comboBox1.getSelectedIndex()) {
                    case 0:
                        try {
                            int val1 = Integer.parseInt(textField1.getText());
                            int val2 = Integer.parseInt(textField2.getText());
                            int total = val1 + val2;
                            textField3.setText(String.valueOf(total));
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        break;
                    case 1:
                        try {
                            int val1 = Integer.parseInt(textField1.getText());
                            int val2 = Integer.parseInt(textField2.getText());
                            int total = val1 - val2;
                            textField3.setText(String.valueOf(total));
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        break;
                    case 2:
                        try {
                            int val1 = Integer.parseInt(textField1.getText());
                            int val2 = Integer.parseInt(textField2.getText());
                            int total = val1 * val2;
                            textField3.setText(String.valueOf(total));
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        break;
                    case 3:
                        try {
                            int val1 = Integer.parseInt(textField1.getText());
                            int val2 = Integer.parseInt(textField2.getText());
                            float total = (float) val1 / val2;
                            textField3.setText(String.valueOf(total));
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        break;
                }
            }
        });
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setBounds(100, 100, 800, 200);
        calculator.setVisible(true);
    }
}
