/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Juego.Pintado;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author cr-al
 */
public class Jugador extends Personaje implements KeyListener,Runnable {

    int jugador;
    int vida;
    
    public Jugador(int inicio_x, int inicio_y, int tipo,Pintado dibujo)
    {
        super(inicio_x,inicio_y, dibujo);
        this.jugador = tipo;
        this.vida = 3;
    }
    
    @Override
    public void pintar() {
        this.dibujo.pintar(pos_x, pos_y, this.jugador);
    }
    
    @Override
    public void despintar() {
        this.dibujo.despintar(pos_x, pos_y);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char tecla = e.getKeyChar();
        if(this.vida > 0){
            if(this.jugador == 1) movJugador1(tecla);
                else movJugador2(tecla);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void movJugador1(char tecla)
    {
        switch(tecla)
        {
            case 'a':{
                // Mover nave a la izquierda
                movIzq();
                break;
            }
            case 's':{
                break;
            }
            case 'd':{
                movDer();
                break;
            }
        }
    }
    
    private void movJugador2(char tecla)
    {
        switch(tecla)
        {
            case 'j':{
                movIzq();
                break;
            }
            case 'k':{
                break;
            }
            case 'l':{
                movDer();
                break;
            }
        }
    }
    
    // ************************************* MOVIMIENTOS NAVE ****************************

    private void movIzq()
    {
        int npos_y = this.pos_y - 1;
        if ( moverValido(this.pos_x, npos_y) )
        {
            despintar();
            this.pos_y = npos_y;
            pintar();
        }
    }
    
    private void movDer()
    {
        int npos_y = this.pos_y + 1;
        if ( moverValido(this.pos_x, npos_y) )
        {
            despintar();
            this.pos_y = npos_y;
            pintar();
        }
    }
    
    private boolean moverValido(int x,int y)
    {
        return this.dibujo.puntoValido(x, y) && !this.dibujo.hayAmigo(x, y);
    }

    @Override
    public void run() {
        
        while(this.vida > 0)
        {
            revisarSalud();
            this.dibujo.mostrarVida(jugador, vida);
        }  
    }
    
    private void revisarSalud()
    {
         try {
        //Verificar si hay un enemigo
        if(this.dibujo.hayEnemigo(pos_x, pos_y))
        {
            this.vida --;
            if ( this.vida > 0) {
                this.dibujo.pintarDolor(pos_x, pos_y, jugador);
                Thread.sleep(600);
                this.dibujo.pintar(pos_x, pos_y, jugador);
            }
            else {
                this.dibujo.pintarExplosion(pos_x, pos_y);
                Thread.sleep(600);
                this.dibujo.despintar(pos_x, pos_y);
            }
        }
        }catch (InterruptedException ex) {
                Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
