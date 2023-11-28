public class UsuarioBiblioteca {
        //create the variable encapsulation
        private int id;
        private String nombre;
        private String apellido;
        private String Correo;
        private String direccion;
        public UsuarioBiblioteca(int id, String nombre, String apellido, String correo, String direccion) {
            this.id = id;
            this.nombre = nombre;
            this.apellido = apellido;
            Correo = correo;
            this.direccion = direccion;
        }
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getNombre() {
            return nombre;
        }
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        public String getApellido() {
            return apellido;
        }
        public void setApellido(String apellido) {
            this.apellido = apellido;
        }
        public String getCorreo() {
            return Correo;
        }
        public void setCorreo(String correo) {
            Correo = correo;
        }
        public String getDireccion() {
            return direccion;
        }
        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }
        @Override
        public String toString() {
            return "UsuarioBiblioteca [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", Correo="
                    + Correo + ", direccion=" + direccion + "]";
        }
        //we make a comparison
    public String formatoArchivo(){
        return id + "|"+ nombre + "|"+ apellido+ "|"+ Correo + "|"+ direccion + "|";
    }  
}
