/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author marti
 */
public class Producto {
        private String nombre;
        private double precio;
        private int stock;
        private String imagen;
        private String categoria;
    
    public Producto(String nombre, double precio, int stock, String imagen, String categoria){
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.imagen = imagen;
        this.categoria = categoria;
    }
    
    // ahora vamos con los getters para obtener todo
    
    public  String getNombre (){
        return nombre;
    }
    public double getPrecio(){
        return precio;
    }
    public int getStock(){
        return stock;
    }
    public String getCategoria(){
        return categoria;
    }
    public String getImagen(){
    return imagen;
    }

    // un set nomas
    public void setStock (int stock){
        this.stock = stock;
    }
    
    // metemos un override para mostrar el nombre nadamas
    public String toString (){
        return nombre;
    }
    
    
}
