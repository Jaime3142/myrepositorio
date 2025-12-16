

package com.example.nomina;

import javax.swing.SwingUtilities;

public class Nomina {

    public static void main(String[] args) {
       
         javax.swing.SwingUtilities.invokeLater(() -> {
            Interfaz frame = new Interfaz();
            frame.setVisible(true);
        });
    }
}
