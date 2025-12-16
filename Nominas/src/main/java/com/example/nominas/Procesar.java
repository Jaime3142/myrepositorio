

package com.example.nominas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;


public class Procesar {

  // Método para procesar las nóminas
    public static void procesarNominas(String archivoEntrada, String archivoSalida
                                       ) throws IOException {

     // Iniciar un SwingWorker para procesar las nóminas en segundo plano
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            
            protected Void doInBackground() throws Exception {

                try (BufferedReader reader = new BufferedReader(new FileReader(archivoEntrada));
                     BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {

                    String line;
                    String cabecera = reader.readLine();
                    if (cabecera != null) {
                        writer.write(cabecera + ";Sueldo Neto\n"); // Escribimos la cabecera en el archivo de salida
                    }

                    int count = 0;
                    double totalSueldo = 0;
                    double maxSueldo = 0;
                    String empleadoMax = "";
                    int totalProcesados = 0;

                    // Procesar cada línea del archivo
                    while ((line = reader.readLine()) != null) {
                        try {
                            String[] datos = line.split(";");

                            // Asegurarse de que hay 4 campos en cada línea
                            if (datos.length < 4) {
                                continue; // Saltar esta línea si no tiene el formato correcto
                            }

                            // Asignar los datos a las variables correspondientes
                            String nombre = datos[1];  // Nombre del empleado
                            int empleadoId = Integer.parseInt(datos[0].replace("EMP", "")); // El ID del empleado
                            double sueldoBrutoAnual = Double.parseDouble(datos[2]);
                            int antiguedad = Integer.parseInt(datos[3]);

                            // Crear el objeto Empleado
                            Empleado empleado = new Empleado(empleadoId, nombre, sueldoBrutoAnual, antiguedad);

                            // Calcular el sueldo neto mensual
                            double sueldoNetoMensual = empleado.calcularSueldoNetoMensual();
                            totalSueldo += sueldoNetoMensual;

                            // Mantener el empleado que más cobra
                            if (sueldoNetoMensual > maxSueldo) {
                                maxSueldo = sueldoNetoMensual;
                                empleadoMax = nombre + " (" + sueldoNetoMensual + ")";
                            }

                            // Escribir la línea original más el sueldo neto calculado
                            writer.write(line + ";" + String.format("%.2f", sueldoNetoMensual) + "\n");

                            totalProcesados++;

                            // Publicar el progreso cada 1000 registros
                            if (totalProcesados % 1000 == 0) {
                                publish(totalProcesados);
                            }

                        } catch (NumberFormatException e) {
                                       e.printStackTrace();             }
                    }

                   
                    

                }

                return null;  // Finaliza el SwingWorker
            }

            private void publish(int totalProcesados) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        }
                }
}





