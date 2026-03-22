/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Extras;

import Modelo.Producto;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author marti
 */
public class DatoProducto {
    public static String[] getCategorias(){
        return new String[] {"Alimentos", "Bebidas", "Botanas", "Limpieza", "Higiene"};
    }
    public static ArrayList<Producto> getProductos(String categoria){
        switch (categoria){
            case "Alimentos": return getAlimentos();
            case "Bebidas": return getBebidas();
            case "Botanas": return getBotanas();
            case "Limpieza": return getLimpieza();
            case "Higiene": return getHigiene();
            default: return new ArrayList<>();
        }
    }

    private static ArrayList<Producto>getAlimentos(){
        ArrayList<Producto> lista = new ArrayList<>();
        lista.add(new Producto("Arroz", 22, 10, "", "Alimentos"));
        lista.add(new Producto("Frijoles", 30, 10, "", "Alimentos"));
        lista.add(new Producto("Sopa",5, 10, "", "Alimentos"));

        return lista;
    }
    private static ArrayList<Producto>getBebidas(){
        ArrayList<Producto> lista = new ArrayList<>();
        lista.add(new Producto("Agua", 15, 10, "", "Bebidas"));
        lista.add(new Producto("Jugo", 15, 10, "", "Bebidas"));
        lista.add(new Producto("Gaseosa", 20, 10, "", "Bebidas"));
        return lista;
    }

    private static ArrayList<Producto> getBotanas(){
        ArrayList<Producto> lista = new ArrayList<>();
        lista.add(new Producto("Chicles", 5, 10, "", "Botanas"));
        lista.add(new Producto("Sabritas", 18, 10, "", "Botanas"));
        lista.add(new Producto("Rancheritos", 16, 10, "", "Botanas"));
        return lista;
    }
    private static ArrayList<Producto> getLimpieza(){
        ArrayList<Producto> lista = new ArrayList<>();
        lista.add(new Producto("Detergente", 40, 10, "", "Limpieza"));
        lista.add(new Producto("Jabon", 15, 10, "", "Limpieza"));
        lista.add(new Producto("Cepillo", 17, 10, "", "Limpieza"));
        return lista;
    }

    private static ArrayList<Producto> getHigiene(){
        ArrayList<Producto> lista = new ArrayList<>();
        lista.add(new Producto("Shampoo", 32, 10, "", "Higiene"));
        lista.add(new Producto("Jabon Corporal", 20, 10, "", "Higiene"));
        lista.add(new Producto("Acondicionador", 43, 10, "", "Higiene"));
        return lista;
    }
}
