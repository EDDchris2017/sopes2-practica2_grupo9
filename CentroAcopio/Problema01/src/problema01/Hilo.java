/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problema01;

import java.util.Random;

/**
 *
 * @author ddani
 */
public class Hilo implements Runnable {

    ColaCajas<Integer> cola;
    int numero;

    public Hilo(ColaCajas<Integer> cola, int numero) {
        this.cola = cola;
        this.numero = numero;
    }

    @Override
    public void run() {
        Random r = new Random();
        int operacion = r.nextInt(2);

        if (operacion == 0) {
            cola.insertar(numero);
        } else {
            cola.eliminar();
        }
    }

}
