/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mrojas.hipodromo.models;

/**
 *
 * @author manu
 */
public class Apostador {
    String name;
    int puntaje;

    /**
     * Constructor para un nuevo apostador.
     * 
     * @param name el nombre que tendrá el apostador.
     */

    public Apostador(String name) {
        this.name = name;
        this.puntaje = 0;
    }

    public String getName() {
        return name;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje += puntaje;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + puntaje;
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
        Apostador other = (Apostador) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (puntaje != other.puntaje)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Apostador [name=" + name + ", puntaje=" + puntaje + "]";
    }

}
