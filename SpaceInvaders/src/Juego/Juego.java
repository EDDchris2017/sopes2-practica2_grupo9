/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Ventana.Ventana;
import javax.swing.JFrame;

/**
 *
 * @author cr-al
 */
public class Juego {
    
    final int velocidad_enemigos;
    final int tiempo_partida;
    final int cuadro_dibujo;
    final Ventana ventana;
    final Pintado dibujo;
    
    
    public Juego(int velocidad_enemigos,int tiempo_partida,int cuadro_dibujo)
    {
        this.velocidad_enemigos = velocidad_enemigos;
        this.tiempo_partida     = tiempo_partida;
        this.cuadro_dibujo      = cuadro_dibujo;
        this.ventana            = new Ventana();
        this.dibujo             = new Pintado(ventana.panel_juego, cuadro_dibujo);
    }
    
    public void iniciarJuego()
    {
        // Preparar Tablero
        this.dibujo.pintarTablero();
        // Mostrar Ventana de Juego
        this.ventana.repaint();
        this.ventana.setLayout(null);
        this.ventana.setVisible(true);
    }
    
    
    
}
