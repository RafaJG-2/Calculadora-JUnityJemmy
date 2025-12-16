package com.mycompany.calculadora_ver2;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;

import javax.swing.SwingUtilities;

public class Calculadora_ver2Test {

    private JFrameOperator frame;
    private JTextFieldOperator pantalla;

    @BeforeEach
    void abrir_calculadora() throws Exception {
        Calculadora_ver2 calc = new Calculadora_ver2();
        SwingUtilities.invokeAndWait(() -> calc.setVisible(true));
        frame = new JFrameOperator(calc);
        pantalla = new JTextFieldOperator(frame, 0);
    }

    @AfterEach
    void cerrar_ventana() {
        frame.dispose();
    }

    @Test
    void testSuma() {
        pulsar("2", "+", "3", "=");
        assertEquals("5.0", pantalla.getText());
    }
    
        @Test
    void testSuma2() {
        pulsar("5", "+", "4", "=");
        assertEquals("7.0", pantalla.getText());
    }

    @Test
    void testResta() {
        pulsar("9", "-", "4", "=");
        assertEquals("5.0", pantalla.getText());
    }

    @Test
    void testMultiplicacion() {
        pulsar("6", "*", "2", "=");
        assertEquals("12.0", pantalla.getText());
    }

    @Test
    void testDivision() {
        pulsar("8", "/", "2", "=");
        assertEquals("4.0", pantalla.getText());
    }

    @Test
    void testOperacionCombinada_NoSoportada() {
        pulsar("2", "+", "3", "*", "4", "=");
        assertEquals("12.0", pantalla.getText());
    }
    
    //ERRORES. SON 6 ERRORES EN TOTAL
    
        @Test
    void testSumaIncorrecta() {
        pulsar("2", "+", "2", "=");
        assertEquals("5.0", pantalla.getText());
    }

    @Test
        void testRestaIncorrecta() {
            pulsar("1", "0", "-", "3", "=");
            assertEquals("10.0", pantalla.getText());
        }


    @Test
    void testMultiplicacionIncorrecta() {
        pulsar("4", "*", "3", "=");
        assertEquals("15.0", pantalla.getText());
    }

    @Test
    void testDivisionIncorrecta() {
        pulsar("9", "/", "3", "=");
        assertEquals("2.0", pantalla.getText());
    }

    @Test
    void testOperacionCombinadaIncorrecta() {
        pulsar("2", "+", "3", "*", "4", "=");
        assertEquals("20.0", pantalla.getText());
    }

    @Test
    void testResultadoCeroIncorrecto() {
        pulsar("5", "-", "5", "=");
        assertEquals("1.0", pantalla.getText());
    }

    
    private void pulsar(String... botones) {
        for (String b : botones) {
            new JButtonOperator(frame, b).push();
        }
    }
}
