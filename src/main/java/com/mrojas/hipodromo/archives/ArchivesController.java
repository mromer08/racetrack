/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mrojas.hipodromo.archives;

import com.mrojas.hipodromo.archives.analizador.lexer.Lexer;
import com.mrojas.hipodromo.archives.analizador.parser.Parser;
import com.mrojas.hipodromo.models.Apuesta;
import com.mrojas.hipodromo.models.Horse;
import com.mrojas.hipodromo.util.ListaEnlazada;
import java.io.*;
import java.nio.charset.Charset;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author manu
 */
public class ArchivesController {

    private static Parser parser;
    private static Lexer lexer;

    /**
     * Método para solicitarle al usuario una ruta en el ordenador
     *
     * @return Retorna la ruta que seleccionó el jugador, en caso de no hacerlo
     * retorna nulo.
     */
    public static String getFileChooserPath() {
        JFileChooser fileChooser = new JFileChooser("Seleccione la ruta");
        fileChooser.setApproveButtonText("Ok");
        fileChooser.showOpenDialog(null);
        return fileChooser.getSelectedFile().getPath();
    }

    /**
     * Metodo para leer archivos en forma de texto
     *
     * @param path Se requiere indicar la ruta del archivo que se quiere leer
     * @return Retorna una lista de Strings con el contenido del archivo
     */
    public static void addApuestasFile(String path, ListaEnlazada add) {
        //List<String> response = new ArrayList<>();
        Charset utf8 = Charset.forName("UTF-8");
        try (
                 BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path), utf8));) {
            lexer = new Lexer(in);
            parser = new Parser(lexer);

            try {
                parser.setApuestas(add);
                parser.parse();
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ocurrió un error al leer el archivo", "Archivo de entrada", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar cargar el archivo", "Archivo no cargado", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void guardarArchivo(String path, ListaEnlazada apuestas) {
        File archivo = new File(path+".csv");
        ListaEnlazada copyApuesta = new ListaEnlazada();
        try ( PrintWriter buffer = new PrintWriter(new FileWriter(archivo, true))) {

            while (apuestas.size() > 0) {                
                Apuesta aux = apuestas.pop();
                copyApuesta.push(aux);
                buffer.print(aux.getApostador().getName() + ", " + aux.getMonto());
                for (int i = 0; i < aux.getHorses().length; i++) {
                    buffer.print(", " + aux.getHorses()[i].getNumero());
                }
                buffer.println();
                
            }
            apuestas = copyApuesta;
            // buffer.close();
            JOptionPane.showMessageDialog(null, "Archivo guardado con éxito", "Archivo guardado", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar crear el archivo", "Archivo no generado", JOptionPane.ERROR_MESSAGE);
        }
    }

}
