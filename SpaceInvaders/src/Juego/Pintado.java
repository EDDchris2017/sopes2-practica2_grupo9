/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Entidades.Casilla;
import Ventana.Ventana;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author cr-al
 */
public class Pintado {
    JPanel panel_juego;     // Panel del Juego
    Ventana ventana;        // Ventana del juego
    int tam_cuadro;         // Tamaño del cuadrado
    public Casilla[][] tablero; //Tablero de Juego en Memoria
    int filas;
    int columnas;
    
    final String player1_img = "jugador1.png";
    final String player2_img = "jugador2.png";
    final String vacio_img   = "espacio.jpg";
    final String disparo_img = "disparo.png";
    final String enemigo_img = "enemigo.png";
    final String explosion_img ="explosion.png";
    final String dolor_j1 = "dolorj1.png";
    final String dolor_j2 = "dolorj2.png";
    
    public Pintado(Ventana ventana, int tam_cuadro)
    {
        this.ventana     = ventana;
        this.panel_juego = ventana.panel_juego;
        this.tam_cuadro  = tam_cuadro;
    }
    
    public Casilla[][] pintarTablero()
    {
       this.panel_juego.setSize(600, 600);
       this.filas    = (int)this.panel_juego.getWidth() / tam_cuadro;
       this.columnas = (int)this.panel_juego.getHeight() / tam_cuadro;
       this.tablero  = new Casilla[this.filas][this.columnas];
       this.panel_juego.setLayout(new GridLayout(this.filas, this.columnas));
       
       for(int x = 0; x < this.filas ; x++)
       {
           for(int y = 0; y < this.columnas; y++)
           {
               Casilla nueva_casilla = new Casilla(tam_cuadro);
               //nueva_casilla.setImage(new File(".").getAbsolutePath().replace(".","") + "imagenes\\" + this.vacio_img, 0);
               tablero[x][y] = nueva_casilla;
               this.panel_juego.add(tablero[x][y]);
           }
       }
       this.panel_juego.repaint();
       return tablero;
        
    }
    
    /**
     * Validar que el punto a pintar o despintar esta dentro del limite
     * @param pos_x
     * @param pos_y
     * @return Booleano indicando si el punto es valido
     */
    public boolean puntoValido(int pos_x, int pos_y)
    {
        return ((pos_x >= 0 && pos_x <this.filas && pos_y >= 0 && pos_y < this.columnas));
    }
    
    public boolean hayAmigo(int x,int y)
    {
        return this.tablero[x][y].getOcupado() == 2 || this.tablero[x][y].getOcupado() == 1;
    }
    
    public boolean hayEnemigo(int x,int y)
    {
        return this.tablero[x][y].getOcupado() == 3;
    }
    
    /**
     * 
     * @param pos_x posicion x del tablero
     * @param pos_y posicion y del tablero 
     * @param tipo  4 = disparo ; 3 = enemigo ; 2 = amigo2 ; 1 = amigo1 ; 0 = nada 
     */
    public void pintar(int pos_x,int pos_y,int tipo)
    {
        // Pintar posicion
        String img_ruta = new File(".").getAbsolutePath().replace(".","") + "imagenes\\";
        switch(tipo)
        {
            case 4:
                img_ruta += this.disparo_img;
                break;
            case 3:
                img_ruta += this.enemigo_img;
                break;
            case 2:
                img_ruta += this.player2_img;
                break;
            case 1:
                img_ruta += this.player1_img;
                break;
            default:
                img_ruta += this.vacio_img;
        }
        actualizarTablero(pos_x, pos_y, img_ruta, tipo);
    }
    
    public void pintarExplosion(int x, int y)
    {
        String img_ruta = new File(".").getAbsolutePath().replace(".","") + "imagenes\\" + this.explosion_img;
        actualizarTablero(x, y, img_ruta, 0);
        
    }
    
    public void pintarDolor(int x, int y, int jugador)
    {
        String img_ruta = new File(".").getAbsolutePath().replace(".","") + "imagenes\\" + ((jugador == 1) ? this.dolor_j1 : this.dolor_j2);
        actualizarTablero(x, y, img_ruta, jugador);
        
    }
    
    private void actualizarTablero(int x,int y, String img, int contenido)
    {
        this.tablero[x][y].setImage(img, contenido);
        this.tablero[x][y].repaint();
        this.panel_juego.repaint();
    }
    
    public void despintar(int x, int y)
    {
        //String ruta = new File(".").getAbsolutePath().replace(".","") + "imagenes\\" + this.vacio_img;
        String ruta = "";
        this.tablero[x][y].setImage(ruta,0);
    }
    
    // ******************************* COMPONENTES DE VISTA ********************************
    public void mostrarVida(int tipo_jugador, int vida)
    {
        this.ventana.mostrarVida(tipo_jugador, vida);
    }

}
