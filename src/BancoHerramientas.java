

import java.util.concurrent.ThreadLocalRandom;

public class BancoHerramientas {


    static final Herramienta[] BANCO_HERRAMIENTAS = {
       
        new Herramienta("Sierra de mano"),
        new Herramienta("Martillo"),
        new Herramienta("Metro"),
        new Herramienta("Nivel"),
        new Herramienta("Calibre"),
        new Herramienta("Soldador"),
        new Herramienta("Destornillador de estrella"),
        new Herramienta("Destornillador plano"),
        new Herramienta("Destornillador torks"),
        new Herramienta("Llave inglesa"),

    };


   public static void cogerHerramienta(int numAle1, int numAle2, String nomAl) {
        //Creo una lista donde guardare las herramientas usadas por el alumno
        Herramienta[] listaHerramientaAlumno = new Herramienta[2];

        //Asigno esas herramientas a cada posicion del array anterior
        listaHerramientaAlumno[0]= BANCO_HERRAMIENTAS[numAle1];
        listaHerramientaAlumno[1]= BANCO_HERRAMIENTAS[numAle2];



        //solucion: si los numeros aleatorios que envia el alumno son iguales
        if (numAle1 == numAle2){

            int nuevoNum;

            do {
                nuevoNum = Alumno.sacarNumAleatorio();
            }while (nuevoNum == numAle1);

            listaHerramientaAlumno[1]= BANCO_HERRAMIENTAS[nuevoNum];
        }

        synchronized (listaHerramientaAlumno[0]) {

            synchronized (listaHerramientaAlumno[1]) {

                //Muestra el nombre del alumno y las herramientas que ha escogido
                System.out.println(nomAl + " ha cogido estas herramientas: " + listaHerramientaAlumno[0].getNombreHerramienta()
                        + " y " + listaHerramientaAlumno[1].getNombreHerramienta());

                //Elige un numero aleatorio de tiempo el cual esta trabajando con las herramientas
                try {
                    int timpoAleatorioUsoHerramientas = ThreadLocalRandom.current().nextInt(2000, 3000 + 1);
                    Thread.sleep(timpoAleatorioUsoHerramientas);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                //muestro que el alumno finalizo de trabajar con las herramientas
                System.out.println(nomAl + " ha acabado con las herramientas");
            }
        }

    }



}
