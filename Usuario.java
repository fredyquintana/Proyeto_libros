//create the user class 
public class Usuario {
    //create the variable encapsulation
    private int id;
    private String ISBN;
    protected String autor;
    private String genero;
    public String cantidad;

    
//create the constructor
    public Usuario(int id, String ISBN, String autor, String genero, String cantidad) {
        this.id = id;
        this.ISBN = ISBN;
        this.autor = autor;
        this.genero = genero;
        this.cantidad = cantidad;
    }
    
    
//create the getter and setter
    public void setId(int id) {
		this.id = id;
	}



	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}



	public void setAutor(String autor) {
		this.autor = autor;
	}



	public void setGenero(String genero) {
		this.genero=genero;
	}



	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}



	public int getId() {
        return id;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public String getCantidad() {
        return cantidad;
    }
//create the toString method
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", ISBN=" + ISBN + ", autor=" + autor + ", genero=" + genero
                + ", cantidad=" + cantidad + "]";
    }
//we make a comparison
    public String formatoArchivo(){
        return id + "|"+ ISBN + "|"+ autor + "|"+ genero + "|"+ cantidad + "|";
    }

    
    
}
