

package com.example.nominas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Empleado {
  int empleadoId;
       double sueldoBrutoAnual;
    int antiguedad;
        String nombre;

        public Empleado(int empleadoId, String nombre, double sueldoBrutoAnual, int antiguedad) {
            this.empleadoId = empleadoId;
            this.sueldoBrutoAnual = sueldoBrutoAnual;
            this.antiguedad = antiguedad;
            this.nombre = nombre;
        }
        public double calcularSueldoNetoMensual() {
        // 1. Prorrateo del sueldo bruto anual
        double sueldoMensual = this.sueldoBrutoAnual / 12;
        
        // 2. Retención IRPF
        double retencionIRPF = (this.sueldoBrutoAnual < 20000) ? sueldoMensual * 0.10 : sueldoMensual * 0.15;
        
        // 3. Plus de antigüedad
        double plusAntiguedad = this.antiguedad * 30;
        
        // 4. Sueldo neto mensual
        return sueldoMensual - retencionIRPF + plusAntiguedad;
    }

    // Getters
    public int getEmpleadoId() {
        return empleadoId;
    }

    public double getSueldoBrutoAnual() {
        return sueldoBrutoAnual;
    }

    public int getAntiguedad() {
        return antiguedad;
    }
    public String getNombre(){
        return nombre;
    }
}
