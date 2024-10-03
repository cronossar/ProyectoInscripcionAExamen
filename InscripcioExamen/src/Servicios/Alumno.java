package Servicios;

public class Alumno {

        private int idAlumno;
        private String apellido;
        private String nombre;
        private String dni;

        public Alumno(int idAlumno, String apellido, String nombre, String dni) {
            this.idAlumno = idAlumno;
            this.apellido = apellido;
            this.nombre = nombre;
            this.dni = dni;
        }

        // Getters y Setters

        public int getIdAlumno() {
            return idAlumno;
        }

        public void setIdAlumno(int idAlumno) {
            this.idAlumno = idAlumno;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDni() {
            return dni;
        }

        public void setDni(String dni) {
            this.dni = dni;
        }
    }

