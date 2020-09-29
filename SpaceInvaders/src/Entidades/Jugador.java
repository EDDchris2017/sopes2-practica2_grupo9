/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Juego.Pintado;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch(e.getKeyChar())
        {
            case 'a':{
                // Mover nave a la izquierda
                movIzq();
            }
            case 's':{
                
            }
            case 'd':{
                
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void movIzq()
    {
        int npos_y = this.pos_y - 1;
        if ( this.dibujo.puntoValido(this.pos_x, this.pos_y) )
        {
            this.pos_y = npos_y;
            this.dibujo.pintar(pos_x, pos_y, this.jugador);
        }
    }
    
    private void movDer()
    {
        int npos_y = this.pos_y + 1;
        if ( this.dibujo.puntoValido(this.pos_x, this.pos_y) )
        {
            this.pos_y = npos_y;
            this.dibujo.pintar(pos_x, pos_y, this.jugador);
        }
    }
}
