//create the user class 
public class Prestamo {
    //create the variable encapsulation
    private int id;
    private String id_libro;
    private String fecha_entrega;
    private String fecha_devolucion;
    private String cantidad;
 
public Prestamo(int id, String id_libro, String fecha_entrega, String fecha_devolucion, String cantidad) {
        this.id = id;
        this.id_libro = id_libro;
        this.fecha_entrega = fecha_entrega;
        this.fecha_devolucion = fecha_devolucion;
        this.cantidad = cantidad;
    }



    //create the getter and setter
    public void setId(int id) {
		this.id = id;
	}



    public void setId_libro(String id_libro) {
        this.id_libro = id_libro;
    }


	public void setFecha_entrega(String fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}



	public void setFecha_devolucion(String fecha_devolucion) {
		this.fecha_devolucion=fecha_devolucion;
	}



	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}



	public int getId() {
        return id;
    }

    public String getId_libro() {
        return id_libro;
    }

    public String getFecha_entrega() {
        return fecha_entrega;
    }

    public String getFecha_devolucion() {
        return fecha_devolucion;
    }

    public String getCantidad() {
        return cantidad;
    }
//create the toString method
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", id_libro=" + id_libro + ", fecha_entrega=" + fecha_entrega + ", fecha_devolucion=" + fecha_devolucion
                + ", cantidad=" + cantidad + "]";
    }
//we make a comparison
    public String formatoArchivo(){
        return id + "|"+ id_libro + "|"+ fecha_entrega+ "|"+ fecha_devolucion + "|"+ cantidad + "|";
    }

  
}
