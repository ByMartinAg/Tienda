/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Acciones;
import Modelo.Venta;
import Extras.Ticket;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author marti
 */
public class Exportar {
    // mete el ticket en archivo txt o csv en la ruta

    public static boolean exportarTicketTxt(Venta venta, String ruta) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(ruta));
            writer.println(Ticket.construirTicket(venta));
            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error al exportar" + e.getMessage());
            return false;
        }
    }

    public static boolean exportarTicketCsv(Venta venta, String ruta) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(ruta));
            writer.println("Producto, Categoria, Precio Unitario, Cantidad, Subtotal");

            for (var item : venta.getItems()){
                writer.printf("%s, %s, %2f, %d, %, %.2f%n",
                        item.getProducto().getNombre(),
                        item.getProducto().getCategoria(),
                        item.getProducto().getPrecio(),
                        item.getCantidad(),
                        item.getSubtotal());
            }
            writer.printf("%n,,,Subtotal,%.2f%n", venta.getSubtotal());
            writer.printf(",,,IVA (16%),%.2f%n", venta.getMontoIva());
            writer.printf(",,,Total,%.2f%n", venta.getTotal());
            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error al exportar" + e.getMessage());
            return false;
        }
    }
}
