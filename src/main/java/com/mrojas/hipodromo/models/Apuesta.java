/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mrojas.hipodromo.models;

import java.util.Arrays;

/**
 *
 * @author manu
 */
public class Apuesta {

    private Apostador apostador;
    private Horse[] horses;
    private double monto;
    private long timeVerificar;
    private long timePuntaje;
    private int pasosVerificar;
    private int pasosPuntaje;
    public static int maxPasoVerificar = 0;
    public static int maxPasoPuntaje = 0;
    public static int menPasoVerificar = 6;
    public static int menPasoPuntaje = 54;

    /**
     * Constructor para crear una nueva apuesta
     * 
     * @param apostador nombre de la persona que está apostando
     * @param monto     el monto que apostará la persona
     * @param horses    arreglo con los caballos a los que les apostó
     */

    public Apuesta(String apostador, double monto, Horse[] horses) {
        this.apostador = new Apostador(apostador);
        this.monto = monto;
        this.horses = horses;
    }

    /**
     * Método que valida si la apuesta es válida o no. Posee un time complexity de
     * O(n)
     * 
     * @return retorna false si existe algún caballo repetido dentro de la apuesta,
     *         de lo contrario es true.
     */

    public boolean isValid() {
        this.horses = alphabeticSort(horses);
        pasosVerificar = 2;
        long inicio = System.nanoTime();
        long fin;
        for (int i = 0; i < horses.length - 1; i++) {    //2n+1
            pasosVerificar += 3;
            if (horses[i].equals(horses[i + 1])) {       //(n-1)*1
                pasosVerificar ++;
                setMaxVerificar(pasosVerificar);
                setMenVerificar(pasosVerificar);
                fin = System.nanoTime();
                timeVerificar = (fin-inicio);
                return false;                           //(n-1)*1
            }
        }
        setMaxVerificar(pasosVerificar);
        setMenVerificar(pasosVerificar);
        fin = System.nanoTime();
        timeVerificar = (fin-inicio);
        return true;
    }

    /**
     * Método que calcula el punteo obtenido por el apostador en función de los
     * caballos a los que les apostó. Tiene un time complexity de O(n)
     * 
     * @param ganadores arreglo de los caballos con los cuales se comparará para
     *                  calcular los resultados.
     */
    public void calcularPunteo(Horse[] ganadores) {                     //1
        pasosPuntaje = 4;
        long inicio = System.nanoTime();
        int punteo = 10;                                                //1

        for (int i = 0; i < ganadores.length; i++) {                    //2n+2
            if (ganadores[i].getLugar() == horses[i].getLugar()) {      //n
                pasosPuntaje++;
                apostador.setPuntaje(punteo);                           //n
            }
            punteo--;                                                   //n
            pasosPuntaje += 4;
        }
        setMaxPuntaje(pasosPuntaje);
        setMenPuntaje(pasosPuntaje);
        long fin = System.nanoTime();
        timePuntaje = (fin-inicio);
    }

    public Apostador getApostador() {
        return apostador;
    }

    public Horse[] getHorses() {
        return horses;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public long getTimeVerificar(){
        return timeVerificar;
    }

    public long getTimePuntaje(){
        return timePuntaje;
    }

    

    public int getPasosVerificar() {
        return pasosVerificar;
    }

    public int getPasosPuntaje() {
        return pasosPuntaje;
    }

    private void setMaxVerificar(int max){
        if (max > maxPasoVerificar) {
            maxPasoVerificar = max;
        }
    }

    private void setMaxPuntaje(int max){
        if (max > maxPasoPuntaje) {
            maxPasoPuntaje = max;
        }
    }

    private void setMenVerificar(int men){
        if (men < menPasoVerificar) {
            menPasoVerificar = men;
        }
    }

    private void setMenPuntaje(int men){
        if (men < menPasoPuntaje) {
            menPasoPuntaje = men;
        }
    }

    private static Horse[] alphabeticSort(Horse[] array) { // insertion sort
        Horse key;
        int j;
        for (int i = 1; i < array.length; i++) {
            key = array[i];
            j = i - 1;

            while ((j >= 0) && key.getName().compareTo(array[j].getName()) < 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        return array;
    }

    @Override
    public String toString() {
        return "Apuesta [apostador=" + apostador + ", horses=" + Arrays.toString(horses) + ", monto=" + monto + "]";
    }

}