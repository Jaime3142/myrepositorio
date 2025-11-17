package com.example.Inazuma;

public class Tecnica {
    private int idTecnica;
    private String nombre;
    private String tipo;
    private int idJugador;
    

public Tecnica(){

}
public Tecnica(String nombre, String tipo){
this.nombre = nombre;
this.tipo = tipo;
}
public int getIdTecnica() {
    return idTecnica;
}
public void setIdTecnica(int idTecnica) {
    this.idTecnica = idTecnica;
}
public String getNombre() {
    return nombre;
}
public void setNombre(String nombre) {
    this.nombre = nombre;
}
public String getTipo() {
    return tipo;
}
public void setTipo(String tipo) {
    this.tipo = tipo;
}
public int getIdJugador() {
    return idJugador;
}
public void setIdJugador(int idJugador) {
    this.idJugador = idJugador;
}

}
