import Servicios.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
        int idAlumno = obtenerId(scanner, "Servicios.Alumno");
        String apellido = obtenerCampo(scanner, "Apellido");
        String nombre = obtenerCampo(scanner, "Nombre");
        String dni = obtenerCampo(scanner, "DNI");

        // Agregar el alumno
        alumnos.add(new Alumno(idAlumno, apellido, nombre, dni));
        System.out.println("Alumno cargado exitosamente.");
    }

    private static void cargarDocentes(Scanner scanner) {
        int idDocente = obtenerId(scanner, "Servicios.Docente");
        String apellido = obtenerCampo(scanner, "Apellido");
        String nombre = obtenerCampo(scanner, "Nombre");
        String dni = obtenerCampo(scanner, "DNI");

        // Agregar el docente
        docentes.add(new Docente(idDocente, apellido, nombre, dni));
        System.out.println("Docente cargado exitosamente.");
    }

    private static void cargarAsignaturas(Scanner scanner) {
        int idAsignatura = obtenerId(scanner, "Servicios.Asignatura");
        String nombreAsignatura = obtenerCampo(scanner, "Nombre de Asignatura");
        int añoEstudio = obtenerAñoEstudio(scanner);

        // Agregar la asignatura
        asignaturas.add(new Asignatura(idAsignatura, nombreAsignatura, añoEstudio));
        System.out.println("Asignatura cargada exitosamente.");
    }

    private static int obtenerId(Scanner scanner, String tipo) {
        int id = -1;
        while (true) {
            try {
                System.out.print("Ingrese ID " + tipo + ": ");
                id = scanner.nextInt();
                scanner.nextLine(); // Limpiamos el buffer
                if (id <= 0) {
                    throw new IllegalArgumentException("El ID debe ser un número positivo.");
                }
                break; // Salimos del bucle si la entrada es válida
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor ingrese un número entero para el ID.");
                scanner.nextLine(); // Limpiamos el buffer
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return id;
    }

    private static String obtenerCampo(Scanner scanner, String campo) {
        String valor;
        while (true) {
            System.out.print("Ingrese " + campo + ": ");
            valor = scanner.nextLine();
            if (valor.trim().isEmpty()) {
                System.out.println("Error: " + campo + " no puede estar vacío.");
            } else {
                break; // Salimos del bucle si la entrada es válida
            }
        }
        return valor;
    }

    private static int obtenerAñoEstudio(Scanner scanner) {
        int añoEstudio = -1;
        while (true) {
            try {
                System.out.print("Ingrese Año de Estudio: ");
                añoEstudio = scanner.nextInt();
                scanner.nextLine(); // Limpiamos el buffer
                if (añoEstudio <= 0) {
                    throw new IllegalArgumentException("El año debe ser un número positivo.");
                }
                break; // Salimos del bucle si la entrada es válida
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor ingrese un número entero para el año de estudio.");
                scanner.nextLine(); // Limpiamos el buffer
            }
        }
        return añoEstudio;
    }

    private static void crearMesaExamen(Scanner scanner) {
        int idMesa = obtenerId(scanner, "Mesa");
        mostrarAsignaturas();

        System.out.print("Seleccione ID Asignatura: ");
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
        while (true) {
            System.out.print("Ingrese fecha y hora (YYYY-MM-DDTHH:MM): ");
            String fechaHoraInput = scanner.nextLine();
            try {
                fechaHora = LocalDateTime.parse(fechaHoraInput);
                break; // La fecha es válida
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Formato de fecha y hora inválido. Inténtelo de nuevo.");
            }
        }

        cronos.add(new CronoMesaExamen(idMesa, asignatura, fechaHora));
        System.out.println("Mesa de examen creada exitosamente.");
    }

    private static void inscribirAlumno(Scanner scanner) {
        mostrarAlumnos();
        int idAlumno = obtenerId(scanner, "Alumno");

        Alumno alumno = alumnos.stream()
                .filter(a -> a.getIdAlumno() == idAlumno)
                .findFirst()
                .orElse(null);
        if (alumno == null) {
            System.out.println("Alumno no encontrado.");
            return;
        }

        mostrarMesasExamen();
        int idMesa = obtenerId(scanner, "Mesa");

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
        String carpeta = "C:/MesasExamen/";
        String nombreArchivo = "informe_mesas_examen.txt";

        // Asegúrate de que la ruta de la carpeta termine con una barra
        File directorio = new File(carpeta);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Carpeta creada: " + carpeta);
            } else {
                System.out.println("Error al crear la carpeta: " + carpeta);
                return;
            }
        }

        String rutaArchivo = carpeta + nombreArchivo;

        try (FileWriter writer = new FileWriter(rutaArchivo)) {
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
            System.out.println("Informe generado exitosamente en: " + rutaArchivo);
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
