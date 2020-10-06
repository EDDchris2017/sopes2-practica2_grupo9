package main;

import gui.Window;

public class Principal {

    static Window gui;

    public static void main(String[] args) {

        gui = new Window();
        gui.setVisible(true);

        Establecimiento area = new Establecimiento(20);

        // Creating producer and consumer threads
        Thread producer = new Thread(new Cliente(area));
        Thread consumer = new Thread(new Barbero(area));

        producer.start();
        consumer.start();

    }

}
