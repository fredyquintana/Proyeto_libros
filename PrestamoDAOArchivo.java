//import the classes
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
//create the class UserDAOFile that inherits from the UserDao interface
public class PrestamoDAOArchivo implements PrestamoDAO {
    //create the arraylist of type user
    String nombreArchivo;
    ArrayList<Prestamo> listaPrestamos;
    //create constructor
    public PrestamoDAOArchivo(String nomAr) {
        this.nombreArchivo = nomAr;
    }
    //create the arraylist of type user
    public PrestamoDAOArchivo(ArrayList<Prestamo> listaPrestamos) {
        this.listaPrestamos = listaPrestamos;
    }
//create the deleteContent method
    public void borrarContenido() {
        try {
            //write to the text file
            FileWriter escribir = new FileWriter(nombreArchivo);
            PrintWriter linea = new PrintWriter(escribir);

            // Cerrar los flujos para garantizar que los cambios se guarden
            linea.close();
            escribir.close();

        } catch (IOException e) {
            System.out.println("Error al borrar el contenido: " + e);
        }
    }
//create the create method
    @Override
    public int create(Prestamo prestamo) throws SQLException {
        PrintWriter linea = null;
        FileWriter escribir = null;
        File archivo = new File(nombreArchivo);

        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            //write to the text file
            escribir = new FileWriter(archivo, true);
            linea = new PrintWriter(escribir);

            // Escribir en el archivo
            linea.println(prestamo.formatoArchivo());

        } catch (IOException e) {
            System.out.println("Error: " + e);
        } finally {
            // Asegúrate de cerrar PrintWriter y FileWriter en el bloque finally
            if (linea != null) {
                linea.close();
            }
            if (escribir != null) {
                try {
                    escribir.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar FileWriter: " + e);
                }
            }
        }
        return 0;
    }

    @Override
    public Prestamo read(String id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @Override
    public int update(Prestamo prestamo) throws SQLException {
        int result = -1;

        try {
            File archivo = new File(nombreArchivo);
            if (archivo.exists()) {
                //read everything in the txt file and update it
                ArrayList<Prestamo> listaPrestamos = readAll();
                for (Prestamo prestamoAux : listaPrestamos) {
                    if (prestamoAux.getId() == prestamo.getId()) {
                        prestamoAux.setId_libro(prestamo.getId_libro());
                        prestamoAux.setFecha_entrega(prestamo.getFecha_entrega());
                        prestamoAux.setFecha_devolucion(prestamo.getFecha_devolucion());
                        prestamoAux.setCantidad(prestamo.getCantidad());
                        break;
                    }
                }

                PrintWriter linea = null;
                FileWriter escribir = null;

                try {
                    //write to the text file
                    escribir = new FileWriter(archivo, false);
                    linea = new PrintWriter(escribir);

                    for (Prestamo prestamoAux : listaPrestamos) {
                        linea.println(prestamoAux.formatoArchivo());
                    }
                } catch (IOException e) {
                    System.out.println("Error: " + e);
                } finally {
                    if (linea != null) {
                        linea.close();
                    }
                    if (escribir != null) {
                        try {
                            escribir.close();
                        } catch (IOException e) {
                            System.out.println("Error al cerrar FileWriter: " + e);
                        }
                    }
                }

                result = 0;
            } else {
                System.out.println("Lista no existente");
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return result;
    }

    //Delete user
   //we go through all the arraylist with the iterator and remove the user we want
    public ArrayList<Prestamo> borrar(int clave) throws SQLException {
        Iterator<Prestamo> peliculaIterator = listaPrestamos.iterator();
        while (peliculaIterator.hasNext()) {
            Prestamo prestamito = peliculaIterator.next();
            if (prestamito.getId() == clave) {
                peliculaIterator.remove();
            }
        }
        return listaPrestamos;
    }
//read all the users found in the txt
    @Override
    public ArrayList<Prestamo> readAll() throws SQLException {
        ArrayList<Prestamo> listaPrestamos = new ArrayList<>();

        try {
            File archivo = new File(nombreArchivo);
            if (archivo.exists()) {
                FileReader leer = new FileReader(archivo);
                BufferedReader br = new BufferedReader(leer);
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] valores = linea.split("\\|");
                    int id = Integer.parseInt(valores[0]);
                    String id_libro =valores[1];
                    String fecha_entrega = valores[2];
                    String fecha_devolucion = valores[3];
                    String cantidad = valores[4];

                    Prestamo aux = new Prestamo(id, id_libro, fecha_entrega, fecha_devolucion, cantidad);
                    //add the user to the listUsers
                    listaPrestamos.add(aux);

                }
            } else {
                System.out.println("Lista no existente");
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return listaPrestamos;
    }

@Override
public int delete(int id) throws SQLException {

  ArrayList<Prestamo> prestamos = readAll();

  Iterator<Prestamo> iterator = prestamos.iterator();

  while(iterator.hasNext()) {
    Prestamo prestamo = iterator.next();

    if(prestamo.getId() == id) {
      iterator.remove();
    }
  }

  PrintWriter writer = null;
  FileWriter fileWriter = null;

  try {
    //write to the text file
    fileWriter = new FileWriter(nombreArchivo, false);
    writer = new PrintWriter(fileWriter);
    //we write in the txt with the format that we put in it
    for(Prestamo prestamo : prestamos) {
      writer.println(prestamo.formatoArchivo());
    }

  } catch (IOException e) {
    e.printStackTrace();
  } finally {
    if(writer != null) writer.close();
    if(fileWriter != null) try {
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  return 0;
}


}
