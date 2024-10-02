import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Alumno> alumnos = new ArrayList<>();
        List<Asignatura> asignaturas = new ArrayList<>();
        List<Examen> examenes = new ArrayList<>();

        while (true) {
            System.out.println("\n--- Menú de Opciones ---");
            System.out.println("1. Agregar Alumno");
            System.out.println("2. Agregar Asignatura");
            System.out.println("3. Agregar Examen");
            System.out.println("4. Inscribir Alumno a Examen");
            System.out.println("5. Imprimir Inscripción");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1: // Agregar Alumno
                    System.out.print("Nombre del alumno: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Apellido del alumno: ");
                    String apellido = scanner.nextLine();
                    System.out.print("DNI del alumno: ");
                    String dni = scanner.nextLine();
                    List<Asignatura> adeudadas = new ArrayList<>();
                    alumnos.add(new Alumno(nombre, apellido, dni, adeudadas));
                    System.out.println("Alumno agregado.");
                    break;

                case 2: // Agregar Asignatura
                    System.out.print("Nombre de la asignatura: ");
                    String nombreAsignatura = scanner.nextLine();
                    System.out.print("Año de estudio: ");
                    int añoEstudio = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    asignaturas.add(new Asignatura(nombreAsignatura, añoEstudio));
                    System.out.println("Asignatura agregada.");
                    break;

                case 3: // Agregar Examen
                    System.out.print("Nombre de la asignatura para el examen: ");
                    String asignaturaExamen = scanner.nextLine();
                    Asignatura asignaturaEncontrada = null;
                    for (Asignatura asig : asignaturas) {
                        if (asig.getNombre().equalsIgnoreCase(asignaturaExamen)) {
                            asignaturaEncontrada = asig;
                            break;
                        }
                    }
                    if (asignaturaEncontrada != null) {
                        System.out.print("Fecha del examen (dd-MM-yyyy): ");
                        String fechaExamenStr = scanner.nextLine();
                        // Debes parsear la fecha correctamente
                        Date fechaExamen = new Date(); // Cambia esto por el parseo real
                        Examen examen = new Examen(asignaturaEncontrada, fechaExamen);
                        examenes.add(examen);
                        System.out.println("Examen agregado.");
                    } else {
                        System.out.println("Asignatura no encontrada.");
                    }
                    break;

                case 4: // Inscribir Alumno a Examen
                    System.out.print("DNI del alumno: ");
                    String dniInscripcion = scanner.nextLine();
                    Alumno alumnoInscripcion = null;
                    for (Alumno alum : alumnos) {
                        if (alum.getDni().equals(dniInscripcion)) {
                            alumnoInscripcion = alum;
                            break;
                        }
                    }
                    if (alumnoInscripcion != null) {
                        System.out.print("Nombre de la asignatura para inscribir: ");
                        String asignaturaInscripcion = scanner.nextLine();
                        Examen examenInscripcion = null;
                        for (Examen exam : examenes) {
                            if (exam.getAsignatura().getNombre().equalsIgnoreCase(asignaturaInscripcion)) {
                                examenInscripcion = exam;
                                break;
                            }
                        }
                        if (examenInscripcion != null) {
                            examenInscripcion.inscribirAlumno(alumnoInscripcion);
                            System.out.println("Alumno inscrito en el examen.");
                        } else {
                            System.out.println("Examen no encontrado.");
                        }
                    } else {
                        System.out.println("Alumno no encontrado.");
                    }
                    break;

                case 5: // Imprimir Inscripción
                    System.out.print("DNI del alumno: ");
                    String dniImprimir = scanner.nextLine();
                    Alumno alumnoImprimir = null;
                    for (Alumno alum : alumnos) {
                        if (alum.getDni().equals(dniImprimir)) {
                            alumnoImprimir = alum;
                            break;
                        }
                    }
                    if (alumnoImprimir != null) {
                        System.out.print("Nombre de la asignatura para imprimir inscripción: ");
                        String asignaturaImprimir = scanner.nextLine();
                        for (Examen exam : examenes) {
                            if (exam.getAsignatura().getNombre().equalsIgnoreCase(asignaturaImprimir)) {
                                Inscripcion inscripcion = new Inscripcion(alumnoImprimir, exam);
                                inscripcion.imprimirInscripcion();
                                break;
                            }
                        }
                    } else {
                        System.out.println("Alumno no encontrado.");
                    }
                    break;

                case 6: // Salir
                    System.out.println("Saliendo de la aplicación.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción inválida, por favor intente de nuevo.");
                    break;
            }
        }
    }
}

