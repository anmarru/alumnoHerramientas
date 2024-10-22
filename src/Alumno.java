

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Alumno implements Runnable {

    private String nomAlumno;
    private AtomicInteger id;

    public Alumno(String nomAlumno) {
        this.nomAlumno = nomAlumno;
        
    }

    public String getNomAlumno() {
        return nomAlumno;
    }

    public void setNomAlumno(String nomAlumno) {
        this.nomAlumno = nomAlumno;
    }

    public static int sacarNumAleatorio(){
        return ThreadLocalRandom.current().nextInt(0,9+1);
    }

    @Override
    public void run() {

        //Se ejecuta de manera infinita
        while (true) {
            BancoHerramientas.cogerHerramienta(sacarNumAleatorio(),sacarNumAleatorio(), getNomAlumno());

            //Cuando el hilo acaba con las herramientas descansa un tiempo aleatorio
            try {
                int timpoAleatorioDescando = ThreadLocalRandom.current().nextInt(1000,2000+1);
                Thread.sleep(timpoAleatorioDescando);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}