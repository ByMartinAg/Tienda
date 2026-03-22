/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author marti
 */
public class VentaTabla extends AbstractTableModel{
    private String[] columnas = {"Nombre", "Categoria", "Precio Unit.", "Cantidad", "Subtotal"};
    private ArrayList<ItemVenta> items;

    public VentaTabla() {
        this.items = new ArrayList<>();
    }

    // ahora los metodos para la tabla
    public int getRowCount() {
        return items.size();
    }

    public int getColumnCount() {
        return columnas.length;
    }

    public String getColumnName(int col) {
        return columnas[col];
    }

    public Object getValueAt(int fila, int col){
        ItemVenta item = items.get(fila);
        switch (col){
            case 0: return  item.getProducto().getNombre();
            case 1: return  item.getProducto().getCategoria();
            case 2: return  String.format("%.2f", item.getProducto().getPrecio());
            case 3: return  item.getCantidad();
            case 4: return  String.format("%.2f", item.getSubtotal());
            default: return  "";
        }
    }
    // ahora si controlar todo
    public void agregarItem(ItemVenta item) {
        this.items.add(item);
        fireTableRowsInserted(items.size() - 1, items.size() - 1);
    }
    public void eliminarItem(int fila) {
        this.items.remove(fila);
        fireTableRowsDeleted(fila, fila);
    }

    public ItemVenta getItem(int fila) {
        return items.get(fila);
    }

    public ArrayList<ItemVenta> getItems() {
        return items;
    }

    public void limpiar () {
        items.clear();
        fireTableDataChanged();

    }

}

