/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.awt.Color;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author cr-al
 */
public class Casilla extends JLabel{
    
    //4 = disparo ; 3 = enemigo ; 2 = amigo2 ; 1 = amigo1 ; 0 = nada 
    int ocupado;
    
    public Casilla(int cuadro){
        super();
        super.setSize(cuadro, cuadro);
        super.setBackground(null);
        this.ocupado = 0;
    }
    
    
    public void setImage(String ruta,int ocupado)
    {
        this.ocupado = ocupado;
        ImageIcon fot = new ImageIcon(ruta);
        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(super.getWidth(), super.getHeight(), Image.SCALE_DEFAULT));
        super.setIcon(icono);
        super.repaint();
    }
    
    public int getOcupado()
    {
        return this.ocupado;
    }
         
            
    /**
     * ImageIcon fot = new ImageIcon(path_ala_imagen);
Icon icono = new ImageIcon(fot.getImage().getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_DEFAULT));
jLabel1.setIcon(icono);
this.repaint();
     */
}
