/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Extras;
import Modelo.ItemVenta;
import Modelo.Venta;
/**
 *
 * @author marti
 */
public class Ticket {
    // SE USA SOLO TEXTO para armar el ticket
    public static String construirTicket(Venta venta){
        StringBuilder sb = new StringBuilder();

        sb.append("Tienda de abarrotes");
        sb.append("Los mejores precios\n");

        if (venta.getCliente()!= null){
            sb.append("Cliente: ").append(venta.getCliente().getNombre()).append(" ").append(venta.getCliente().getApellidos()).append("\n");
            sb.append("Edad: ").append(venta.getCliente().getEdad()).append("\n");
        }
        sb.append("---------\n");
        sb.append(String.format("%-20s %6s %8s %10s \n", "Producto", "Cant.", "P.Unit", "Subtotal"));
        sb.append("----------------\n");

            for (ItemVenta item : venta.getItems()){
                sb.append(String.format("%-20s %6d %8.2f %10.2f \n",
                        item.getProducto().getNombre(),
                        item.getCantidad(),
                        item.getProducto().getPrecio(),
                        item.getSubtotal()));
            }
            sb.append("------------\n");
            sb.append(String.format("%30s %10.2f\n", "SUBTOTAL SIN IVA: ", venta.getSubtotal()));
            sb.append(String.format("%30s %10.2f\n" , "IVA (16%): ", venta.getMontoIva()));
            sb.append(String.format("%30s %10.2f\n", "TOTAL:", venta.getTotal()));
            sb.append("\n Gracias por su compra\n");

            return sb.toString();
    }

        // solo para derle formato y que no se n os vaya
    private static String formaCorta (String texto, int max){
        if (texto.length() <= max){
            return texto.substring(0, max-1) + " ";
        }
        return texto;
    }
}
