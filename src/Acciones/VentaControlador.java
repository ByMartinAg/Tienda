/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Acciones;
import Modelo.ItemVenta;
import Modelo.Producto;
import Modelo.Venta;
import Modelo.VentaTabla;
/**
 *
 * @author marti
 */
public class VentaControlador {
    private Venta venta;
    private VentaTabla ventaTabla;

    public VentaControlador() {
        this.venta = new Venta();
        this.ventaTabla = new VentaTabla();
    }
    // va a agregar un producto a la venta, si ya existe va a agregar o sumar la cantidad
    public void agregarProducto(Producto producto, int cantidad) {
        // revisra si ya esta en el carro
        for (int i = 0; i < ventaTabla.getItems().size(); i++) {
            ItemVenta item = ventaTabla.getItems().get(i);
            if (item.getProducto().getNombre().equals(producto.getNombre())) {
                item.setCantidad(item.getCantidad() + cantidad);
                producto.setStock(producto.getStock() - cantidad);
                ventaTabla.fireTableRowsUpdated(i, i);
                return;
            }
        }
        // aqui si no existe meter un nuevo item
        ItemVenta nuevoItem = new ItemVenta(producto, cantidad);
        ventaTabla.agregarItem(nuevoItem);
        venta.agregarItem(nuevoItem);

        // para reducir el stock del propducto
        producto.setStock(producto.getStock() - cantidad);
    }

            // para eliminar la fila seleccionada del carrito
              public void eliminarProducto(int fila){
                if (fila < 0 || fila >= ventaTabla.getRowCount()) {
                    return;
                }
                ItemVenta items = ventaTabla.getItem(fila);
                // devolvemos el stock
                items.getProducto().setStock(items.getProducto().getStock() + items.getCantidad());

                ventaTabla.eliminarItem(fila);
                venta.eliminarItem(fila);
            }

            // limpia para nueva venta
            public void nuevaVenta(){
                 // regresa el stock a todos los items
                for (ItemVenta item1 : ventaTabla.getItems()){
                    item1.getProducto().setStock(item1.getProducto().getStock() + item1.getCantidad());
                }
                venta = new Venta();
                ventaTabla.limpiar();
            }
            // getters para asignar la vista nomas

    public VentaTabla getVentaTabla() {
        return ventaTabla;
    }
    public Venta getVenta() {
        return venta;
    }
    public double getSubtotal() {
        return venta.getSubtotal();
    }
    public double getIVA (){
        return venta.getMontoIva();
    }
    public double getTotal() {
        return venta.getTotal();
    }
}




