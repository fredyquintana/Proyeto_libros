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
public class UsuarioDAOArchivo implements UsuarioDAO {
    //create the arraylist of type user
    String nombreArchivo;
    ArrayList<Usuario> listaUsuarios;
    //create constructor
    public UsuarioDAOArchivo(String nomAr) {
        this.nombreArchivo = nomAr;
    }
    //create the arraylist of type user
    public UsuarioDAOArchivo(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
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
    public int create(Usuario usuario) throws SQLException {
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
            linea.println(usuario.formatoArchivo());

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
    public Usuario read(String id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @Override
    public int update(Usuario usuario) throws SQLException {
        int result = -1;

        try {
            File archivo = new File(nombreArchivo);
            if (archivo.exists()) {
                //read everything in the txt file and update it
                ArrayList<Usuario> listaUsuarios = readAll();
                for (Usuario usuarioAux : listaUsuarios) {
                    if (usuarioAux.getId() == usuario.getId()) {
                        usuarioAux.setISBN(usuario.getISBN());
                        usuarioAux.setAutor(usuario.getAutor());
                        usuarioAux.setGenero(usuario.getGenero());
                        usuarioAux.setCantidad(usuario.getCantidad());
                        break;
                    }
                }

                PrintWriter linea = null;
                FileWriter escribir = null;

                try {
                    //write to the text file
                    escribir = new FileWriter(archivo, false);
                    linea = new PrintWriter(escribir);

                    for (Usuario usuarioAux : listaUsuarios) {
                        linea.println(usuarioAux.formatoArchivo());
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
    public ArrayList<Usuario> borrar(int clave) throws SQLException {
        Iterator<Usuario> peliculaIterator = listaUsuarios.iterator();
        while (peliculaIterator.hasNext()) {
            Usuario usuario = peliculaIterator.next();
            if (usuario.getId() == clave) {
                peliculaIterator.remove();
            }
        }
        return listaUsuarios;
    }
//read all the users found in the txt
    @Override
    public ArrayList<Usuario> readAll() throws SQLException {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        try {
            File archivo = new File(nombreArchivo);
            if (archivo.exists()) {
                FileReader leer = new FileReader(archivo);
                BufferedReader br = new BufferedReader(leer);
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] valores = linea.split("\\|");
                    int id = Integer.parseInt(valores[0]);
                    String nombre = valores[1];
                    String apellido = valores[2];
                    String correo = valores[3];
                    String contrasena = valores[4];

                    Usuario aux = new Usuario(id, nombre, apellido, correo, contrasena);
                    //add the user to the listUsers
                    listaUsuarios.add(aux);

                }
            } else {
                System.out.println("Lista no existente");
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return listaUsuarios;
    }

@Override
public int delete(int id) throws SQLException {

  ArrayList<Usuario> usuarios = readAll();

  Iterator<Usuario> iterator = usuarios.iterator();

  while(iterator.hasNext()) {
    Usuario usuario = iterator.next();

    if(usuario.getId() == id) {
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
    for(Usuario usuario : usuarios) {
      writer.println(usuario.formatoArchivo());
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
