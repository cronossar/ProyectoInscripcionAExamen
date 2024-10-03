import Servicios.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainAppGUI {
    private static List<Alumno> alumnos = new ArrayList<>();
    private static List<Docente> docentes = new ArrayList<>();
    private static List<Asignatura> asignaturas = new ArrayList<>();
    private static List<CronoMesaExamen> cronos = new ArrayList<>();
    private static List<InscripMesaExamen> inscripciones = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestión de Mesas de Examen");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setLayout(new GridLayout(0, 1));

            // Botones
            JButton btnCargarAlumnos = new JButton("Cargar Alumnos");
            JButton btnCargarDocentes = new JButton("Cargar Docentes");
            JButton btnCargarAsignaturas = new JButton("Cargar Asignaturas");
            JButton btnCrearMesaExamen = new JButton("Crear Mesa de Examen");
            JButton btnInscribirAlumno = new JButton("Inscribir Alumno a Mesa");
            JButton btnGenerarInforme = new JButton("Generar Informe");
            JButton btnSalir = new JButton("Salir");

            // Añadir acciones a los botones
            btnCargarAlumnos.addActionListener(e -> cargarAlumnos());
            btnCargarDocentes.addActionListener(e -> cargarDocentes());
            btnCargarAsignaturas.addActionListener(e -> cargarAsignaturas());
            btnCrearMesaExamen.addActionListener(e -> crearMesaExamen());
            btnInscribirAlumno.addActionListener(e -> inscribirAlumno());
            btnGenerarInforme.addActionListener(e -> generarInforme());

            btnSalir.addActionListener(e -> System.exit(0));

            // Añadir botones al marco
            frame.add(btnCargarAlumnos);
            frame.add(btnCargarDocentes);
            frame.add(btnCargarAsignaturas);
            frame.add(btnCrearMesaExamen);
            frame.add(btnInscribirAlumno);
            frame.add(btnGenerarInforme);
            frame.add(btnSalir);

            frame.setVisible(true);
        });
    }

    private static void cargarAlumnos() {
        // Implementar lógica de carga de alumnos usando un diálogo
        JTextField idField = new JTextField();
        JTextField apellidoField = new JTextField();
        JTextField nombreField = new JTextField();
        JTextField dniField = new JTextField();

        Object[] message = {
                "ID Alumno:", idField,
                "Apellido:", apellidoField,
                "Nombre:", nombreField,
                "DNI:", dniField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Cargar Alumno", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int idAlumno = Integer.parseInt(idField.getText());
            String apellido = apellidoField.getText();
            String nombre = nombreField.getText();
            String dni = dniField.getText();

            alumnos.add(new Alumno(idAlumno, apellido, nombre, dni));
            JOptionPane.showMessageDialog(null, "Alumno cargado exitosamente.");
        }
    }

    private static void cargarDocentes() {
        // Implementar lógica de carga de docentes similar a cargarAlumnos
        JTextField idField = new JTextField();
        JTextField apellidoField = new JTextField();
        JTextField nombreField = new JTextField();
        JTextField dniField = new JTextField();

        Object[] message = {
                "ID Docente:", idField,
                "Apellido:", apellidoField,
                "Nombre:", nombreField,
                "DNI:", dniField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Cargar Docente", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int idDocente = Integer.parseInt(idField.getText());
            String apellido = apellidoField.getText();
            String nombre = nombreField.getText();
            String dni = dniField.getText();

            docentes.add(new Docente(idDocente, apellido, nombre, dni));
            JOptionPane.showMessageDialog(null, "Docente cargado exitosamente.");
        }
    }

    private static void cargarAsignaturas() {
        // Implementar lógica de carga de asignaturas similar a cargarAlumnos
        JTextField idField = new JTextField();
        JTextField nombreField = new JTextField();
        JTextField añoField = new JTextField();

        Object[] message = {
                "ID Asignatura:", idField,
                "Nombre Asignatura:", nombreField,
                "Año de Estudio:", añoField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Cargar Asignatura", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int idAsignatura = Integer.parseInt(idField.getText());
            String nombreAsignatura = nombreField.getText();
            int añoEstudio = Integer.parseInt(añoField.getText());

            asignaturas.add(new Asignatura(idAsignatura, nombreAsignatura, añoEstudio));
            JOptionPane.showMessageDialog(null, "Asignatura cargada exitosamente.");
        }
    }

    private static void crearMesaExamen() {
        // Implementar lógica de creación de mesa de examen
        JTextField idField = new JTextField();
        JTextField asignaturaField = new JTextField();
        JTextField fechaHoraField = new JTextField();

        Object[] message = {
                "ID Mesa:", idField,
                "ID Asignatura:", asignaturaField,
                "Fecha y Hora (YYYY-MM-DDTHH:MM):", fechaHoraField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Crear Mesa de Examen", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int idMesa = Integer.parseInt(idField.getText());
            int idAsignatura = Integer.parseInt(asignaturaField.getText());
            LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraField.getText());

            Asignatura asignatura = asignaturas.stream()
                    .filter(a -> a.getIdAsignatura() == idAsignatura)
                    .findFirst()
                    .orElse(null);

            if (asignatura != null) {
                cronos.add(new CronoMesaExamen(idMesa, asignatura, fechaHora));
                JOptionPane.showMessageDialog(null, "Mesa de examen creada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Asignatura no encontrada.");
            }
        }
    }

    private static void inscribirAlumno() {
        if (alumnos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay alumnos disponibles para inscribir.");
            return;
        }

        if (cronos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay mesas de examen disponibles para inscribir.");
            return;
        }

        // Mostrar lista de alumnos
        StringBuilder alumnosList = new StringBuilder("Seleccione un Alumno:\n");
        for (Alumno alumno : alumnos) {
            alumnosList.append(alumno.getIdAlumno()).append(": ").append(alumno.getNombre()).append(" ").append(alumno.getApellido()).append("\n");
        }

        String idAlumnoInput = JOptionPane.showInputDialog(null, alumnosList.toString());
        if (idAlumnoInput == null) return; // Cancelar

        int idAlumno;
        try {
            idAlumno = Integer.parseInt(idAlumnoInput);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.");
            return;
        }

        Alumno alumno = alumnos.stream()
                .filter(a -> a.getIdAlumno() == idAlumno)
                .findFirst()
                .orElse(null);

        if (alumno == null) {
            JOptionPane.showMessageDialog(null, "Alumno no encontrado.");
            return;
        }

        // Mostrar lista de mesas de examen
        StringBuilder mesasList = new StringBuilder("Seleccione una Mesa de Examen:\n");
        for (CronoMesaExamen mesa : cronos) {
            mesasList.append(mesa.getIdMesa()).append(": ").append(mesa.getAsignatura().getNombreAsignatura()).append("\n");
        }

        String idMesaInput = JOptionPane.showInputDialog(null, mesasList.toString());
        if (idMesaInput == null) return; // Cancelar

        int idMesa;
        try {
            idMesa = Integer.parseInt(idMesaInput);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.");
            return;
        }

        CronoMesaExamen mesaExamen = cronos.stream()
                .filter(m -> m.getIdMesa() == idMesa)
                .findFirst()
                .orElse(null);

        if (mesaExamen == null) {
            JOptionPane.showMessageDialog(null, "Mesa de examen no encontrada.");
            return;
        }

        // Inscribir alumno
        inscripciones.add(new InscripMesaExamen(alumno, mesaExamen));
        JOptionPane.showMessageDialog(null, "Alumno inscrito a la mesa de examen exitosamente.");
    }


    private static void generarInforme() {
        // Implementar lógica de generación de informe
        JOptionPane.showMessageDialog(null, "Informe generado exitosamente.");
    }
}

