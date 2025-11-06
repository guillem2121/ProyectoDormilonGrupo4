package HiloDormilon;

//TODO: complicar codigo y hacer javadoc
//Comentario Guillermo
//Comentario Olga

class HiloDormilon extends Thread {
    @Override
    public void run() {
        try{
            System.out.println("Uyyyy que sueeñoo me voy a dormir...");
            Thread.sleep(30000);
            System.out.println("Zzzzzzzzz");
        }catch (InterruptedException e){
            System.out.println("¡Uy va!, me han despertado");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            //1. CREACIÓN DEL HILO
            System.out.println("Iniciando hilo");
            Thread dormilon = new HiloDormilon();
            System.out.println("Estado del hilo: " + dormilon.getState());  //NEW

            //2. INICIO Y EJECUCIÓN
            System.out.println("Ejecutando hilo");
            dormilon.start();
            System.out.println("Estado del hilo: " + dormilon.getState());  //RUNNABLE

            //3. HILO DUERME
            Thread.sleep(2000); //<--¡CUIDADO! No confundir con dormilon.sleep(2000);
            System.out.println("Estado del hilo: " + dormilon.getState());  //TIME_WAITING

            System.out.println("Interrumpo el hilo");
            dormilon.interrupt();
            System.out.println("Estado del hilo: " + dormilon.getState());

            dormilon.join();
            System.out.println("Estado del hilo: " + dormilon.getState());  //TERMINATED

        }catch (InterruptedException e){
            System.out.println("Uy va!, me han despertado");
        }
    }
}
