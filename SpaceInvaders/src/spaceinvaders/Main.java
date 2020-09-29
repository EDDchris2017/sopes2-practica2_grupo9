/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import Juego.Juego;
import Ventana.Ventana;

/**
 *
 * @author cr-al
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // **** Panel de Configuracion ****
        final int velocidad_enemigos = 2;       // Velocidad de descenso de los enemigos
        final int tiempo_partida     = 120;     // Duracion de la partida de la segundos
        final int cuadro_dibujo      = 50;      // Longitud lineas cuadro
        
        // Iniciar Juego
        Juego juego = new Juego(velocidad_enemigos, tiempo_partida, cuadro_dibujo);
        
        juego.iniciarJuego();
        
    }
    
}
