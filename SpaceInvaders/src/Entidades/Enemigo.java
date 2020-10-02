/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Juego.Pintado;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
            while(this.vida > 0 && this.dibujo.puntoValido(pos_x, pos_y)){
                pintar();
                if(revisarChoque()) break;
                Thread.sleep(this.tiempo_llegada);
                if(revisarChoque()) break;
                despintar();
                
                this.pos_x++;
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Enemigo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void pintar() {
        this.dibujo.pintar(pos_x, pos_y, 3, this);
    }

    @Override
    public void despintar() {
        this.dibujo.despintar(pos_x, pos_y);
    }
    
    private void movAbajo()
    {
        int npos_x = this.pos_x + 1;
        this.pos_x = npos_x;
    }
    
    public Runnable revisarDaño = new Runnable() {
        @Override
        public void run() {
                while(vida > 0 && dibujo.puntoValido(pos_x, pos_y))
                {
                    revisarDaño();
                }
        }
    };
    
    private void revisarDaño()
    {
        if ( this.dibujo.hayDisparo(pos_x, pos_y) ) this.vida--;
    }
    
    private boolean revisarChoque()
    {
        return this.dibujo.hayAmigo(pos_x, pos_y);

    }
    
    private void revisarSalud()
    {
        if(this.dibujo.hayDisparo(pos_x, pos_y)) this.vida --;
    }
    
    public void daño()
    {
        this.vida--;
    }
}
