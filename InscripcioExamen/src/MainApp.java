import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static List<Alumno> alumnos = new ArrayList<>();
    private static List<Docente> docentes = new ArrayList<>();
    private static List<Asignatura> asignaturas = new ArrayList<>();
    private static List<CronoMesaExamen> cronos = new ArrayList<>();
    private static List<InscripMesaExamen> inscripciones = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú Principal:");
            System.out.println("1. Cargar Alumnos");
            System.out.println("2. Cargar Docentes");
            System.out.println("3. Cargar Asignaturas");
            System.out.println("4. Crear Mesa de Examen");
            System.out.println("5. Inscribir Alumno a Mesa de Examen");
            System.out.println("6. Generar Informe");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    cargarAlumnos(scanner);
                    break;
                case 2:
                    cargarDocentes(scanner);
                    break;
                case 3:
                    cargarAsignaturas(scanner);
                    break;
                case 4:
                    crearMesaExamen(scanner);
                    break;
                case 5:
                    inscribirAlumno(scanner);
                    break;
                case 6:
                    generarInforme();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void cargarAlumnos(Scanner scanner) {
        System.out.print("Ingrese ID Alumno: ");
        int idAlumno = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese DNI: ");
        String dni = scanner.nextLine();

        alumnos.add(new Alumno(idAlumno, apellido, nombre, dni));
        System.out.println("Alumno cargado exitosamente.");
    }

    private static void cargarDocentes(Scanner scanner) {
        System.out.print("Ingrese ID Docente: ");
        int idDocente = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese DNI: ");
        String dni = scanner.nextLine();

        docentes.add(new Docente(idDocente, apellido, nombre, dni));
        System.out.println("Docente cargado exitosamente.");
    }

    private static void cargarAsignaturas(Scanner scanner) {
        System.out.print("Ingrese ID Asignatura: ");
        int idAsignatura = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese Nombre Asignatura: ");
        String nombreAsignatura = scanner.nextLine();
        System.out.print("Ingrese Año de Estudio: ");
        int añoEstudio = scanner.nextInt();

        asignaturas.add(new Asignatura(idAsignatura, nombreAsignatura, añoEstudio));
        System.out.println("Asignatura cargada exitosamente.");
    }

    private static void crearMesaExamen(Scanner scanner) {
        System.out.print("Ingrese ID Mesa: ");
        int idMesa = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.println("Seleccione ID Asignatura (0 para ver opciones): ");
        mostrarAsignaturas();
        int idAsignatura = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Asignatura asignatura = asignaturas.stream()
                .filter(a -> a.getIdAsignatura() == idAsignatura)
                .findFirst()
                .orElse(null);

        if (asignatura == null) {
            System.out.println("Asignatura no encontrada.");
            return;
        }

        LocalDateTime fechaHora = null;
        boolean fechaValida = false;

        while (!fechaValida) {
            System.out.print("Ingrese fecha y hora (YYYY-MM-DDTHH:MM): ");
            String fechaHoraInput = scanner.nextLine();

            try {
                fechaHora = LocalDateTime.parse(fechaHoraInput);
                fechaValida = true; // La fecha es válida
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Formato de fecha y hora inválido. Inténtelo de nuevo.");
            }
        }

        cronos.add(new CronoMesaExamen(idMesa, asignatura, fechaHora));
        System.out.println("Mesa de examen creada exitosamente.");
    }

    private static void inscribirAlumno(Scanner scanner) {
        System.out.print("Seleccione ID Alumno (0 para ver opciones): ");
        mostrarAlumnos();
        int idAlumno = scanner.nextInt();
        Alumno alumno = alumnos.stream()
                .filter(a -> a.getIdAlumno() == idAlumno)
                .findFirst()
                .orElse(null);
        if (alumno == null) {
            System.out.println("Alumno no encontrado.");
            return;
        }

        System.out.print("Seleccione ID Mesa (0 para ver opciones): ");
        mostrarMesasExamen();
        int idMesa = scanner.nextInt();
        CronoMesaExamen mesaExamen = cronos.stream()
                .filter(m -> m.getIdMesa() == idMesa)
                .findFirst()
                .orElse(null);
        if (mesaExamen == null) {
            System.out.println("Mesa de examen no encontrada.");
            return;
        }

        inscripciones.add(new InscripMesaExamen(alumno, mesaExamen));
        System.out.println("Alumno inscrito a la mesa de examen exitosamente.");
    }

    private static void generarInforme() {
        try (FileWriter writer = new FileWriter("C:\informe_mesas_examen.txt")) {
            for (CronoMesaExamen mesa : cronos) {
                writer.write("Mesa ID: " + mesa.getIdMesa() + "\n");
                writer.write("Asignatura: " + mesa.getAsignatura().getNombreAsignatura() + "\n");
                writer.write("Fecha y Hora: " + mesa.getFechaHora() + "\n");
                writer.write("Alumnos Inscritos:\n");

                for (InscripMesaExamen inscripcion : inscripciones) {
                    if (inscripcion.getCronoMesa().getIdMesa() == mesa.getIdMesa()) {
                        writer.write(" - " + inscripcion.getAlumno().getNombre() + " " + inscripcion.getAlumno().getApellido() + "\n");
                    }
                }
                writer.write("\n");
            }
            System.out.println("Informe generado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al generar el informe: " + e.getMessage());
        }
    }

    private static void mostrarAlumnos() {
        for (Alumno alumno : alumnos) {
            System.out.println("ID: " + alumno.getIdAlumno() + ", Nombre: " + alumno.getNombre() + " " + alumno.getApellido());
        }
    }

    private static void mostrarDocentes() {
        for (Docente docente : docentes) {
            System.out.println("ID: " + docente.getIdDocente() + ", Nombre: " + docente.getNombre() + " " + docente.getApellido());
        }
    }

    private static void mostrarAsignaturas() {
        for (Asignatura asignatura : asignaturas) {
            System.out.println("ID: " + asignatura.getIdAsignatura() + ", Nombre: " + asignatura.getNombreAsignatura());
        }
    }

    private static void mostrarMesasExamen() {
        for (CronoMesaExamen mesa : cronos) {
            System.out.println("ID: " + mesa.getIdMesa() + ", Asignatura: " + mesa.getAsignatura().getNombreAsignatura());
        }
    }
}
