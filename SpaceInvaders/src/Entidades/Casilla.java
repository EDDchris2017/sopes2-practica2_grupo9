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
    
    //3 = disparo ; 2 = enemigo ; 1 = amigo ; 0 = nada 
    int ocupado;
    
    public Casilla(int cuadro){
        super();
        super.setSize(cuadro, cuadro);
        super.setBackground(null);
        this.ocupado = 0;
    }
    
    
    public void setImage(String ruta,int ocupado)
    {
        
        ImageIcon fot = new ImageIcon(ruta);
        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(super.getWidth()+34, super.getHeight(), Image.SCALE_DEFAULT));
        super.setIcon(icono);
        super.repaint();
    }
    
    
         
            
    /**
     * ImageIcon fot = new ImageIcon(path_ala_imagen);
Icon icono = new ImageIcon(fot.getImage().getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_DEFAULT));
jLabel1.setIcon(icono);
this.repaint();
     */
}
