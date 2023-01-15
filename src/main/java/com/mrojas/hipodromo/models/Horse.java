/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mrojas.hipodromo.models;

import java.util.Random;

/**
 *
 * @author manu
 */
public class Horse {
    public static final Random random = new Random();

    private String name;
    private int numero;
    private int lugar;

    /**
     * Constructor para crear un nuevo caballo
     * 
     * @param name el nombre que tendrá el caballo
     * @param numero el número que tendrá el caballo
     */

    public Horse(String name, int numero) {
        this.name = name;
        this.numero = numero;
    }

    public String getName() {
        return name;
    }

    public int getLugar() {
        return lugar;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }
    
    public int getNumero(){
        return numero;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Horse other = (Horse) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return (numero) + ". " + name;
    }
}
