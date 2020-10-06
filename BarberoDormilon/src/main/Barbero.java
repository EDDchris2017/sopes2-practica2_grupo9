package main;

import javax.swing.*;

public class Barbero implements Runnable {

    private Establecimiento area;

    public Barbero (Establecimiento area){
        this.area = area;
    }

    @Override
    public void run() {
        while (true) {
            try {
                area.atender();
                Thread.sleep((int)(Math.random() * 10001));
                // el corte está hecho
                System.out.println("Barbero:    Terminó de cortar!");
                dibujar();
            } catch ( InterruptedException exception ) {
                exception.printStackTrace();
            }
        }
    }

    private static void dibujar() throws InterruptedException {
        Principal.gui.getLblBarbero().setIcon(new ImageIcon(Principal.gui.getClass().getResource("/images/celebration.png")));
        Principal.gui.getLblBarbero().setText("Finalizado!");
        Thread.sleep(2000);
    }

}
