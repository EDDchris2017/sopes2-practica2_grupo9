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
    
    Jugador j1;
    Jugador j2;
    
    boolean partida_ganada;
    
    public Juego(int velocidad_enemigos,int tiempo_partida,int cuadro_dibujo)
    {
        this.velocidad_enemigos = velocidad_enemigos;
        this.tiempo_partida     = tiempo_partida;
        this.cuadro_dibujo      = cuadro_dibujo;
        this.ventana            = new Ventana();
        this.dibujo             = new Pintado(ventana, cuadro_dibujo);
        partida_ganada          = false;
    }
    
    public void iniciarJuego()
    {
        // **** Preparar Tablero *****
        this.dibujo.pintarTablero();
        
        // **** Creando Jugadores ****
        Jugador nj1 = crearJugador(1);
        Jugador nj2 = crearJugador(2);
        this.j1 = nj1;
        this.j2 = nj2;
        ExecutorService ejecutorJugadores = Executors.newCachedThreadPool();
        ejecutorJugadores.execute(nj1);
        ejecutorJugadores.execute(nj2);
        this.ventana.addKeyListener(nj1);
        this.ventana.addKeyListener(nj2);
        
        // **** Creando Enemigos ****
        Thread hilo_enemigos = new Thread(generarEnemigos);
        hilo_enemigos.start();
        // **** Revisando tiempo de juego ****
        Thread hilo_tiempo = new Thread(revisarFrecuencia);
        hilo_tiempo.start();
        
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
                while(ventana.isVisible() &&  !partida_ganada)
                {
                    int tiempo_llegada = 5700 - 1000*(fe_enemiga);
                    //Salir si va muy rapido
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
    
    public Runnable revisarFrecuencia = new Runnable() {
        @Override
        public void run() {
            try{
                int contador = 0;
                while(ventana.isVisible() || !partida_ganada)
                {
                    if( partidaPerdida())
                        {
                            JOptionPane.showMessageDialog(null,"SE PERDIO LA PARTIDA !!!");
                        }else if ( partidaGanada())
                        {
                            JOptionPane.showMessageDialog(null,"GANARON LA PARTIDA !!!");
                            partida_ganada = true;
                        }
                    if(contador > 25 )
                    {
                        contador = 0;
                        fe_enemiga++;
                        
                    }
                    
                    ventana.contFe(contador);
                    ventana.mostrarFe(fe_enemiga);
                    contador ++;
                    Thread.sleep(1000);
                }
            }catch(InterruptedException ex)
            {
                JOptionPane.showMessageDialog(null, "Error al generar enemigos");
            }
           
        }
    };
    
    private boolean partidaPerdida()
    {
        return this.j1.murio() && this.j2.murio();
    }
    
    private boolean partidaGanada()
    {
        return this.fe_enemiga > 4;
    }
    
    
}
