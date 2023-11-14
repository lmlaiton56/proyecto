/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.supermercado;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.List;

public class Supermercado {

    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
        List<Cajero> cajeros = new ArrayList<>();

        System.out.print("Ingrese la cantidad de cajeros: ");
        int numCajeros = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numCajeros; i++) {
            cajeros.add(new Cajero());
        }

        int numeroCliente = 1;

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Registrar producto en carrito");
            System.out.println("3. Calcular total de compra del cliente actual");
            System.out.println("4. Eliminar última cuenta del cliente actual");
            System.out.println("5. Mostrar estadísticas de ventas de un cajero");
            System.out.println("6. Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del cliente: ");
                    String nombreCliente = scanner.nextLine();
                    Cliente nuevoCliente = new Cliente(nombreCliente);
                    int numCajero = (numeroCliente - 1) % numCajeros;
                    cajeros.get(numCajero).agregarCliente(nuevoCliente);
                    System.out.println("Cliente " + numeroCliente + " (" + nombreCliente + ") agregado al cajero " + (numCajero + 1) + ".");
                    numeroCliente++;
                    break;
                case 2:
                    System.out.print("Nombre del producto: ");
                    String nombreProducto = scanner.nextLine();
                    System.out.print("Costo del producto: ");
                    double costoProducto = scanner.nextDouble();
                    System.out.print("Cantidad de producto: ");
                    int cantidadProducto = scanner.nextInt();
                    Producto producto = new Producto(nombreProducto, costoProducto, cantidadProducto);
                    int cajeroActual = (numeroCliente - 1) % numCajeros;
                    Cliente clienteActual = cajeros.get(cajeroActual).atenderCliente();
                    if (clienteActual != null) {
                        clienteActual.agregarProducto(producto);
                        cajeros.get(cajeroActual).agregarCliente(clienteActual);
                        System.out.println("Producto " + nombreProducto + " registrado en el carrito de " + clienteActual.getNombre() + ".");
                    } else {
                        System.out.println("No hay clientes para registrar productos.");
                    }
                    break;
                case 3:
                    System.out.print("Número de cajero: ");
                    int cajeroCalcularTotal = scanner.nextInt() - 1;
                    Cajero cajero = cajeros.get(cajeroCalcularTotal);
                    Cliente cliente = cajero.atenderCliente();
                    if (cliente != null) {
                        double totalCompra = cliente.calcularTotalCompra();
                        System.out.println("Total de compra de " + cliente.getNombre() + ": $" + totalCompra);
                        cajero.agregarCliente(cliente);
                    } else {
                        System.out.println("No hay clientes para calcular el total.");
                    }
                    break;
                case 4:
                    System.out.print("Número de cajero: ");
                    int cajeroEliminarCliente = scanner.nextInt() - 1;
                    Cliente clienteEliminado = cajeros.get(cajeroEliminarCliente).atenderCliente();
                    if (clienteEliminado != null) {
                        System.out.println("Cliente " + clienteEliminado.getNombre() + " eliminado del cajero " + (cajeroEliminarCliente + 1) + ".");
                    } else {
                        System.out.println("No hay clientes para eliminar en el cajero " + (cajeroEliminarCliente + 1) + ".");
                    }
                    break;
                case 5:
                    System.out.print("Número de cajero: ");
                    int cajeroMostrarVentas = scanner.nextInt() - 1;
                    int clientesAtendidos = cajeros.get(cajeroMostrarVentas).clientesAtendidos.size();
                    double ventasTotales = 0;
                    for (Cliente clienteAtendido : cajeros.get(cajeroMostrarVentas).clientesAtendidos) {
                        ventasTotales += clienteAtendido.calcularTotalCompra();
                    }
                    System.out.println("Cajero " + (cajeroMostrarVentas + 1) + " ha atendido a " + clientesAtendidos + " clientes y ha vendido $" + ventasTotales);
                    break;
                case 6:
                    for (int i = 0; i < numCajeros; i++) {
                        clientesAtendidos = cajeros.get(i).clientesAtendidos.size();
                        System.out.println("Cajero " + (i + 1) + " ha atendido a " + clientesAtendidos + " clientes.");
                    }
                    break;
                case 7:
                    System.out.println("Saliendo del programa.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}

    