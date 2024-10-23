import java.util.concurrent.ThreadLocalRandom;

public class TallerDeHerramientas {
    public static void main(String[] args) throws Exception {
        // array para guardar 5 objetos Thread, que guarda alumno que ejecuta la tarea
        // concurrente (Alumno)
        Thread[] listaAlumnos = new Thread[5];

        for (int i = 0; i < listaAlumnos.length; i++) {
            listaAlumnos[i] = new Thread(new Alumno("Alumno " + i));
            listaAlumnos[i].start();
        }
    }

    private static class Alumno implements Runnable {

        private String nomAlumno;

        public Alumno(String nomAlumno) {
            this.nomAlumno = nomAlumno;

        }

        public String getNomAlumno() {
            return nomAlumno;
        }



        private static int sacarNumAleatorio() {
            return ThreadLocalRandom.current().nextInt(0, 9 + 1);
        }

        @Override
        public void run() {

            while (true) {
                BancoHerramientas.cogerHerramienta(sacarNumAleatorio(), sacarNumAleatorio(), getNomAlumno());

                // Cuando el hilo acaba con las herramientas descansa un tiempo aleatorio entre
                // 1 y 2 segundos
                try {
                    int descansoAleatorio = ThreadLocalRandom.current().nextInt(1000, 2000 + 1);
                    Thread.sleep(descansoAleatorio);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static class Herramienta {
        private String nombreHerramienta;

        public Herramienta(String nombreHerramienta) {
            this.nombreHerramienta = nombreHerramienta;
        }

        public String getNombreHerramienta() {
            return nombreHerramienta;
        }

    }

    private static class BancoHerramientas {

        static final Herramienta[] BANCO_HERRAMIENTAS = {

                // creo mis herramientas
                new Herramienta("alicate"),
                new Herramienta("martillo"),
                new Herramienta("metro"),
                new Herramienta("nivel"),
                new Herramienta("taladro"),
                new Herramienta("cortador de metal"),
                new Herramienta("destornillador de estrella"),
                new Herramienta("destornillador plano"),
                new Herramienta("serrucho"),
                new Herramienta("llave inglesa"),

        };

        // metodo para que el alumno coja dos herramientas del banco de herramientas
        public static void cogerHerramienta(int numAleatorio1, int numAleatorio2, String nombreAlumno) {

            // creo una lista que guarda las herramientas usadas por el alumno
            Herramienta[] listaHerramientaAlumno = new Herramienta[2];

            // creo una matriz para guardar las herramientas que el alumno coge
            listaHerramientaAlumno[0] = BANCO_HERRAMIENTAS[numAleatorio1];
            listaHerramientaAlumno[1] = BANCO_HERRAMIENTAS[numAleatorio2];

            // solucion: si los numeros aleatorios son iguales
            if (numAleatorio1 == numAleatorio2) {
                // creo un nuevo numero para asignarle otro indice hasta q sea diferente
                int nuevoNummero;

                do {
                    nuevoNummero = Alumno.sacarNumAleatorio();
                } while (nuevoNummero == numAleatorio1);
                // le asigno la herramienta distinta al segundo indice del array
                listaHerramientaAlumno[1] = BANCO_HERRAMIENTAS[nuevoNummero];
            }

            // sincronizo las dos herramientas para que otro alumno no las puedan usar hasta
            // q termine
            synchronized (listaHerramientaAlumno[0]) {

                synchronized (listaHerramientaAlumno[1]) {

                    // muestra el nombre del alumno y las herramientas que escogio
                    System.out.println(nombreAlumno + " ha cogido estas herramientas: "
                            + listaHerramientaAlumno[0].getNombreHerramienta()
                            + " y " + listaHerramientaAlumno[1].getNombreHerramienta());

                    // genero un numero aleatorio de tiempo para que el alumno trabaje con la
                    // herramienta, entre 2 y 3 segundos
                    try {
                        int timpoAleatorioTrabajoHerramientas = ThreadLocalRandom.current().nextInt(2000, 3000 + 1);
                        // duermo el hilo durante este tiempo
                        Thread.sleep(timpoAleatorioTrabajoHerramientas);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    // muestro el alumno que termina de trabajar con las herramientas
                    System.out.println(nombreAlumno + " ha acabado con las herramientas");
                }
            }

        }

    }

}
