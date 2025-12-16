package com.mycompany.calculadora_ver2;

import javax.swing.*;
import java.awt.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Calculadora_ver2 extends JFrame {

    private JTextField pantalla;

    private double num1 = 0;
    private double num2 = 0;
    private String operador = "";

    public Calculadora_ver2() {
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        pantalla = new JTextField();
        pantalla.setEditable(false);
        add(pantalla, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4, 4));

        String[] botones = {
            "1", "2", "3", "/",
            "4", "5", "6", "*",
            "7", "8", "9", "-",
            "0", "borrar", "=", "+"
        };

        for (String texto : botones) {
            JButton b = new JButton(texto);
            b.setText(texto);
            b.addActionListener(e -> pulsar(texto));
            panel.add(b);
        }

        add(panel, BorderLayout.CENTER);
    }

    private void pulsar(String valor) {

        if (valor.equals("borrar")) {
            pantalla.setText("");
            num1 = 0;
            num2 = 0;
            operador = "";
            return;
        }

        if (valor.equals("+") || valor.equals("-") || 
            valor.equals("*") || valor.equals("/")) {

            num1 = Double.parseDouble(pantalla.getText());
            operador = valor;
            pantalla.setText("");
            return;
        }

   
        if (valor.equals("=")) {
            num2 = Double.parseDouble(pantalla.getText());
            double resultado = 0;

            switch (operador) {
                case "+": resultado = num1 + num2; break;
                case "-": resultado = num1 - num2; break;
                case "*": resultado = num1 * num2; break;
                case "/": resultado = num1 / num2; break;
            }

            pantalla.setText(String.valueOf(resultado));
            return;
        }

        pantalla.setText(pantalla.getText() + valor);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculadora_ver2().setVisible(true);
        });
    }
}
