/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mrojas.hipodromo.controllers;

import com.mrojas.hipodromo.archives.ArchivesController;
import com.mrojas.hipodromo.models.Apuesta;
import com.mrojas.hipodromo.models.Hipodromo;
import com.mrojas.hipodromo.models.Horse;
import com.mrojas.hipodromo.util.ListaEnlazada;
import com.mrojas.hipodromo.views.NewApuestaFrame;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author manu
 */
public class NewApuestaController {

    private NewApuestaFrame frame;
    private DefaultListModel<Horse> model;
    private int cantHorses = 0;
    private ListaEnlazada apuestas;

    /**
     * Constructor para el controlador de la ventana para agregar una nueva apuesta.
     * @param frame el frame correspondiente a la ventana para una nueva apuesta.
     * @param apuestas listado de las apuestas acumuladas.
     */
    
    public NewApuestaController(NewApuestaFrame frame, ListaEnlazada apuestas) {
        this.apuestas = apuestas;
        this.frame = frame;
        frame.setIconImage(new ImageIcon(getClass().getResource("/images/hipodromo.png")).getImage());
        listarCompetidores();
        model = new DefaultListModel<>();
        frame.getBtmAgregarApuesta().setEnabled(false);
    }

    /**
     * Metodo para agregar el orden de llegada de un caballo dentro de la nueva apuesta.
     */
    
    public void agregarCaballo(){
        Horse select = (Horse) frame.getListaCompetidores().getSelectedItem();
        Horse nuevo = new Horse(select.getName(), select.getNumero());
        nuevo.setLugar(cantHorses+1);
        model.addElement(nuevo);
        frame.getListaCaballos().setModel(model);
        cantHorses++;
        actualizarBoton();
    }

    /**
     * metodo para validar y agregar una nueva apuesta.
     */
    
    public void agregarApuesta(){
        String name = frame.getTxtName().getText();
        String monto = frame.getTxtMonto().getText();
        
        if (name == null || monto == null || name.isBlank() || monto.isBlank() || !monto.matches("\\d*(\\.\\d+)?")) {
            JOptionPane.showMessageDialog(frame, "Nombre o monto inválido", "Entrada inválida", JOptionPane.WARNING_MESSAGE);
        }else{
            apuestas.push(new Apuesta(name, Double.parseDouble(monto), listCaballos()));
            clear();
            JOptionPane.showMessageDialog(frame, "¡Apuesta ingresada!", "Apuesta ingresada", JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * Metodo que carga el archivo de entrada seleccionado por el usuario.
     */
    
    public void cargarTxtFile(){
        ArchivesController.addApuestasFile(ArchivesController.getFileChooserPath(), apuestas);
        JOptionPane.showMessageDialog(null, "Se ha cargado el archivo de entrada", "Archivo cargado", JOptionPane.PLAIN_MESSAGE);
    }
    
    public void saveFile(){
        ArchivesController.guardarArchivo(ArchivesController.getFileChooserPath(), apuestas);
    }

    /**
     * metodo para limpiar los campos del frame
     */
    
    public void clear() {
        frame.getTxtMonto().setText("");
        frame.getTxtName().setText("");
        cantHorses = 0;
        model.removeAllElements();
        actualizarBoton();
    }
    
    private Horse[] listCaballos(){       
        Horse[] horses = new Horse[10];
        
        for(int i = 0; i < 10; i++){
            horses[i] = model.getElementAt(i);
        }
        return horses;
    }
    
    
    
    private void actualizarBoton(){
        if (cantHorses >= Hipodromo.COMPETIDORES.length) {
            frame.getBtmAgregarCaballo().setEnabled(false);
            frame.getBtmAgregarApuesta().setEnabled(true);
        }else{
            frame.getBtmAgregarCaballo().setText("Agregar " + (cantHorses+1) + "° Lugar" );
            frame.getBtmAgregarApuesta().setEnabled(false);
            frame.getBtmAgregarCaballo().setEnabled(true);
        }
        
        
    }

    private void listarCompetidores() {
        for (int i = 0; i < Hipodromo.COMPETIDORES.length; i++) {
            frame.getListaCompetidores().addItem(Hipodromo.COMPETIDORES[i]);
        }
    }

}
