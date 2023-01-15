/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mrojas.hipodromo.models;

import com.mrojas.hipodromo.util.ListaEnlazada;

/**
 *
 * @author manu
 */
public class Hipodromo {

    public static final String[] NAME_HORSES = { "Affirmed","Assault","Citation", "Gallant Fox","Justify", "Omaha", "Secretariat", "Sir Barton",  "War Admiral", "Whirlaway"};
    
    /**
     * Arreglo de los competidores del hipodromo.
     */
    public static Horse[] COMPETIDORES = new Horse[10];

    public Hipodromo(){
        crearCompetidores();
        COMPETIDORES[9].setLugar(8);
    }

    private void crearCompetidores(){
        for (int i = 0; i < NAME_HORSES.length; i++) {
            COMPETIDORES[i] = new Horse(NAME_HORSES[i], i+1);
        }
    }    
}