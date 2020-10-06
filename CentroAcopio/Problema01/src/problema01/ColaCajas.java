/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problema01;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 *
 * @author ddani
 */
public class ColaCajas<E> {

    int maximo;
    int entraron;
    int salieron;
    LinkedList<E> cola;
    ReentrantLock bloqueo;
    Condition noVacio;
    Condition noLleno;
    JLabel cajas[];
    JLabel llega;
    JLabel recoger;
    JLabel entra;
    JLabel sale;

    public ColaCajas(int maximo, JLabel[] cajas, JLabel llega, JLabel recoger,JLabel entra, JLabel sale) {
        this.maximo = maximo;
        cola = new LinkedList<>();
        bloqueo = new ReentrantLock(true);
        noVacio = bloqueo.newCondition();
        noLleno = bloqueo.newCondition();
        this.cajas = cajas;
        this.llega = llega;
        this.recoger = recoger;
        this.entraron = 0;
        this.salieron = 0;
        this.entra = entra;
        this.sale = sale;
    }

    public void insertar(E elemento) {
        bloqueo.lock();
        try {
            int num_llega = Integer.parseInt(llega.getText());
            llega.setText(String.valueOf(num_llega+1));
            while (cola.size() == maximo) {
                noLleno.await();
            }
            cola.add(elemento);
            pintar();
            this.entraron++;
            Random ran = new Random();
            int tiempo = ran.nextInt(45);
            Thread.sleep(tiempo);
            int num_llega_f = Integer.parseInt(llega.getText());
            llega.setText(String.valueOf(num_llega_f-1));
            this.entra.setText(String.valueOf(entraron));
            noVacio.signalAll();
        } catch (Exception e) {
            System.out.println("En insertar");
            System.out.println(e);
        } finally {
            bloqueo.unlock();
        }
    }

    public E eliminar() {
        bloqueo.lock();
        try {
            int num_rec = Integer.parseInt(recoger.getText());
            recoger.setText(String.valueOf(num_rec+1));
            while (cola.isEmpty()) {
                noVacio.await();
            }
            E elemento = cola.remove();
            pintar();
            this.salieron++;
            Random ran = new Random();
            int tiempo = ran.nextInt(45);
            Thread.sleep(tiempo);
            int num_rec_f = Integer.parseInt(recoger.getText());
            recoger.setText(String.valueOf(num_rec_f-1));
            sale.setText(String.valueOf(salieron));
            noLleno.signalAll();
            return elemento;
        } catch (Exception e) {
            System.out.println("En eliminar");
            System.out.println(e);
            return null;
        } finally {
            bloqueo.unlock();
        }
    }

    private void pintar() {
        Border lleno = BorderFactory.createLineBorder(Color.ORANGE, 5);
        for (int x = 0; x < cola.size(); x++) {
            cajas[x].setBorder(lleno);
        }
        Border vacio = BorderFactory.createLineBorder(Color.black, 5);
        for (int x = cola.size(); x < maximo; x++) {
            cajas[x].setBorder(vacio);
        }
    }
}
