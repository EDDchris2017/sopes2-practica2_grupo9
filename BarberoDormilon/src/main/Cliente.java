package main;

public class Cliente implements Runnable {

    private Establecimiento area;

    public Cliente(Establecimiento area){
        this.area = area;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep((int) (Math.random() * 1001));
                area.recibir(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}