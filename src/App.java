public class App {
    public static void main(String[] args) throws Exception {
        Thread[] listaAlumnos=new Thread[5];

        for (int i = 0; i < listaAlumnos.length; i++) {
            listaAlumnos[i]= new Thread(new Alumno("Alumno " + i));
            listaAlumnos[i].start();
    }
    }
}
