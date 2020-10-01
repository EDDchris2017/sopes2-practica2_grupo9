/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Juego.Pintado;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cr-al
 */
public class Enemigo extends Personaje implements Runnable{

    int vida;
    int tiempo_llegada;
    
    public Enemigo(int pos_x,int pos_y,int fe_llegada, Pintado dibujo)
    {
        super(pos_x,pos_y,dibujo);
        this.vida = 2;
        this.tiempo_llegada = 800;
        if(tiempo_llegada <= 0) this.tiempo_llegada = 100;
    }
    
    @Override
    public void run() {
        try {
            pintar();
            while(this.dibujo.puntoValido(pos_x, pos_y)){
                Thread.sleep(this.tiempo_llegada);
                // Mover 
                if(!movAbajo())break;
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Enemigo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void pintar() {
        this.dibujo.pintar(pos_x, pos_y, 3);
    }

    @Override
    public void despintar() {
        this.dibujo.despintar(pos_x, pos_y);
    }
    
    private boolean movAbajo()
    {
        int npos_x = this.pos_x + 1;
        if ( this.dibujo.puntoValido(npos_x, pos_y) )
        {
            despintar();
            this.pos_x = npos_x;
            pintar();
            if (revisarChoque()) return false;
        }else
        {
            despintar();
        }
        return true;
    }
    
    private boolean revisarChoque()
    {
        if ( this.dibujo.hayAmigo(pos_x, pos_y)){
            this.dibujo.despintar(pos_x, pos_y);
            return true;
        }
        return false;

    }
}
