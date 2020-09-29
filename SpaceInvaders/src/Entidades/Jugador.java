/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Juego.Pintado;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

/**
 *
 * @author cr-al
 */
public class Jugador extends Personaje implements KeyListener {

    int jugador;
    
    public Jugador(int inicio_x, int inicio_y, int tipo,Pintado dibujo)
    {
        super(inicio_x,inicio_y, dibujo);
        this.jugador = tipo;
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
        if(this.jugador == 1) movJugador1(tecla);
            else movJugador2(tecla);
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
                // Mover nave a la izquierda
                movIzq();
                break;
            }
            case 's':{
                
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
            // Realizar Movimiento
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

    
}
