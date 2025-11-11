package HiloDormilon;

/**
 * La clase HiloDormilon extiende la clase {@link Thread} para crear un hilo
 * que simula un comportamiento de "sueño" durante un tiempo determinado.
 * Puede ser interrumpido durante su estado de reposo.
 *
 * @author TuNombre
 * @version 1.0
 */
class HiloDormilon extends Thread {

    /**
     * El método `run` es el punto de entrada para la ejecución del hilo.
     * El hilo imprimirá un mensaje indicando que se va a dormir, luego
     * entrará en un estado de espera (dormido) durante 30 segundos. Si el hilo
     * completa su sueño sin interrupciones, imprimirá un segundo mensaje.
     * Si el hilo es interrumpido mientras duerme, capturará la
     * {@link InterruptedException} y mostrará un mensaje indicando que ha sido
     * despertado.
     */
    @Override
    public void run() {
        try{
            System.out.println("Uyyyy que sueeñoo me voy a dormir...");
            // El hilo se duerme durante 30000 milisegundos (30 segundos).
            Thread.sleep(30000);
            System.out.println("Zzzzzzzzz");
        }catch (InterruptedException e){
            // Este bloque se ejecuta si se llama al método interrupt() sobre este hilo
            // mientras está en el estado de espera (durmiendo).
            System.out.println("¡Uy va!, me han despertado");
        }
    }
}

/**
 * La clase Main es la clase principal que demuestra el ciclo de vida y la
 * interrupción de un objeto {@link HiloDormilon}.
 */
public class Main {

    /**
     * El método `main` es el punto de entrada de la aplicación.
     * Crea, inicia, monitoriza, interrumpe y espera la finalización de un hilo
     * `HiloDormilon`, mostrando su estado en cada etapa clave de su ciclo de vida.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan en esta aplicación).
     */
    public static void main(String[] args) {
        try {
            // 1. CREACIÓN DEL HILO
            System.out.println("Iniciando hilo");
            Thread dormilon = new HiloDormilon();
            // Imprime el estado del hilo justo después de su creación. El estado será NEW.
            System.out.println("Estado del hilo: " + dormilon.getState());  //NEW

            // 2. INICIO Y EJECUCIÓN
            System.out.println("Ejecutando hilo");
            // Inicia la ejecución del hilo. La JVM llamará al método run() de la clase HiloDormilon.
            dormilon.start();
            // Imprime el estado del hilo inmediatamente después de llamar a start(). El estado será RUNNABLE.
            System.out.println("Estado del hilo: " + dormilon.getState());  //RUNNABLE

            // 3. HILO DUERME
            // El hilo principal (main) se detiene durante 2 segundos.
            // Esto se hace para asegurar que el hilo 'dormilon' ha tenido tiempo de entrar
            // en su propio método sleep() y cambiar su estado.
            Thread.sleep(2000); //<--¡CUIDADO! No confundir con dormilon.sleep(2000);
            // Imprime el estado del hilo 'dormilon'. Para este momento, estará en estado TIMED_WAITING
            // porque está dentro de su Thread.sleep(30000).
            System.out.println("Estado del hilo: " + dormilon.getState());  //TIME_WAITING

            System.out.println("Interrumpo el hilo");
            // Interrumpe el hilo 'dormilon'. Esto causará que el método sleep() dentro
            // del hilo 'dormilon' lance una InterruptedException.
            dormilon.interrupt();
            // Imprime el estado del hilo justo después de la interrupción. El estado puede variar
            // rápidamente, pero a menudo volverá a ser RUNNABLE brevemente mientras maneja la excepción.
            System.out.println("Estado del hilo: " + dormilon.getState());

            // Espera a que el hilo 'dormilon' termine completamente su ejecución antes de continuar.
            dormilon.join();
            // Imprime el estado final del hilo. Una vez que el método run() ha finalizado,
            // el estado del hilo será TERMINATED.
            System.out.println("Estado del hilo: " + dormilon.getState());  //TERMINATED

        }catch (InterruptedException e){
            // Este bloque catch manejaría una InterruptedException si el hilo principal (main)
            // fuera interrumpido por otro hilo mientras está en sleep() o join().
            System.out.println("Uy va!, me han despertado");
        }
    }
}
