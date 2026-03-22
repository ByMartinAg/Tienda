/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author marti
 */
public class Cliente {
    private String nombre;
    private String apellidos;
    private int edad;
    
    public Cliente (String nombre, String apellidos, int edad){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }
    
    public String getNombre (){
        return nombre;
    }
    public String getAppellidos () {
        return apellidos;
    }
    public int getEdad (){
        return edad;
    }
    
}
