package com.example.nomina;

public class Empleado {
    private String nombre;
    private double sueldoBrutoAnual;
    private int antiguedad;

    public Empleado(String nombre, double sueldoBrutoAnual, int antiguedad) {
        this.nombre = nombre;
        this.sueldoBrutoAnual = sueldoBrutoAnual;
        this.antiguedad = antiguedad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSueldoBrutoAnual() {
        return sueldoBrutoAnual;
    }

    public int getAntiguedad() {
        return antiguedad;
    }
}

