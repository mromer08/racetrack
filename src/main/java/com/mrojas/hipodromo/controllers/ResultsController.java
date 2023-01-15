/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mrojas.hipodromo.controllers;

import com.mrojas.hipodromo.models.Apuesta;
import com.mrojas.hipodromo.util.ListaEnlazada;
import com.mrojas.hipodromo.views.ResultsFrame;
import javax.swing.ImageIcon;

/**
 *
 * @author manu
 */
public class ResultsController {

    private ResultsFrame frame;
    private ListaEnlazada apuestas;

    /**
     * Constructor para el controlador de la ventana que muestra los resultados de las apuestas.
     * @param frame el frame correspondiente a la muestra de resultados al usuario.
     * @param apuestas listado de las apuestas acumuladas.
     */
    public ResultsController(ResultsFrame frame, ListaEnlazada apuestas) {
        this.frame = frame;
        frame.setIconImage(new ImageIcon(getClass().getResource("/images/hipodromo.png")).getImage());
        this.apuestas = apuestas;
        actualizarTabla(false);
    }

    /**
     * Metodo para actualizar la tabla de resultados.
     * @param isAlphabeticSort Valor que define si se ordenará de manera alfabética (true) o por puntaje (false)
     */

    public void actualizarTabla(boolean isAlphabeticSort) {
        ListaEnlazada apuestasCopy = new ListaEnlazada();
        Object[][] datos = new Object[apuestas.size()][2];
        int i = apuestas.size()-1;
        apuestas.insertionSort(isAlphabeticSort);
        

        while (apuestas.size() > 0) {
            Apuesta agregar = apuestas.pop();
            apuestasCopy.push(agregar);
            datos[i][0] = agregar.getApostador().getName();
            datos[i][1] = agregar.getApostador().getPuntaje();
            i--;
        }

        frame.getTablaResultados().setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                    "Nombre", "Punteo"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        this.apuestas = apuestasCopy;
    }

    /**
     * Metodo que utiliza los valores seleccionados por el usuario para ordenar los resultados.
     * @param tipo
     */
    
    public void sortApuestas(int tipo){
        switch (tipo) {
            case 0 -> actualizarTabla(false);
            case 1 -> actualizarTabla(true);
        }
    }

}
