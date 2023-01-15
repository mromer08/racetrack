/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mrojas.hipodromo.controllers;

import com.mrojas.hipodromo.Main;
import com.mrojas.hipodromo.models.Hipodromo;
import com.mrojas.hipodromo.util.ListaEnlazada;
import com.mrojas.hipodromo.views.InputResultsFrame;
import com.mrojas.hipodromo.views.NewApuestaFrame;
import com.mrojas.hipodromo.views.ReportsFrame;
import com.mrojas.hipodromo.views.ResultsFrame;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author manu
 */
public class MainController {

    Main frame;
    private Hipodromo hipodromo;

    private ListaEnlazada apuestas;

    /**
     * Constructor para el controlador del main frame
     * 
     * @param frame el frame principal de la app.
     */

    public MainController(Main frame) {
        this.frame = frame;
        frame.setIconImage(new ImageIcon(getClass().getResource("/images/hipodromo.png")).getImage());
        this.hipodromo = new Hipodromo();
        apuestas = new ListaEnlazada();
        mostrarCompetidores();

    }

    /**
     * metodo para abrir una nueva ventana para agregar una apuesta.
     */

    public void nuevaApuesta() {
        NewApuestaFrame newApuesta = new NewApuestaFrame(apuestas);
        newApuesta.setVisible(true);
    }

    /**
     * metodo para abrir una nueva ventana para agregar los resultados de la
     * carrera.
     */

    public void inputResults() {
        InputResultsFrame results = new InputResultsFrame(apuestas);
        results.setVisible(true);
    }

    /**
     * metodo para abrir una nueva ventana para mostrar los resultados de las
     * apuestas.
     */

    public void showResults() {
        if (apuestas.size() > 0 && Hipodromo.COMPETIDORES[0].getLugar() > 0) {
            ResultsFrame resultsFrame = new ResultsFrame(apuestas);
            resultsFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(frame, "No se han ingresado apuestas o resultados", "Sin resultados",
                    JOptionPane.WARNING_MESSAGE);
        }

    }
    
    public void showReports(){
        ReportsFrame reports = new ReportsFrame();
        reports.setVisible(true);
    }

    /**
     * metodo colocar en una tabla los competidores que compiten.
     */

    private void mostrarCompetidores() {
        Object[][] datos = new Object[Hipodromo.COMPETIDORES.length][2];

        for (int i = 0; i < Hipodromo.COMPETIDORES.length; i++) {
            datos[i][0] = Hipodromo.COMPETIDORES[i].getNumero();
            datos[i][1] = Hipodromo.COMPETIDORES[i].getName();
        }

        frame.getTablaCompetidores().setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[] {
                        "No.", "Nombre"
                }) {
            Class[] types = new Class[] {
                    java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[] {
                    false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        frame.getTablaCompetidores().getColumnModel().getColumn(0).setWidth(20);
        frame.getTablaCompetidores().setRowHeight(30);
    }

}
