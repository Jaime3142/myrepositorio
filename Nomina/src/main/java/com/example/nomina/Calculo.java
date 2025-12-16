

package com.example.nomina;


public class Calculo {
    public static double calcularNetoMensual(Empleado e) {
        // 1. Prorrateo
        double mensual = e.getSueldoBrutoAnual() / 12.0;

        // 2. IRPF
        double irpf = e.getSueldoBrutoAnual() < 20000 ? 0.10 : 0.15;
        mensual -= mensual * irpf;

        // 3. Antigüedad
        mensual += e.getAntiguedad() * 30;

        return mensual;
    }
}
