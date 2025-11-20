package com.example.Inazuma;

public class Jugador {
    private int idJugador;
    private String nombre;
    private String posicion;
    

    public Jugador(){

    }
    public Jugador(String nombre, String posicion){
        this.nombre = nombre;
        this.posicion = posicion;

    }
    public int getIdJugador() {
        return idJugador;
    }
    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPosicion() {
        return posicion;
    }
    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
    
    public String toString() {
        return " nombre=" + nombre + " posicion= " + posicion;
    }
    
}
