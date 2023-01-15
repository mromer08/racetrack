/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mrojas.hipodromo.controllers;

import com.mrojas.hipodromo.models.Apuesta;
import com.mrojas.hipodromo.models.Hipodromo;
import com.mrojas.hipodromo.util.ListaEnlazada;
import com.mrojas.hipodromo.views.ReportsFrame;
import javax.swing.ImageIcon;

/**
 *
 * @author manu
 */
public class ReportsController {

    private ReportsFrame frame;

    public ReportsController(ReportsFrame frame) {
        this.frame = frame;
        frame.setIconImage(new ImageIcon(getClass().getResource("/images/hipodromo.png")).getImage());
        mostrarReportes();
    }

    private void mostrarReportes() {
        frame.getTablaResultados().setModel(new javax.swing.table.DefaultTableModel(
                new String[][]{
                    {"Verificación de apuestas", (InputResultsController.getTimePromedioValidar() + " ns"), (InputResultsController.getPasosPromedioVerificar() + " pasos") , Apuesta.maxPasoVerificar + " pasos", Apuesta.menPasoVerificar + " pasos"},
                    {"Cálculo de resultados", (InputResultsController.getTimePromedioPuntaje()+ " ns"), (InputResultsController.getPasosPromedioPuntaje()+ " pasos") , Apuesta.maxPasoPuntaje + " pasos", Apuesta.menPasoPuntaje + " pasos"},
                    {"Ordenamiento de resultados", (ListaEnlazada.timeSort + " ms"), ListaEnlazada.pasosSort + " pasos", ListaEnlazada.pasosSort + " pasos", ListaEnlazada.pasosSort + " pasos"}
                },
                new String[]{
                    "Servicio Crítico", "Tiempo promedio", "Promedio de pasos", "Mayor cantidad de pasos", "Menor cantidad de pasos"
                }) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
    }

}
