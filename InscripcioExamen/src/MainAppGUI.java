import Servicios.*;
import javax.swing.*;
import java.awt.*;
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
            JButton btnVerEliminarAlumnos = new JButton("Ver/Eliminar Alumnos");
            JButton btnCargarDocentes = new JButton("Cargar Docentes");
            JButton btnVerEliminarDocentes = new JButton("Ver/Eliminar Docentes");
            JButton btnCargarAsignaturas = new JButton("Cargar Asignaturas");
            JButton btnVerEliminarAsignaturas = new JButton("Ver/Eliminar Asignaturas");
            JButton btnCrearMesaExamen = new JButton("Crear Mesa de Examen");
            JButton btnInscribirAlumno = new JButton("Inscribir Alumno a Mesa");
            JButton btnGenerarInforme = new JButton("Generar Informe");
            JButton btnSalir = new JButton("Salir");

            // Añadir acciones a los botones
            btnCargarAlumnos.addActionListener(e -> cargarAlumnos());
            btnVerEliminarAlumnos.addActionListener(e -> verYEliminarAlumnos());
            btnCargarDocentes.addActionListener(e -> cargarDocentes());
            btnVerEliminarDocentes.addActionListener(e -> verYEliminarDocentes());
            btnCargarAsignaturas.addActionListener(e -> cargarAsignaturas());
            btnVerEliminarAsignaturas.addActionListener(e -> verYEliminarAsignaturas());
            //btnCrearMesaExamen.addActionListener(e -> crearMesaExamen());
            //btnInscribirAlumno.addActionListener(e -> inscribirAlumno());
            //btnGenerarInforme.addActionListener(e -> generarInforme());
            btnSalir.addActionListener(e -> System.exit(0));

            // Añadir botones al marco
            frame.add(btnCargarAlumnos);
            frame.add(btnVerEliminarAlumnos);
            frame.add(btnCargarDocentes);
            frame.add(btnVerEliminarDocentes);
            frame.add(btnCargarAsignaturas);
            frame.add(btnVerEliminarAsignaturas);
            frame.add(btnCrearMesaExamen);
            frame.add(btnInscribirAlumno);
            frame.add(btnGenerarInforme);
            frame.add(btnSalir);

            frame.setVisible(true);
        });
    }

    private static void cargarAlumnos() {
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

    private static void verYEliminarAlumnos() {
        if (alumnos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay alumnos para mostrar.");
            return;
        }

        DefaultListModel<Alumno> listModel = new DefaultListModel<>();
        for (Alumno alumno : alumnos) {
            listModel.addElement(alumno);
        }

        JList<Alumno> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);

        int option = JOptionPane.showConfirmDialog(null, scrollPane, "Selecciona Alumnos para Eliminar", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            List<Alumno> selectedAlumnos = list.getSelectedValuesList();
            for (Alumno selected : selectedAlumnos) {
                alumnos.remove(selected);
            }
            JOptionPane.showMessageDialog(null, "Alumnos eliminados exitosamente.");
        }
    }

    private static void cargarDocentes() {
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

    private static void verYEliminarDocentes() {
        if (docentes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay docentes para mostrar.");
            return;
        }

        DefaultListModel<Docente> listModel = new DefaultListModel<>();
        for (Docente docente : docentes) {
            listModel.addElement(docente);
        }

        JList<Docente> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);

        int option = JOptionPane.showConfirmDialog(null, scrollPane, "Selecciona Docentes para Eliminar", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            List<Docente> selectedDocentes = list.getSelectedValuesList();
            for (Docente selected : selectedDocentes) {
                docentes.remove(selected);
            }
            JOptionPane.showMessageDialog(null, "Docentes eliminados exitosamente.");
        }
    }

    private static void cargarAsignaturas() {
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

    private static void verYEliminarAsignaturas() {
        if (asignaturas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay asignaturas para mostrar.");
            return;
        }

        DefaultListModel<Asignatura> listModel = new DefaultListModel<>();
        for (Asignatura asignatura : asignaturas) {
            listModel.addElement(asignatura);
        }

        JList<Asignatura> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);

        int option = JOptionPane.showConfirmDialog(null, scrollPane, "Selecciona Asignaturas para Eliminar", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            List<Asignatura> selectedAsignaturas = list.getSelectedValuesList();
            for (Asignatura selected : selectedAsignaturas) {
                asignaturas.remove(selected);
            }
            JOptionPane.showMessageDialog(null, "Asignaturas eliminadas exitosamente.");
        }
    }

    // Otros métodos (crearMesaExamen, inscribirAlumno, generarInforme) se mantienen sin cambios
}
