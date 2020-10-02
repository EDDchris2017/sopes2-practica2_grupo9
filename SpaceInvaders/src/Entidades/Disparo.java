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
public class Disparo extends Personaje implements Runnable{

    int avance;
    public Disparo(int x, int y, Pintado dibujo)
    {
        super(x, y, dibujo);
        this.avance = 50;
    }
    
    @Override
    public void run() {
        try {
            while(this.dibujo.puntoValido(pos_x, pos_y))
            {
                if(hayImpacto()) break;
                pintar();
                Thread.sleep(this.avance);
                despintar();
                
                this.pos_x--;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Enemigo.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public void pintar() {
        this.dibujo.pintar(pos_x, pos_y, 4, this);
    }

    @Override
    public void despintar() {
        this.dibujo.despintar(pos_x, pos_y);
    }
    
    public boolean hayImpacto()
    {
       // Verificar si es un enemigo
        if( this.dibujo.hayEnemigo(pos_x, pos_y) )
        {
            Enemigo enemigo = (Enemigo)this.dibujo.getPersonaje(pos_x, pos_y);
            enemigo.daño();
            return true;
        }
        return false;
    }
    
}
