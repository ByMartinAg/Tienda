/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.ArrayList;
/**
 *
 * @author marti
 */
public class Venta {
    public static final double IVA = 0.16;
    private Cliente cliente;
    private ArrayList<ItemVenta> items;
     
    public Venta() {
        this.items = new ArrayList<>();
    }
    public void setCliente (Cliente cliente){
        this.cliente = cliente;
    }
    // solo para usar gets en otras clases, son unicamente para no llamar a toda la variable y tener todo en una clase
    public Cliente getCliente(){
        return cliente;
    }
    public ArrayList<ItemVenta> getItems (){
    return items;
    }
    public void agregarItem(ItemVenta item){
        items.add(item);
    }
    public void eliminarItem(int indice){
        items.remove(indice);
    }
    
    // tener todos los subtotales sin iva
    public double getSubtotal(){
        double total = 0;
        for (ItemVenta item : items){
            total = total + item.getSubtotal();
        }
        return total;
    }
    public double getMontoIva(){
        return getSubtotal() * IVA;
    }
    public double getTotal(){
        return getSubtotal()+getMontoIva();
    }
    
}
