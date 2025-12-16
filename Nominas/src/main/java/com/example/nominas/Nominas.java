

package com.example.nominas;
import static com.example.nominas.Procesar.procesarNominas;
import java.io.IOException;


public class Nominas {

    public static void main(String[] args) {
       String archivoEntrada = "nominas_masivas.csv";  
        String archivoSalida = "resultado_nominas.csv";  

        try {
            Procesar.procesarNominas(archivoEntrada, archivoSalida);
            System.out.println("El cálculo de las nóminas ha sido completado y guardado en " + archivoSalida);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al procesar los archivos.");
        }
    }
}
