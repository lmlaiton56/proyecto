/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.supermercado;

import java.util.ArrayList;

import java.util.List;


/**
 *
 * @author milen
 */
public class Cajero {
        List<Cliente> clientesAtendidos = new ArrayList<>();

    public void agregarCliente(Cliente cliente) {
        clientesAtendidos.add(cliente);
    }

    public Cliente atenderCliente() {
        if (clientesAtendidos.size() > 0) {
            return clientesAtendidos.remove(clientesAtendidos.size() - 1);
        }
        return null;
    }
    
}

