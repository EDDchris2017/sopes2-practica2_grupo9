/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Juego.Pintado;

/**
 *
 * @author cr-al
 */
public abstract class Personaje {
    
    public int pos_x;
    public int pos_y;
    public Pintado dibujo;
    
    public Personaje(int pos_x, int pos_y, Pintado dibujo)
    {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }
    
    /**
     * Pintar personaje en tablero
     */
    public abstract void pintar();
}
