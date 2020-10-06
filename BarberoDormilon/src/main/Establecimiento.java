package main;

import javax.swing.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Establecimiento {

    int count;
    final int maxSillas;
    final Object[] array;
    int putIndex, takeIndex;
    boolean barberoEstaDurmiendo;

    final ReentrantLock lock = new ReentrantLock(true);
    // Conditions
    final Condition produceCond  = lock.newCondition();
    final Condition consumeCond = lock.newCondition();

    public Establecimiento (int maxSillas) {
        this.maxSillas = maxSillas;
        // Array to store element for CustomBlockingQueue
        array = new Object[maxSillas];
        // El barbero al inicio está durmiendo
        barberoEstaDurmiendo = true;
    }

    public void recibir(Object x) throws InterruptedException {
        lock.lock();
        System.out.println("Area:       Llega un nuevo cliente!");
        try {
            while (count == array.length){
                // Queue is full, producers need to wait
                System.err.println("Area:       Lugar lleno, cliente se va ...");
                produceCond.await();
            }
            array[putIndex] = x;
            //System.out.println("Producing - " + x);
            putIndex++;
            if (putIndex == array.length){
                putIndex = 0;
            }
            // Increment the count for the array
            ++count;
            repaintSillas();
            if (barberoEstaDurmiendo) {
                barberoEstaDurmiendo = false;
                System.out.println("Barbero:    Un cliente me desperto!");
                dibujar("wake-up", "Despierte!");
            }
            consumeCond.signal();
        } finally {
            lock.unlock();
        }
    }

    public void atender() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0){
                // Queue is empty, consumers need to wait
                barberoEstaDurmiendo = true;
                System.out.println("Barbero:    Durmiendo ...");
                dibujar("sleeping", "Durmiendo ...");
                consumeCond.await();
            }
            Object x = array[takeIndex];
            //System.out.println("Consuming - " + x);
            takeIndex++;
            if (takeIndex == array.length){
                takeIndex = 0;
            }
            // reduce the count for the array
            --count;
            repaintSillas();
            System.out.println("Barbero:    Cortando pelo!");
            dibujar("cutting-hair", "Trabajando!");
            // send signal producer
            produceCond.signal();
        } finally {
            lock.unlock();
        }
    }

    private static void dibujar(String imagen, String mensaje) throws InterruptedException {
        Principal.gui.getLblBarbero().setIcon(new ImageIcon(Principal.gui.getClass().getResource("/images/"+ imagen +".png")));
        Principal.gui.getLblBarbero().setText(mensaje);
        Thread.sleep(1000);
    }

    public void repaintSillas() {
        for (int i = 0; i < maxSillas; i++) {
            if (i < count) {
                Principal.gui.getSillas()[i].setIcon(new ImageIcon(Principal.gui.getClass().getResource("/images/waiting.png")));
            } else {
                Principal.gui.getSillas()[i].setIcon(new ImageIcon(Principal.gui.getClass().getResource("/images/empty-chair.png")));
            }
        }
    }

}
