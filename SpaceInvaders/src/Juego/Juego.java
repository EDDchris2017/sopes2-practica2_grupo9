/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Entidades.Enemigo;
import Entidades.Jugador;
import Ventana.Ventana;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
    int fe_enemiga = 1;
    
    public Juego(int velocidad_enemigos,int tiempo_partida,int cuadro_dibujo)
    {
        this.velocidad_enemigos = velocidad_enemigos;
        this.tiempo_partida     = tiempo_partida;
        this.cuadro_dibujo      = cuadro_dibujo;
        this.ventana            = new Ventana();
        this.dibujo             = new Pintado(ventana, cuadro_dibujo);
    }
    
    public void iniciarJuego()
    {
        // **** Preparar Tablero *****
        this.dibujo.pintarTablero();
        
        // **** Creando Jugadores ****
        Jugador nj1 = crearJugador(1);
        Jugador nj2 = crearJugador(2);
        ExecutorService ejecutorJugadores = Executors.newCachedThreadPool();
        ejecutorJugadores.execute(nj1);
        ejecutorJugadores.execute(nj2);
        this.ventana.addKeyListener(nj1);
        this.ventana.addKeyListener(nj2);
        
        // **** Creando Enemigos ****
        ExecutorService generadorEnemigos = Executors.newSingleThreadExecutor();
        generadorEnemigos.execute(generarEnemigos);
        
        
        // Mostrar Ventana de Juego
        this.ventana.repaint();
        this.ventana.setLayout(null);
        this.ventana.setVisible(true);
    }
    
    /**
     * Crear un nuevo Jugador
     * @return 
     */
    private Jugador crearJugador(int tipo)
    {
        Jugador nj = new Jugador(this.dibujo.filas-2, (tipo == 1) ? 1 : this.dibujo.columnas - 2, tipo, dibujo);
        nj.pintar();
        return nj;
    }
    
    private Enemigo crearEnemigo(int frecuencia)
    {
        int inicio_y = getRandomColumnas();
        Enemigo ne = new Enemigo(0, inicio_y, frecuencia, this.dibujo);
        return ne;
    }
    
    private int getRandomColumnas()
    {
        return new Random().nextInt((this.dibujo.columnas - 1 - 0) + 1);
    }
    
    public Runnable generarEnemigos = new Runnable() {
        @Override
        public void run() {
            try{
                while(ventana.isVisible())
                {
                    int tiempo_llegada = 1500 - (100 * fe_enemiga);
                    Enemigo enemigo = crearEnemigo(tiempo_llegada);
                    Thread hilo_enemigo = new Thread(enemigo);
                    hilo_enemigo.start();
                    Thread.sleep(tiempo_llegada);
                }
            }catch(InterruptedException ex)
            {
                JOptionPane.showMessageDialog(null, "Error al generar enemigos");
            }
           
        }
    };
    
    
}
